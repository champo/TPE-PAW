package ar.edu.itba.paw.grupo1.web.Query;


import org.apache.wicket.markup.html.basic.Label;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
public class QueryPage extends BasePage {

	public QueryPage(User user) {
		
		add(new Label("test", user.getName()));
	}
	
	public QueryPage() {
		// TODO Auto-generated constructor stub
	}
}
