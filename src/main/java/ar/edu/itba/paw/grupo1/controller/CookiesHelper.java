package ar.edu.itba.paw.grupo1.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesHelper {

	private CookiesHelper() {
	}
	
	public static Cookie getCookie(HttpServletRequest req, String name) {
		
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
	
	public static void expireCookie(HttpServletRequest req, HttpServletResponse resp, String name) {
		
		Cookie cookie = getCookie(req, name);
		if (cookie != null) {
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
	}
	
	public static void setInfiniteCookie(HttpServletResponse resp, String name,
			String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(Integer.MAX_VALUE);
		resp.addCookie(cookie);
	}
	
}
