package ar.edu.itba.paw.grupo1.web.Brokers;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;

import ar.edu.itba.paw.grupo1.web.BrokerData;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.Query.QueryPage;

@SuppressWarnings("serial")
public class BrokersPage extends BasePage{

	private List<BrokerData> brokersList = new ArrayList<BrokerData>();

	
	public BrokersPage() {

		getBrokers();
		add(new ListView<BrokerData>("brokersList", new PropertyModel<List<BrokerData>>(this, "brokersList")) {
			@Override
			protected void populateItem(ListItem<BrokerData> item) {
				BrokerData broker = item.getModelObject();
				if (broker.getLogoExtension() != null && !broker.getLogoExtension().isEmpty()) {
					item.add(new Image("brokerIcon", new ContextRelativeResource("/images/arqvengers.png")));
				} else {
					item.add(new Image("brokerIcon", new ContextRelativeResource("/images/arqvengers.png")).setVisible(false));
				}
				item.add(new Label("brokerName", broker.getName()));
				PageParameters pars = new PageParameters();
				pars.add("user", broker.getId());
				BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>("brokerProperties", QueryPage.class, pars);
				link.add(new Label("label", broker.getProperties() + " properties"));
				item.add(link);
			}
		});
	}


	private void getBrokers() {
		
		brokersList.add(new BrokerData(1, "ALFPROP", ".png", 4));
		brokersList.add(new BrokerData(2, "ARGENPROP", ".png", 4));
		brokersList.add(new BrokerData(3, "MAMAMIA!", null, 8));
		brokersList.add(new BrokerData(4, "Peluqeria La Paloma", "", 6));
	}
}
