package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@SuppressWarnings("serial")
public class ListPropertiesServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PropertyService propService = ApplicationContainer.get(PropertyService.class);
		User user = getLoggedInUser(req);
		
		req.setAttribute("properties", propService.getProperties(user.getId()));
		render(req, resp, "listProperties.jsp", "List Properties");
	}	
}
