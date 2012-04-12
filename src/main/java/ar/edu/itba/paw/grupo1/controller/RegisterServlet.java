package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;

@SuppressWarnings("serial")
public class RegisterServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (isLoggedIn(req)) {
			resp.sendRedirect(req.getContextPath());
			return;
		}
		
		render(req, resp, "register.jsp", "Register");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (isLoggedIn(req)) {
			resp.sendRedirect(req.getContextPath());
			return;
		}
		
		boolean error = false;
		
		error |= !checkParameter(req, "name", 0, 50);
		error |= !checkParameter(req, "surname", 0, 50);
		error |= !checkParameter(req, "username", 0, 50);
		error |= !hasParameter(req, "password");
		error |= !areParamsEqual(req, "password", "passwordConfirmation");
		error |= !checkEmail(req, "email", 0, 50);
		error |= !checkPhone(req, "phone", 0, 20);
		
		if (error) {
			render(req, resp, "register.jsp", "Register");
			return;
		}
		
		try {
			User user = ApplicationContainer.get(UserService.class).register(
				req.getParameter("name"),
				req.getParameter("surname"),
				req.getParameter("email"),
				req.getParameter("phone"),
				req.getParameter("username"),
				req.getParameter("password")
			);
			
			if (user != null) {
				render(req, resp, "registerSuccess.jsp", "Register");
				return;
			}
			
		} catch (UserAlreadyExistsException e) {
			req.setAttribute("usernameDuplicate", true);
		}
		
		render(req, resp, "register.jsp", "Register");
	}
}
