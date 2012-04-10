package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@SuppressWarnings("serial")
public class PublishPropertyServlet extends BaseServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (req.getParameter("id") != null) {
			int id = Integer.parseInt(req.getParameter("id"));
			PropertyService propService = ApplicationContainer.get(PropertyService.class);
			Property property = propService.getById(id);
			property.publish();
			propService.save(property, getLoggedInUser(req));			
		}
		resp.sendRedirect("/listProperties");
	}
}
