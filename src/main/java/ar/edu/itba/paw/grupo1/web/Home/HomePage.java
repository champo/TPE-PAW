package ar.edu.itba.paw.grupo1.web.Home;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.HelloWorld.HelloWorldPage;
import ar.edu.itba.paw.grupo1.web.Login.LoginPage;
import ar.edu.itba.paw.grupo1.web.PropertyList.PropertyListPage;
import ar.edu.itba.paw.grupo1.web.Register.RegisterPage;

public class HomePage extends BasePage {

	public HomePage() {
		add(new BookmarkablePageLink<Void>("helloWorld", HelloWorldPage.class));
		add(new BookmarkablePageLink<Void>("propertyListLink", PropertyListPage.class).setVisible(isSignedIn()));
		add(new BookmarkablePageLink<Void>("loginLink", LoginPage.class).setVisible(!isSignedIn()));
		add(new BookmarkablePageLink<Void>("registerLink", RegisterPage.class).setVisible(!isSignedIn()));

	}
}
