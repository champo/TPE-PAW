package ar.edu.itba.paw.grupo1.web;

import javax.servlet.http.Cookie;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.UserRepository;

@SuppressWarnings("serial")
public class WicketSession extends AbstractAuthenticatedWebSession {

	public static final String GUEST = "GUEST";

	public static final String USER = "USER";
	
	private Integer id;

	public static WicketSession get() {
		return (WicketSession) Session.get();
	}

	public WicketSession(Request request, UserRepository users) {
		super(request);
		
		if (request instanceof WebRequest) {
			WebRequest req = (WebRequest) request;
			
			Cookie username = req.getCookie("username");
			Cookie pass = req.getCookie("pass");
			
			if (username != null && pass != null) {
				signIn(users.get(username.getValue()), pass.getValue());
			}
		}
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

	public void signOut(Request request, Response response) {
		
		WebResponse res = (WebResponse) response;
		WebRequest req = (WebRequest) request;
		
		removeCookie(req, res, "username");
		removeCookie(req, res, "pass");
		
		invalidate();
	}
	
	private void removeCookie(WebRequest req, WebResponse res, String name) {
		Cookie cookie = req.getCookie(name);
		if (cookie != null) {
			System.out.println("Cookies suck " + name);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
		}
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
