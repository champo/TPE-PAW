package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@SuppressWarnings("serial")
public class PropertyDetailServlet extends LayoutServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getParameter("id") != null) {
			PropertyService propertyService = ApplicationContainer.get(PropertyService.class);

			Property property = propertyService.getById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("property", property);
		} else {
			//TODO show error
		}
		render(req, resp, "propertyDetail", "Property Detail");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
