package ar.edu.itba.paw.grupo1.web.Query;


import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.OperationType;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.OrderType;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Currency;
import ar.edu.itba.paw.grupo1.web.QueryListPanel;
import ar.edu.itba.paw.grupo1.web.WicketUtils;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
public class QueryPage extends BasePage {
	
	public QueryPage() {
		
		this(null);		
	}
	
	public QueryPage(boolean unpublished) {
		//show that the property is no longer published, the user cannot acces it
	}

	public QueryPage(PropertyQuery propertyQuery) {

		addLabel("unpublished",false);
		if (propertyQuery == null) {
			propertyQuery = new PropertyQuery();
		}
		
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		
		Form<PropertyQuery> form = new Form<PropertyQuery>("queryForm", new CompoundPropertyModel<PropertyQuery>(propertyQuery)) {
			
			@Override
			protected void onSubmit() {
								
				feedbackPanel.setVisible(false);
				setResponsePage(new QueryPage(getModelObject()));
			}

			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		
		WicketUtils.addRadioChoice(form, "property", PropertyType.values());
		WicketUtils.addRadioChoice(form, "operation", OperationType.values());
		
		WicketUtils.addDropDownMenu(form, "currency", Currency.values());
		
		form.add(new TextField<Double>("rangeFrom"));
		form.add(new TextField<Double>("rangeTo"));
		WicketUtils.addDropDownMenu(form, "order", OrderType.values());

		form.add(feedbackPanel);		
		form.add(new Button("submit", new ResourceModel("submit")));
		add(form);

		QueryListPanel queryListPanel = new QueryListPanel("queryListPanel", propertyQuery);
		add(queryListPanel, queryListPanel.size() != 0);	
	}	
}
