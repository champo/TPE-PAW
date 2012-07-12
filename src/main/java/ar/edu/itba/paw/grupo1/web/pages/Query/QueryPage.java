package ar.edu.itba.paw.grupo1.web.pages.Query;


import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.RangeValidator;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.OperationType;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.OrderType;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Currency;
import ar.edu.itba.paw.grupo1.web.WicketUtils;
import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.panels.QueryListPanel;

@SuppressWarnings("serial")
public class QueryPage extends BasePage {
	
	private FeedbackPanel feedbackPanel;

	public QueryPage() {
		this(null);		
	}
	
	public QueryPage(PropertyQuery propertyQuery) {

		if (propertyQuery == null) {
			propertyQuery = new PropertyQuery();
		}
		
		feedbackPanel = new FeedbackPanel("feedback");
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
		
		TextField<Double> rangeFromTextField = new TextField<Double>("rangeFrom");
		TextField<Double> rangeToTextField = new TextField<Double>("rangeTo");
		rangeFromTextField.add(new RangeValidator<Double>(0.0, (double) Integer.MAX_VALUE));
		rangeToTextField.add(new RangeValidator<Double>(0.0, (double) Integer.MAX_VALUE));
		
		form.add(rangeToTextField);
		form.add(rangeFromTextField);
		form.add(new PricesRangeValidator(rangeFromTextField, rangeToTextField));
		WicketUtils.addDropDownMenu(form, "order", OrderType.values());

		form.add(feedbackPanel);		
		form.add(new Button("submit", new ResourceModel("submit")));
		add(form);

		QueryListPanel queryListPanel = new QueryListPanel("queryListPanel", propertyQuery);
		add(queryListPanel, queryListPanel.size() != 0);	
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		feedbackPanel.setVisible(feedbackPanel.anyMessage());
	}
}

class PricesRangeValidator extends AbstractFormValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1381612933564811051L;

	private final TextField<Double> fromComp;
	private final TextField<Double> toComp;

	public PricesRangeValidator(TextField<Double> comp1, TextField<Double> comp2)
	{
		fromComp = comp1;
		toComp = comp2;
	}
	
	@Override
	public FormComponent<?>[] getDependentFormComponents() {
		return new FormComponent[] { fromComp, toComp };
	}

	@Override
	public void validate(Form<?> form) {
		String from = fromComp.getValue();
		String to = toComp.getValue();
		if ("".equals(from)) {
			from = "0";
		} else {
			from = from.replaceAll(",", "");
		}
		if ("".equals(to)) { 
			to = Integer.toString(Integer.MAX_VALUE);
		} else {
			to = to.replaceAll(",", "");
		}
		
		if (Double.parseDouble(from) > Double.parseDouble(to)) {
			error(fromComp);
		}
	}
}

