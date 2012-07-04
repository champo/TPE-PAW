package ar.edu.itba.paw.grupo1.web.AddProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.web.Service;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.PropertyList.PropertyListPage;

@SuppressWarnings("serial")
public class AddPropertyPage extends BasePage {

	private transient String address;
	private transient String neighbourhood;
	private transient double price;
	private transient int numRooms;
	private transient double indoorSpace;
	private transient double outdoorSpace;
	private transient String description;
	private transient int antiquity;
	private transient List<Service> servicesList;
	private transient PropertyType propertyType = PropertyType.HOUSE;
	private transient OperationType operationType = OperationType.SELLING;

	@SpringBean
	private PropertyRepository properties;
	
	
	public AddPropertyPage() {

		servicesList = new ArrayList<Service>(getServices(new HashSet<Services>())); 
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		
		Form<AddPropertyPage> form = new Form<AddPropertyPage>("addPropertyForm", new CompoundPropertyModel<AddPropertyPage>(this)) {
			
			@Override
			protected void onSubmit() {
				
				Set<Services> services = new HashSet<Services>(toEnumSet(servicesList));
				Property property = new Property(propertyType, operationType, address, neighbourhood, price,
						numRooms, indoorSpace, outdoorSpace, description, antiquity, services, true, 
						getSignedInUser(), false, 0);
				property.publish();
				if (!isMine(property)) {
					throw new PermissionDeniedException();
				}
				properties.save(property);
				setResponsePage(PropertyListPage.class);
			}

			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
				
		addDropDownMenu("propertyType", new PropertyType[] {PropertyType.HOUSE, PropertyType.FLAT}, form);
		addDropDownMenu("operationType", new OperationType[] {OperationType.SELLING, OperationType.LEASING}, form);

		form.add(feedbackPanel);		
		form.add(new TextField<String>("address").setRequired(true));
		form.add(new TextField<String>("neighbourhood").setRequired(true));
		form.add(new TextField<Double>("price").setRequired(true));
		form.add(new TextField<Integer>("numRooms").setRequired(true));
		form.add(new TextField<Double>("indoorSpace").setRequired(true));
		form.add(new TextField<Double>("outdoorSpace").setRequired(true));
		form.add(new TextArea<String>("description").setRequired(false));
		form.add(new TextField<Integer>("antiquity").setRequired(true));
		
		form.add(new ListView<Service>("servicesList", new PropertyModel<List<Service>>(this, "servicesList")) {

			@Override
			protected void populateItem(ListItem<Service> item) {
				Service service = item.getModelObject();
				item.add(new CheckBox("service", new PropertyModel<Boolean>(service, "present")));				
				item.add(new Label("serviceLabel", getLocalizer().getString(service.getName(), this)));
			}
		});
		
		form.add(new Button("submit", new ResourceModel("Submit")));
		add(form);
	}
	
	private Set<Services> toEnumSet(List<Service> servicesList) {
		HashSet<Services> set = new HashSet<Services>();
		
		for (Service s: servicesList) {
			set.add(s.ToEnum());
		}
		
		return set;
	}

	private <T extends Enum<T>> void addDropDownMenu(String id, T[] selectOptions, Form<AddPropertyPage> form) {
		
		EnumChoiceRenderer<T> choiceRenderer = new EnumChoiceRenderer<T>(this);
		List<? extends T> model = Arrays.asList(selectOptions);
		form.add(new DropDownChoice<T>(id, model, choiceRenderer).setRequired(true));
	}
}
