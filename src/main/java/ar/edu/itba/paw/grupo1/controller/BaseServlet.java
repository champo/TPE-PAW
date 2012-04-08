package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.RespectBinding;

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
	
	protected User getLoggedInUser(HttpServletRequest req) {
		return (User) req.getAttribute("user");
	}
	
	protected void logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		req.removeAttribute("user");
		
		CookiesHelper.expireCookie(req, resp, "username");
		CookiesHelper.expireCookie(req, resp, "hash");
	}
	
	protected void rememberUsername(HttpServletResponse resp, User user) {
		CookiesHelper.setInfiniteCookie(resp, "username", user.getUsername());
	}

	protected void rememberUser(HttpServletResponse resp, User user) {
		rememberUsername(resp, user);
		CookiesHelper.setInfiniteCookie(resp, "hash", user.getPassword());
	}
	
	protected String getRememberedName(HttpServletRequest req) {
		
		Cookie cookie = CookiesHelper.getCookie(req, "username");
		if (cookie != null) {
			return cookie.getValue();
		}
		
		return null;
	}
	
	
}