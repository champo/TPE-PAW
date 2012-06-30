package ar.edu.itba.paw.grupo1.web.Register;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@AuthorizeInstantiation(WicketSession.GUEST)
public class RegisterPage extends BasePage {
	
	public RegisterPage() {
		
	}
}
