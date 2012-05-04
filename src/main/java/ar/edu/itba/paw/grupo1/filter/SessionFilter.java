package ar.edu.itba.paw.grupo1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.itba.paw.grupo1.controller.CookiesHelper;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;

public class SessionFilter implements Filter {

	private UserService userService;

	public SessionFilter(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain next) throws IOException, ServletException {
		
		if (!(req instanceof HttpServletRequest)) {
			next.doFilter(req, resp);
		}
		
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpSession session = httpReq.getSession();
		
		Object userIdObj = session.getAttribute("userId");
		User user = null;
		
		if (userIdObj instanceof Integer) {
			Integer userId = (Integer) userIdObj;
			user = (User) userService.get(userId);
		} else {

			Cookie username = CookiesHelper.getCookie(httpReq, "username");
			Cookie hash = CookiesHelper.getCookie(httpReq, "hash");
			
			if (username != null && hash != null) {
				user = userService.loginWithHash(username.getValue(), hash.getValue());
				
				if (user != null) {
					session.setAttribute("userId", user.getId());
				}
			}
		}
		
		if (user != null) {
			req.setAttribute("user", user);
		}
		
		next.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}	
}
