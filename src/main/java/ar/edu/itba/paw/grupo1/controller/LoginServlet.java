package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.ApplicationContainer;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;

public class LoginServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (isLoggedIn(req)) {
			resp.sendRedirect("/");
			return;
		}
		
		render(req, resp, "login.jsp", "Login");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (isLoggedIn(req)) {
			resp.sendRedirect("/");
			return;
		}
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (username != null && password != null) {
			UserService userService = ApplicationContainer.get(UserService.class);
			User user = userService.login(username, password);
			
			if (user != null) {
				// We can log in now!
				setLoggedInUser(req, user);
				resp.sendRedirect("/");
				return;
			}
		}
		
		req.setAttribute("invalidCredentials", true);
		render(req, resp, "login.jsp", "Login");
	}
}
