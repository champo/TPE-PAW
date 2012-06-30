package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import ar.edu.itba.paw.grupo1.model.User;

@SuppressWarnings("serial")
public class WicketSession extends AbstractAuthenticatedWebSession {

	public static final String GUEST = "GUEST";

	public static final String USER = "USER";
	
	private Integer id;

	public static WicketSession get() {
		return (WicketSession) Session.get();
	}

	public WicketSession(Request request) {
		super(request);
	}

	public Integer getUserId() {
		return id;
	}

	public boolean signIn(User user, String hash) {
		
		if (user != null && user.checkPassword(hash)) {
			id = user.getId();
			bind();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isSignedIn() {
		return id != null;
	}

	public void signOut() {
		invalidate();
		clear();
	}

	@Override
	public Roles getRoles() {

		Roles roles = new Roles();

		if (isSignedIn()) {
			roles.add(USER);
		} else {
			roles.add(GUEST);
		}

		return roles;
	}
}
