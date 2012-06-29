package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.paw.grupo1.web.home.HomePage;

public class Application extends WebApplication {

	private final SessionFactory sessionFactory;
	
	@Autowired
	public Application(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		getRequestCycleListeners().add(new HibernateRequestCycleListener(sessionFactory));
	}
	
	
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}
	
}
