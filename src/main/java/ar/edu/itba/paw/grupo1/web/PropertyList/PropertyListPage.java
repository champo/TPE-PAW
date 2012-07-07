package ar.edu.itba.paw.grupo1.web.PropertyList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.WicketUtils;
import ar.edu.itba.paw.grupo1.web.AddProperty.AddPropertyPage;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.EditProperty.EditPropertyPage;
import ar.edu.itba.paw.grupo1.web.PropertyDetail.PropertyDetailPage;

@SuppressWarnings("serial")
public class PropertyListPage extends BasePage {

	@SpringBean
	private PropertyRepository properties;

	public PropertyListPage() {

		List<Property> propertyList = properties.getProperties(getSignedInUser());
		
		add(new BookmarkablePageLink<Void>("newProperty", AddPropertyPage.class));
		add(new Label("noProperties"), propertyList == null || propertyList.isEmpty());

		add(new RefreshingView<Property>("properties") {

			@Override
			protected Iterator<IModel<Property>> getItemModels() {
				List<IModel<Property>> result = new ArrayList<IModel<Property>>();
				for (Property prop : properties.getProperties(getSignedInUser())) {
					result.add(new EntityModel<Property>(Property.class, prop));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(Item<Property> item) {

				Property property = item.getModelObject();
				
				Link<Property> detailLink = new Link<Property>("detail", item.getModel()) {
					
				     public void onClick() {
				          setResponsePage(new PropertyDetailPage(getModel()));
				     }
				};
				detailLink.add(new Label("id", property.getId().toString()));
				item.add(detailLink);
				item.add(new Label("description", property.getDescription()));
				
				Link<Property> editLink = new Link<Property>("edit", item.getModel()) {
					
				     public void onClick() {
				          setResponsePage(new EditPropertyPage(getModelObject()));
				     }
				};
				item.add(editLink);
				
				Link<Property> publishLink = new Link<Property>("publish", item.getModel()) {

					@Override
					public void onClick() {
						getModelObject().publish();
					}
				};
				WicketUtils.addToContainer(item, publishLink, !property.isPublished());
				
				Link<Property> unpublishLink = new Link<Property>("unpublish", item.getModel()) {

					@Override
					public void onClick() {
						getModelObject().unpublish();
					}
				};
				WicketUtils.addToContainer(item, unpublishLink, property.isPublished());
				
				Link<Property> reserveLink = new Link<Property>("reserve", item.getModel()) {

					@Override
					public void onClick() {
						getModelObject().reserve();
					}
				};
				WicketUtils.addToContainer(item, reserveLink, !property.isReserved());
				
				Link<Property> unreservelink = new Link<Property>("unreserve", item.getModel()) {

					@Override
					public void onClick() {
						getModelObject().unreserve();
					}
				};
				WicketUtils.addToContainer(item, unreservelink, property.isReserved());							
			}
		});
	}
}
