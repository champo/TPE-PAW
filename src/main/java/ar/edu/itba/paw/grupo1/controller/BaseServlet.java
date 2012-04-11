package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	
	protected boolean checkParameter(HttpServletRequest req, String param, int min, int max) {
		
		return checkParameter(req, param, min, max, false); 
	}
	
	protected boolean checkParameter(HttpServletRequest req, String param, int min, int max, boolean optional) {
		
		String value = req.getParameter(param);
		if ((value == null || value.length() == 0) && !optional) {
			req.setAttribute(param + "Empty", true);
			return false;
		} else if (value.length() < min || value.length() > max) {
			req.setAttribute(param + "BadLength", true);
			return false;
		}
		
		return true;
	}
	
	protected boolean checkIntegerParameter(HttpServletRequest req, String param, int min, int max) {
		
		String value = req.getParameter(param);
		Integer num;
		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			return false;
		}
		
		try {
			num = Integer.parseInt(value);
		} catch (Exception e) {
			req.setAttribute(param + "InvalidFormat", true);
			return false;
		}
		
		if (num < min || num > max) {
			req.setAttribute(param + "OutOfRange", true);
			return false;
		}
		return true;
	}

	protected boolean checkDoubleParameter(HttpServletRequest req, String param, int min, double max) {
		
		String value = req.getParameter(param);
		Double num;
		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			return false;
		}
		
		try {
			num = Double.parseDouble(value);
		} catch (Exception e) {
			req.setAttribute(param + "InvalidFormat", true);
			return false;
		}
		
		if (num <= min || num >= max) {
			req.setAttribute(param + "OutOfRange", true);
			return false;
		}
		return true;
	}
	
}