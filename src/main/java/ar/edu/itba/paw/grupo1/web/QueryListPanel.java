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
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.PropertyDetail.PropertyDetailPage;

@SuppressWarnings("serial")
public class QueryListPanel extends Panel{

	@SpringBean
	private PropertyRepository properties;
	protected List<Property> list;
	
	public QueryListPanel(String id, final PropertyQuery propertyQuery, final int resultsPerPage) {
		super(id);
		
		RefreshingView<Property> queryResultsView = new RefreshingView<Property>("queryResults") {

			@Override
			protected Iterator<IModel<Property>> getItemModels() {
				
				list = properties.query(propertyQuery, resultsPerPage).getList();
				List<IModel<Property>> res = new ArrayList<IModel<Property>>();
				for (Property prop: list) {
					res.add(new EntityModel<Property>(Property.class, prop));
				}
				return res.iterator();
			}

			@Override
			protected void populateItem(Item<Property> item) {
				// TODO Auto-generated method stub
				
				
				Link<Property> detailLink = new Link<Property>("seeMore", item.getModel()) {
					
				     public void onClick() {
				          setResponsePage(new PropertyDetailPage(getModelObject()));
				     }
				};
				detailLink.add(new Label("seeMoreLabel", getLocalizer().getString("seeMore", this)));
				item.add(detailLink);
				
			}
		};
		add(queryResultsView);
	}
	
	@Override
	public boolean isVisible() {
		return !list.isEmpty();
	}

}
