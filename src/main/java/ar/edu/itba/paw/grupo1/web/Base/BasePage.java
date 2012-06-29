package ar.edu.itba.paw.grupo1.web.Base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.ContextRelativeResource;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.web.Brokers.BrokersPage;
import ar.edu.itba.paw.grupo1.web.HelloWorld.HelloWorldPage;
import ar.edu.itba.paw.grupo1.web.Home.HomePage;
import ar.edu.itba.paw.grupo1.web.Login.LoginPage;
import ar.edu.itba.paw.grupo1.web.PropertyList.PropertyListPage;
import ar.edu.itba.paw.grupo1.web.Query.QueryPage;

@SuppressWarnings("serial")
public class BasePage extends WebPage {

	private List<Link<Void>> sectionsList = new ArrayList<Link<Void>>();

	
	public BasePage() {
		initLinks();

		BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>("logo", HomePage.class);
		link.add(new Image("search_icon", new ContextRelativeResource("/images/arqvengers.png")));
		add(link);
		
		add(new BookmarkablePageLink<Void>("base_login_link", LoginPage.class));
		add(new BookmarkablePageLink<Void>("base_logout_link", HomePage.class));
		add(new Label("username", "alvaneitor"));
		
		
		add(new ListView<Link<Void>>("link_list", new PropertyModel<List<Link<Void>>>(this, "sectionsList")) {
			@Override
			protected void populateItem(ListItem<Link<Void>> item) {
				item.add(item.getModelObject());
			}
		});
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	private void initLinks() {
		
		Class<?> pages[] = {HelloWorldPage.class , HomePage.class, PropertyListPage.class, QueryPage.class, BrokersPage.class};
		Map<String, String> labels = new HashMap<String, String>();
		labels.put("HelloWorldPage", "HelloWorld");
		labels.put("HomePage", "Home");
		labels.put("PropertyListPage", "My properties");
		labels.put("QueryPage", "Search properties");
		labels.put("BrokersPage", "Brokers");
		
		for(Class<?> page: pages) {
			BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>("link", (Class<? extends Page>)page);
			link.add(new Label("label", labels.get(page.getSimpleName())));
			sectionsList.add(link);
		}
	}

	public void renderHead(IHeaderResponse response) {
//	    response.renderCSSReference(new PackageResourceReference(BasePage.class,
//	      "main.css"));
	    response.renderCSSReference("css/bootstrap.min.css");
	}
	
	protected void rememberName(HttpServletResponse resp, User user) {
		
	}
	
	protected void rememberUser(HttpServletResponse resp, User user) {
		
	}
}
