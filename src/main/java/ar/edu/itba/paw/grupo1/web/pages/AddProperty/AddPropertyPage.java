package ar.edu.itba.paw.grupo1.web.pages.AddProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.Currency;
import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.pages.PropertyList.PropertyListPage;
import ar.edu.itba.paw.grupo1.web.panels.PropertyFormPanel;

@SuppressWarnings("serial")
@AuthorizeInstantiation(WicketSession.USER)
public class AddPropertyPage extends BasePage {

	private transient String address;
	private transient String neighbourhood;
	private transient double price;
	private transient int numRooms;
	private transient double indoorSpace;
	private transient double outdoorSpace;
	private transient String description;
	private transient int antiquity;
	private transient PropertyType propertyType = PropertyType.HOUSE;
	private transient OperationType operationType = OperationType.SELLING;
	private transient Currency currency = Currency.$;

	@SpringBean
	private PropertyRepository properties;
	
	private FeedbackPanel feedbackPanel;
	
	
	public AddPropertyPage() {

		feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
        final CheckGroup<Services> group = new CheckGroup<Services>("group", new ArrayList<Services>());

		Form<AddPropertyPage> form = new Form<AddPropertyPage>("addPropertyForm", new CompoundPropertyModel<AddPropertyPage>(this)) {
			
			@Override
			protected void onSubmit() {
				
				String desc = "";
				if (description != null) {
					desc = description;
				}
				
				Set<Services> services = new HashSet<Services>(group.getModelObject());
				Property property = new Property(propertyType, operationType, address, neighbourhood, price,
						numRooms, indoorSpace, outdoorSpace, desc, antiquity, services, getSignedInUser(), 
						currency);
				property.publish();
				properties.save(property);
				setResponsePage(PropertyListPage.class);
			}

			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		PropertyFormPanel propFormPanel = new PropertyFormPanel("propertyFormPanel", Arrays.asList(Services.values()), group);
		
		form.add(propFormPanel);
		form.add(propFormPanel.getTotalSpaceValidator());
		form.add(feedbackPanel);		
		form.add(new Button("submit", new ResourceModel("Submit")));
		add(form);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		feedbackPanel.setVisible(feedbackPanel.anyMessage());
	}
}
