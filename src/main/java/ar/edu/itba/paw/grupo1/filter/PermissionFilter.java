package ar.edu.itba.paw.grupo1.filter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class PermissionFilter implements javax.servlet.Filter {

	private Set<String> restrictedActions = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		String actionsString = filterConfig.getInitParameter("restricted-actions");
		
		restrictedActions = new TreeSet<String>();
		if (actionsString == null) {
			return;
		}
		
		String[] actions = actionsString.split(",");
		for (String action : actions) {
			restrictedActions.add(action);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (restrictedActions.contains(req.getServletPath())) {

			HttpSession session = req.getSession();
			if (session.getAttribute("userId") == null) {
				
				HttpServletResponse resp = (HttpServletResponse) response;

				String from = req.getRequestURI();
				if (req.getQueryString() != null) {
					from += "?" + req.getQueryString();
				}
				
				resp.sendRedirect(req.getContextPath() + "/login?from=" + URLEncoder.encode(from, "UTF-8"));
				return;
			}
			
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}

