package ar.edu.itba.paw.grupo1.web.pages.Query;

import org.apache.wicket.markup.html.basic.Label;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.model.support.EntityModel;
import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.panels.QueryListPanel;

@SuppressWarnings("serial")
public class UserPropertiesPage extends BasePage {
	
	public UserPropertiesPage(User user) {
		
		addLabel("page.title", "page.title", new EntityModel<User>(User.class, user), true);
		add(new Label("page.subtitle", getLocalizer().getString("page.subtitle", this, new EntityModel<User>(User.class, user))));
		
		QueryListPanel queryListPanel = new QueryListPanel("queryListPanel", user);
		add(queryListPanel, queryListPanel.isVisible());
		
	}
}
