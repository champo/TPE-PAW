package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.CryptoMapper;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.paw.grupo1.repository.UserRepository;
import ar.edu.itba.paw.grupo1.web.pages.Home.HomePage;
import ar.edu.itba.paw.grupo1.web.pages.Login.LoginPage;

public class Application extends AuthenticatedWebApplication {

	private final SessionFactory sessionFactory;
	
	private final UserRepository users;
	
	@Autowired
	public Application(SessionFactory sessionFactory, UserRepository users) {
		this.sessionFactory = sessionFactory;
		this.users = users;
	}
	
	@Override
	protected void init() {
		super.init();
		
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		getRequestCycleListeners().add(new HibernateRequestCycleListener(sessionFactory));
		setRootRequestMapper(new CryptoMapper(getRootRequestMapper(), this));
		
		getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_NO_EXCEPTION_PAGE);
	}
	
	
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new WicketSession(request, users);
	}
	
	
	@Override
	protected IConverterLocator newConverterLocator() {
		ConverterLocator converterLocator = new ConverterLocator();
		//Here's where the converters are registered
		return converterLocator;
	}
	
	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return WicketSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}
	
	
	
}
