package ar.edu.itba.paw.grupo1.web.Query;

import org.apache.wicket.markup.html.basic.Label;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.web.QueryListPanel;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
public class UserPropertiesPage extends BasePage {

	private int resultsPerPage = 10;

	public UserPropertiesPage(User user) {
		
		add(new Label("test", user.getName()));
		//TODO
		
		add(new Label("page.subtitle", getLocalizer().getString("page.subtitle", this, new EntityModel<User>(User.class, user))));
		
		PropertyQuery propertyQuery = new PropertyQuery();
		QueryListPanel queryListPanel = new QueryListPanel("queryListPanel", propertyQuery, resultsPerPage );
		add(queryListPanel, queryListPanel.isVisible());
		
	}
}
