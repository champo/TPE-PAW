package ar.edu.itba.paw.grupo1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.grupo1.ValidationUtils;
import ar.edu.itba.paw.grupo1.model.User;

@Controller
public abstract class BaseController extends WebApplicationObjectSupport {

	public BaseController() {
		super();
	}

	protected ModelAndView render(HttpServletRequest req, HttpServletResponse resp, String file, String title)
			throws ServletException, IOException {
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("documentTitle", title);
		mav.addObject("documentBodyFile", file);
		mav.addObject("basePath", req.getContextPath());
		mav.setViewName("layout");
		return mav;
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

	protected boolean checkIntegerParameter(HttpServletRequest req, String param) {
		
		String value = req.getParameter(param);
		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			return false;
		}
		
		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			req.setAttribute(param + "InvalidFormat", true);
			return false;
		}
		return true;
	}
	
	protected boolean checkIntegerParameter(HttpServletRequest req, String param, int min, int max) {
		

		if (!checkIntegerParameter(req, param)) {
			return false;
		}
		
		String value = req.getParameter(param);
		Integer num = Integer.parseInt(value);

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

}