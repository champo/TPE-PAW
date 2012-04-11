package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.ValidationUtils;
import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;

public class RegisterServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (isLoggedIn(req)) {
			resp.sendRedirect("/");
			return;
		}
		
		render(req, resp, "register.jsp", "Register");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (isLoggedIn(req)) {
			resp.sendRedirect("/");
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
	
	protected boolean checkPhone(HttpServletRequest req, String param, int min, int max) {
		
		if (!checkParameter(req, param, min, max)) {
			return false;
		} else {
			
			if (ValidationUtils.isPhoneNumber(req.getParameter(param))) {
				return true;
			} else {
				req.setAttribute(param + "InvalidFormat", true);
				return false;
			}
		}
	}

	protected boolean checkEmail(HttpServletRequest req, String param, int min,	int max) {
		
		if (!checkParameter(req, param, min, max)) {
			return false;
		} else if (ValidationUtils.isEmail(req.getParameter(param))) {
			return true;
		} else {
			req.setAttribute(param + "InvalidFormat", true);
			return false;
		}
	}

	protected boolean areParamsEqual(HttpServletRequest req, String param1, String param2) {
		
		String value1 = req.getParameter(param1);
		String value2 = req.getParameter(param2);
		
		if (value1 == null || value2 == null) {
			return false;
		} else if (value1.equals(value2)) {
			return true;
		} else {
			req.setAttribute(param2 + "DoesntMatch", true);
			return false;
		}
	}
	
	protected boolean hasParameter(HttpServletRequest req, String param) {
		
		String value = req.getParameter(param);
		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			return false;
		}
		
		return true;
	}
	
	protected boolean checkParameter(HttpServletRequest req, String param, int min, int max) {
		
		String value = req.getParameter(param);
		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			return false;
		} else if (!ValidationUtils.isWithinLength(value, min, max)) {
			req.setAttribute(param + "BadLength", true);
			return false;
		}
		
		return true;
	}
	
}
