package ar.edu.itba.paw.grupo1.web;

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

import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Services;

@SuppressWarnings("serial")
public class PropertyFormPanel extends Panel{

	public PropertyFormPanel(String id, List<Services> servicesList, CheckGroup<Services> group) {
		super(id);
				
		WicketUtils.addDropDownMenu(this, "propertyType", new PropertyType[] {PropertyType.HOUSE, PropertyType.FLAT});
		WicketUtils.addDropDownMenu(this, "operationType", new OperationType[] {OperationType.SELLING, OperationType.LEASING});

		add(new TextField<String>("address").setRequired(true));
		add(new TextField<String>("neighbourhood").setRequired(true));
		add(new TextField<Double>("price").setRequired(true));
		add(new TextField<Integer>("numRooms").setRequired(true));
		add(new TextField<Double>("indoorSpace").setRequired(true));
		add(new TextField<Double>("outdoorSpace").setRequired(true));
		add(new TextArea<String>("description").setRequired(false));
		add(new TextField<Integer>("antiquity").setRequired(true));	
				
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
