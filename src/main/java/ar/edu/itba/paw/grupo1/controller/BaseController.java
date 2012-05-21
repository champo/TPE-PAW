package ar.edu.itba.paw.grupo1.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.grupo1.model.Owned;
import ar.edu.itba.paw.grupo1.model.User;

@Controller
public abstract class BaseController extends WebApplicationObjectSupport {

	public BaseController() {
		super();
	}

	protected ModelAndView render(String file, String title, ModelAndView mav) {
		
		mav.addObject("documentTitle", title);
		mav.addObject("documentBodyFile", file);
		mav.addObject("basePath", getServletContext().getContextPath());
		mav.setViewName("layout");
		
		return mav;
	}
	
	protected boolean isMine(HttpServletRequest req, Owned obj) {
		return obj.getUser().getId() == getLoggedInUser(req).getId();
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