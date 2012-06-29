package ar.edu.itba.paw.grupo1.web.home;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.HelloWorld.HelloWorldPage;
import ar.edu.itba.paw.grupo1.web.PropertyList.PropertyListPage;

public class HomePage extends BasePage {

	public HomePage() {
		add(new BookmarkablePageLink<Void>("helloWorld", HelloWorldPage.class));
		add(new BookmarkablePageLink<Void>("property_list_link", PropertyListPage.class));
		add(new BookmarkablePageLink<Void>("login_link", PropertyListPage.class));
		add(new BookmarkablePageLink<Void>("register_link", PropertyListPage.class));

	}
}
