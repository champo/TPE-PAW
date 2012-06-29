package ar.edu.itba.paw.grupo1.web.HelloWorld;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.Home.HomePage;
import ar.edu.itba.paw.grupo1.web.LabelsExample.LabelsExamplePage;
import ar.edu.itba.paw.grupo1.web.LabelsExample.Weather;

@SuppressWarnings("serial")
public class HelloWorldPage extends BasePage {

	public HelloWorldPage() {
		add(new Label("message", "HOLA MUNDO"));
		add(new BookmarkablePageLink<Void>("home", HomePage.class));

		add(new Link<Void>("weatherBuenosAires") {
			public void onClick() {
				setResponsePage(new LabelsExamplePage(new Weather(1, 54, "Paradise city")));
			}
		});

	}

}
