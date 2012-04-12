package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;

@SuppressWarnings("serial")
public class ContactServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("id", req.getParameter("id"));
		render(req, resp, "contact.jsp", "Contact");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String comment = req.getParameter("comment");
		String id = req.getParameter("id");
		int parsedId = 0;

		boolean validates = true;
		
		req.setAttribute("id", req.getParameter("id"));
		
		try {
			parsedId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			req.setAttribute("invalidId", true);
			validates = false;
		}
		
		if (name == null || name.length() == 0) {
			req.setAttribute("invalidName", true);
			validates = false;
		} else {
			req.setAttribute("nameValue", name);
		}

		if (!checkEmail(req, "email", 1, Integer.MAX_VALUE)) {
			req.setAttribute("emailInvalidFormat", true);
			validates = false;
		} else {
			req.setAttribute("emailValue", email);
		}
		
		if (!checkPhone(req, "phone", 2, Integer.MAX_VALUE)) {
			req.setAttribute("phoneInvalidFormat", true);
			validates = false;
		} else {
			req.setAttribute("phoneValue", phone);
		}

		req.setAttribute("commentValue", comment);

		if (validates) {
			User user = ApplicationContainer.get(UserService.class).get(parsedId);
			if (user != null) {
				req.setAttribute("validates", true);
				req.setAttribute("user", user);
			} else {
				req.setAttribute("invalidId", true);
			}
		}

		render(req, resp, "contact.jsp", "Contact");
	}
}
