package ar.edu.itba.paw.grupo1.web.Brokers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.repository.UserRepository;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.Query.QueryPage;

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
				
				User broker = item.getModelObject();
				String logoFilePath = null;
				if (broker.getLogoExtension() != null && !broker.getLogoExtension().isEmpty()) {
					logoFilePath = "/images/logo_" + broker.getId() + "" + broker.getLogoExtension();
					item.add(new Image("brokerIcon", new ContextRelativeResource(logoFilePath)));
				} else {
					item.add(new Image("brokerIcon", new ContextRelativeResource(logoFilePath)).setVisible(false));
				}
				
				item.add(new Label("brokerName", broker.getName()));
				Link<User> link = new Link<User>("brokerProperties", new EntityModel<User>(User.class, broker)) {

					@Override
					public void onClick() {
						setResponsePage(new QueryPage(getModelObject()));					
					}
				};
				link.add(new Label("label", properties.getProperties(broker).size() + " properties"));
				item.add(link);				
			}
		});
	}
}
