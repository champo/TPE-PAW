package ar.edu.itba.paw.grupo1.web.pages.Home;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.pages.Login.LoginPage;
import ar.edu.itba.paw.grupo1.web.pages.PropertyList.PropertyListPage;
import ar.edu.itba.paw.grupo1.web.pages.Register.RegisterPage;

@SuppressWarnings("serial")
public class HomePage extends BasePage {

	public HomePage() {
		
		add(new BookmarkablePageLink<Void>("propertyListLink", PropertyListPage.class), isSignedIn());
		add(new BookmarkablePageLink<Void>("loginLink", LoginPage.class), !isSignedIn());
		add(new BookmarkablePageLink<Void>("registerLink", RegisterPage.class), !isSignedIn());

	}
}
