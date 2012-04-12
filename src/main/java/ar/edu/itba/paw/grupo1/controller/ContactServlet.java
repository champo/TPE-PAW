package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.controller.exception.InvalidParameterException;
import ar.edu.itba.paw.grupo1.controller.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.PropertyService;
import ar.edu.itba.paw.grupo1.service.UserService;

@SuppressWarnings("serial")
public class ContactServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int propertyId;
		Property property;
		if (checkIntegerParameter(req, "propertyId")) {

			propertyId = Integer.parseInt(req.getParameter("propertyId"));			
			property = ApplicationContainer.get(PropertyService.class).getById(propertyId);
			
			if (property == null) {
				throw new InvalidParameterException();
			} else if (property.getUserId() == getLoggedInUser(req).getId()) {
				resp.sendRedirect(req.getContextPath() + "/propertyDetail?id=" + property.getId());
				return;
			} else if (!property.isPublished()) {
				resp.sendRedirect(req.getContextPath() + "/query?unpublished=true");
				return;
			}			
		} else {
			throw new InvalidParameterException();
		}
		req.setAttribute("propertyId", property.getId());
		req.setAttribute("address", property.getAddress());
		req.setAttribute("neighbourhood", property.getNeighbourhood());
		render(req, resp, "contact.jsp", "Contact");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Property property;
		if (checkIntegerParameter(req, "propertyId")) {
			property = ApplicationContainer.get(PropertyService.class).getById(Integer.parseInt(req.getParameter("propertyId")));

			boolean error = false;
			
			error |= !checkParameter(req, "name", 0, 50);
			error |= !checkParameter(req, "email", 0, 50);
			error |= !checkParameter(req, "phone", 0, 50);
			error |= !checkParameter(req, "comment", 0, 1000, true);
			error |= !checkEmail(req, "email", 0, 50);
			error |= !checkPhone(req, "phone", 0, 20);
			
			if (error) {
				req.setAttribute("propertyId", property.getId());
				req.setAttribute("address", property.getAddress());
				req.setAttribute("neighbourhood", property.getNeighbourhood());
				render(req, resp, "contact.jsp", "Contact");
				return;
			}
		
			if (property == null) {
				throw new InvalidParameterException();
			} else if (property.getUserId() == getLoggedInUser(req).getId()) {
				resp.sendRedirect(req.getContextPath() + "/propertyDetail?id=" + property.getId());
				return;
			} else if (!property.isPublished()) {
				resp.sendRedirect(req.getContextPath() + "/query?unpublished=true");
				return;
			}
		} else {
			throw new InvalidParameterException();
		}
		req.setAttribute("propertyId", property.getId());
		req.setAttribute("address", property.getAddress());
		req.setAttribute("neighbourhood", property.getNeighbourhood());
		req.setAttribute("publisher", ApplicationContainer.get(UserService.class).get(property.getUserId()));
		render(req, resp, "contact.jsp", "Contact");
	}
}
