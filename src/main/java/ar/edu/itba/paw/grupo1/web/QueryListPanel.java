package ar.edu.itba.paw.grupo1.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.PropertyDetail.PropertyDetailPage;

@SuppressWarnings("serial")
public class QueryListPanel extends Panel{

	private static final int ITEMSPERPAGE = 10;
	
	@SpringBean
	private transient PropertyRepository properties;
	
	public QueryListPanel(String id, PropertyQuery propertyQuery) {
		super(id);
		addQueryResultsView(new PropertytDataProvider(propertyQuery, properties));
	}
	
	private void addQueryResultsView(IDataProvider<Property> propertytDataProvider) {
		
		DataView<Property> dataView = new DataView<Property>("queryResults", propertytDataProvider) {

	        @Override
	        protected void populateItem(final Item<Property> item)
	        {
				Property property = item.getModelObject();
	        	item.add(new Label("operationType", getLocalizer().getString(property.getOperationType().toString(), QueryListPanel.this)));				
				item.add(new Label("propertyType", getLocalizer().getString(item.getModelObject().getPropertyType().toString(), QueryListPanel.this)));
				addCustomLabel("neighbourhood", property.getNeighbourhood(), item);
				addCustomLabel("price", Double.toString(property.getPrice()), item);
				addCustomLabel("address", property.getAddress(), item);
				
				Link<Property> detailLink = new Link<Property>("seeMore", item.getModel()) {
					
				     public void onClick() {
				          setResponsePage(new PropertyDetailPage(getModelObject()));
				     }
				};
				detailLink.add(new Label("seeMoreLabel", getLocalizer().getString("seeMore", QueryListPanel.this)));
				item.add(detailLink);
	        }
	        
			private void addCustomLabel(String id, String value, Item<Property> item) {
				String prefix = getLocalizer().getString(id, QueryListPanel.this);
				item.add(new Label(id, prefix + ": " + value));
			}
	    };
	
	    dataView.setItemsPerPage(ITEMSPERPAGE);
	    add(dataView);
	    add(new PagingNavigator("navigator", dataView));
		
	}

	public QueryListPanel(String id, User user) {
		super(id);
		addQueryResultsView(new UserPropertytDataProvider(user, properties));
	}
}
