package ar.edu.itba.paw.grupo1.web.panels;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import ar.edu.itba.paw.grupo1.model.Property.Currency;
import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.web.WicketUtils;

@SuppressWarnings("serial")
public class PropertyFormPanel extends Panel{

	public PropertyFormPanel(String id, List<Services> servicesList, CheckGroup<Services> group) {
		super(id);
				
		WicketUtils.addDropDownMenu(this, "propertyType", new PropertyType[] {PropertyType.HOUSE, PropertyType.FLAT});
		WicketUtils.addDropDownMenu(this, "operationType", new OperationType[] {OperationType.SELLING, OperationType.LEASING});
		WicketUtils.addDropDownMenu(this, "currency", Currency.values());
		
		TextField<String> addressTextField = new TextField<String>("address");
		addressTextField.setRequired(true);
		addressTextField.add(StringValidator.maximumLength(50));
		
		add(addressTextField);
		
		TextField<String> neighbourhoodTextField = new TextField<String>("neighbourhood");
		neighbourhoodTextField.setRequired(true);
		neighbourhoodTextField.add(StringValidator.maximumLength(50));
		
		add(neighbourhoodTextField);
		
		TextField<Double> priceTextField = new TextField<Double>("price");
		priceTextField.setRequired(true);
		priceTextField.add(new RangeValidator<Double>(0.0 , (double) Integer.MAX_VALUE));
		
		add(priceTextField);
		
		TextField<Integer> roomsTextField = new TextField<Integer>("numRooms");
		roomsTextField.setRequired(true);
		roomsTextField.add(new RangeValidator<Integer>(0 , Integer.MAX_VALUE));
		
		add(roomsTextField);
		
		TextField<Double> indoorSpaceTextField = new TextField<Double>("indoorSpace");
		indoorSpaceTextField.setRequired(true);
		indoorSpaceTextField.add(new RangeValidator<Double>(0.0 , (double) Integer.MAX_VALUE));
		
		add(indoorSpaceTextField);

		TextField<Double> outdoorSpaceTextField = new TextField<Double>("outdoorSpace");
		outdoorSpaceTextField.setRequired(true);
		outdoorSpaceTextField.add(new RangeValidator<Double>(0.0 , (double) Integer.MAX_VALUE));
		
		add(outdoorSpaceTextField);
		
		TextArea<String> descriptionTextField = new TextArea<String>("description");
		descriptionTextField.setRequired(false);
		descriptionTextField.add(StringValidator.maximumLength(1000));
		
		add(descriptionTextField);
		
		TextField<Integer> antiquityTextField = new TextField<Integer>("antiquity");
		antiquityTextField.setRequired(true);
		antiquityTextField.add(new RangeValidator<Integer>(0 , Integer.MAX_VALUE));
		
		add(antiquityTextField);	
				
		add(group);
        group.add(new CheckGroupSelector("groupselector"));
        ListView<Services> persons = new ListView<Services>("servicesList", Arrays.asList(Services.values())) {
            
            @Override
            protected void populateItem(ListItem<Services> item)
            {
                item.add(new Check<Services>("service", item.getModel()));
                item.add(new Label("serviceLabel", getLocalizer().getString(item.getModelObject().name(), this)));               
            }

        };

        persons.setReuseItems(true);
        group.add(persons);
	}

}
