package ar.edu.itba.paw.grupo1.web.Brokers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.repository.UserRepository;
import ar.edu.itba.paw.grupo1.service.ImageResource;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.Query.UserPropertiesPage;

@SuppressWarnings("serial")
public class BrokersPage extends BasePage{

	@SpringBean
	private UserRepository users;
	
	@SpringBean
	private PropertyRepository properties;
	
	public BrokersPage() {

		add(new RefreshingView<User>("brokersList") {
			
			@Override
			protected Iterator<IModel<User>> getItemModels() {
				ArrayList<IModel<User>> res = new ArrayList<IModel<User>>();
				List<User> brokers = users.getBrokers();
				for (User user : brokers) {
									
					res.add(new EntityModel<User>(User.class, user));
				}
				return res.iterator();
			}

			@Override
			protected void populateItem(Item<User> item) {
				
				final User broker = item.getModelObject();
				
				NonCachingImage logo = new NonCachingImage("brokerIcon", new AbstractReadOnlyModel() {
					
					@Override 
					public Object getObject() { 
						return new ImageResource(broker.getPhoto(), broker.getLogoExtension()); 
					} 

				});
				
				item.add(logo);
								
				item.add(new Label("brokerName", broker.getName()));
				Link<User> link = new Link<User>("brokerProperties", new EntityModel<User>(User.class, broker)) {

					@Override
					public void onClick() {
						setResponsePage(new UserPropertiesPage(getModelObject()));					
					}
				};
				link.add(new Label("label", properties.getProperties(broker).size() + " properties"));
				item.add(link);				
			}
		});
	}
}
