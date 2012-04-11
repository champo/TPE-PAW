package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.controller.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@SuppressWarnings("serial")
public class PublishPropertyServlet extends BaseServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (checkIntegerParameter(req, "id")) {
			int id = Integer.parseInt(req.getParameter("id"));
			PropertyService propService = ApplicationContainer.get(PropertyService.class);
			Property property = propService.getById(id);
			
			if (property == null) {
				throw new InvalidParameterException();
			} else if (property.getUserId() != getLoggedInUser(req).getId()) {
				throw new PermissionDeniedException();
			}
			
			property.publish();
			propService.save(property, getLoggedInUser(req));			
		}
		resp.sendRedirect("/listProperties");
	}
}
