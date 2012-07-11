package ar.edu.itba.paw.grupo1.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.PropertyDetail.PropertyDetailPage;

@SuppressWarnings("serial")
public class QueryListPanel extends Panel{

	@SpringBean
	private PropertyRepository properties;
	private transient PropertyQuery propertyQuery;
	private transient int resultsPerPage;
	private transient User user;
	
	public QueryListPanel(String id, final PropertyQuery propertyQuery, final int resultsPerPage) {
		super(id);
		
		this.propertyQuery = propertyQuery;
		this.resultsPerPage = resultsPerPage; 
		
		addQueryResultsView();
	}
	
	public QueryListPanel(String id, User user) {
		super(id);
		this.user = user;
		addQueryResultsView();
	}
	

	private void addQueryResultsView() {
		RefreshingView<Property> queryResultsView = new RefreshingView<Property>("queryResults") {

			@Override
			protected Iterator<IModel<Property>> getItemModels() {
				
				List<Property> list = getPropertyList();
				List<IModel<Property>> res = new ArrayList<IModel<Property>>();
				for (Property prop: list) {
					res.add(new EntityModel<Property>(Property.class, prop));
				}
				return res.iterator();
			}

			@Override
			protected void populateItem(Item<Property> item) {
				
				item.add(new Label("operationType", getLocalizer().getString(item.getModelObject().getOperationType().toString(), QueryListPanel.this)));				
				item.add(new Label("propertyType", getLocalizer().getString(item.getModelObject().getPropertyType().toString(), QueryListPanel.this)));
				addCustomLabel("neighbourhood", item.getModelObject().getNeighbourhood(), item);
				addCustomLabel("price", Double.toString(item.getModelObject().getPrice()), item);
				addCustomLabel("address", item.getModelObject().getAddress(), item);
				
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
		add(queryResultsView);
	}
	
	private List<Property> getPropertyList() {
		if (user != null) {
			return properties.getListedProperties(user);
		}
		return properties.query(propertyQuery, resultsPerPage).getList();
	}
}
