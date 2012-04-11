package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.PictureService;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@SuppressWarnings("serial")
public class EditPropertyServlet extends AbstractPropertyServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Property property = null;
		List<Picture> pictures = null;
		if (req.getParameter("id") != null) {
			PropertyService propertyService = ApplicationContainer.get(PropertyService.class);
			PictureService pictureService = ApplicationContainer.get(PictureService.class);
			
			property = propertyService.getById(Integer.parseInt(req.getParameter("id")));
			pictures = pictureService.getByPropId(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("edit", 1);
			setPropertyAttributes(req, property);
			req.setAttribute("pictures", pictures);
		} else {
			
			throw new InvalidParameterException();
		}
		render(req, resp, "editProperty.jsp", "Edit Property");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PropertyService propertyService = ApplicationContainer.get(PropertyService.class);
		Property property = getProperty(req, resp);

		if (property == null) {
			setPropertyAttributes(req);
			req.setAttribute("edit", 1);
			render(req, resp, "editProperty.jsp", "Edit Property");
			return;
		}
		propertyService.save(property, getLoggedInUser(req));
		req.getRequestDispatcher("listProperties").forward(req, resp);

	}

}
