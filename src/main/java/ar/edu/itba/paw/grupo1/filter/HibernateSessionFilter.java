package ar.edu.itba.paw.grupo1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class HibernateSessionFilter implements Filter {
	
	private SessionFactory sessionFactory;
	
	public HibernateSessionFilter(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	@Transactional
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		chain.doFilter(request, response);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void destroy() {
	}

}
