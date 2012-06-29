package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.UserRepository;

public class WicketSession extends WebSession {

		private String username;

		
		public static WicketSession get() {
			return (WicketSession) Session.get();
		}

		public WicketSession(Request request) {
			super(request);
		}

		public String getUsername() {
			return username;
		}

		public boolean signIn(String username, String password, UserRepository users) {
			User user = users.get(username);
			if (user != null && user.checkPassword(password)) {
				this.username = username;
				return true;
			}
			return false;
		}

		public boolean isSignedIn() {
			return username != null;
		}

		public void signOut() {
	        invalidate();
	        clear();
		}
	}
