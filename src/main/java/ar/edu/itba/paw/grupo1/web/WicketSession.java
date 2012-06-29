package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import ar.edu.itba.paw.grupo1.model.User;

@SuppressWarnings("serial")
public class WicketSession extends WebSession {

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
				this.id = user.getId();
				return true;
			}
			return false;
		}

		public boolean isSignedIn() {
			return id != null;
		}

		public void signOut() {
	        invalidate();
	        clear();
		}
	}
