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
	
	protected void logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		req.removeAttribute("user");
		
		expireCookie(req, resp, "username");
		expireCookie(req, resp, "hash");
	}
	
	protected void rememberUsername(HttpServletResponse resp, User user) {
		setInfiniteCookie(resp, "username", user.getUsername());
	}

	private void setInfiniteCookie(HttpServletResponse resp, String name,
			String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(Integer.MAX_VALUE);
		resp.addCookie(cookie);
	}
	
	protected void rememberUser(HttpServletResponse resp, User user) {
		rememberUsername(resp, user);
		setInfiniteCookie(resp, "hash", user.getPassword());
	}
	
	protected String getRememberedName(HttpServletRequest req) {
		
		Cookie cookie = getCookie(req, "username");
		if (cookie != null) {
			return cookie.getValue();
		}
		
		return null;
	}

	protected void expireCookie(HttpServletRequest req, HttpServletResponse resp, String name) {
		
		Cookie cookie = getCookie(req, name);
		if (cookie != null) {
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
	}

	protected Cookie getCookie(HttpServletRequest req, String name) {
		
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}
		
		return null;
	}
	
	
}