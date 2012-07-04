package ar.edu.itba.paw.grupo1.web.Query;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
public class QueryPage extends BasePage {

	public QueryPage(PageParameters pars) {
		
		add(new Label("test", pars.get("user").toString()));
	}
	
	public QueryPage() {
		// TODO Auto-generated constructor stub
	}
}
