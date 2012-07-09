package ar.edu.itba.paw.grupo1.web.AddProperty;

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
import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.PropertyFormPanel;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.PropertyList.PropertyListPage;

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

	@SpringBean
	private PropertyRepository properties;
	
	
	public AddPropertyPage() {

		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
        final CheckGroup<Services> group = new CheckGroup<Services>("group", new ArrayList<Services>());

		Form<AddPropertyPage> form = new Form<AddPropertyPage>("addPropertyForm", new CompoundPropertyModel<AddPropertyPage>(this)) {
			
			@Override
			protected void onSubmit() {
				
				Set<Services> services = new HashSet<Services>(group.getModelObject());
				Property property = new Property(propertyType, operationType, address, neighbourhood, price,
						numRooms, indoorSpace, outdoorSpace, description, antiquity, services, true, 
						getSignedInUser(), false, 0);
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
		form.add(new PropertyFormPanel("propertyFormPanel", Arrays.asList(Services.values()), group));
		form.add(feedbackPanel);		
		form.add(new Button("submit", new ResourceModel("Submit")));
		add(form);
	}
}
