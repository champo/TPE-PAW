package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.paw.grupo1.model.User;

public abstract class BaseServlet extends HttpServlet {

	public BaseServlet() {
		super();
	}

	protected void render(HttpServletRequest req, HttpServletResponse resp, String file, String title)
			throws ServletException, IOException {
		req.setAttribute("documentTitle", title);
		req.setAttribute("documentBodyFile", file);
		req.getRequestDispatcher("WEB-INF/layout.jsp").forward(req, resp);
	}

	protected void setLoggedInUser(HttpServletRequest req, User user) {
		req.getSession().setAttribute("userId", user.getId());
	}
	
	protected boolean isLoggedIn(HttpServletRequest req) {
		return req.getSession().getAttribute("userId") != null;
	}
}