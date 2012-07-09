package ar.edu.itba.paw.grupo1.web.Query;


import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.web.QueryListPanel;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
public class QueryPage extends BasePage {


	private transient Integer paginationFrom;
	private transient Integer paginationto;
	private transient Integer lastPage;
	private transient Integer resultsPerPage = 10;

	public QueryPage() {
		// TODO Auto-generated constructor stub
	}

	public QueryPage(boolean unpublished) {
		// TODO Auto-generated constructor stub
		//show that the property is no longer published, the user cannot acces it
	}

	public QueryPage(final Integer pagenumber) {
		
		
		
		PropertyQuery propertyQuery = new PropertyQuery();
		QueryListPanel queryListPanel = new QueryListPanel("queryListPanel", propertyQuery, resultsPerPage);
		add(queryListPanel, queryListPanel.isVisible());
		
		
		Link<PropertyQuery> firstPageLink = new Link<PropertyQuery>("firstPage") {

			@Override
			public void onClick() {
				if (pagenumber != 1) {
					setResponsePage(new QueryPage(pagenumber));
				}
			}
		};
		Link<PropertyQuery> previousPageLink = new Link<PropertyQuery>("previousPage") {

			@Override
			public void onClick() {
				if (pagenumber != 1) {
					setResponsePage(new QueryPage(pagenumber - 1));
				}
			}
		};
		if (pagenumber == 1) { 
			previousPageLink.getParent().add(new AttributeModifier("class", "disabled"));
			firstPageLink.getParent().add(new AttributeModifier("class", "disabled"));
		}
		
		add(new ListView<Integer>("previousPages", new PropertyModel<List<Integer>>(this, "previousPages")) {
			@Override
			protected void populateItem(final ListItem<Integer> item) {
				
				Link<Integer> link = new Link<Integer>("page") {
					
					@Override
					public void onClick() {
						setResponsePage(new QueryPage(item.getModelObject()));
					}
				};
				item.add(link);
			}
		});
		
		Link<PropertyQuery> currentPageLink = new Link<PropertyQuery>("currentPage") {

			@Override
			public void onClick() {	
				
			}
		};
		currentPageLink.getParent().add(new AttributeModifier("class", "active"));
		
		add(new ListView<Integer>("nextPages", new PropertyModel<List<Integer>>(this, "nextPages")) {
			@Override
			protected void populateItem(final ListItem<Integer> item) {
				
				Link<Integer> link = new Link<Integer>("page") {
					
					@Override
					public void onClick() {
						setResponsePage(new QueryPage(item.getModelObject()));
					}
				};
				item.add(link);
			}
		});
		
		
		Link<PropertyQuery> lastPageLink = new Link<PropertyQuery>("lastPage") {

			@Override
			public void onClick() {
				if (pagenumber != lastPage) {
					setResponsePage(new QueryPage(lastPage));
				}
			}
		};
		
		Link<PropertyQuery> nextPageLink = new Link<PropertyQuery>("nextPage") {

			@Override
			public void onClick() {
				if (pagenumber != lastPage) {
					setResponsePage(new QueryPage(pagenumber + 1));
				}
			}
		};
		if (pagenumber == lastPage) { 
			lastPageLink.getParent().add(new AttributeModifier("class", "disabled"));
			nextPageLink.getParent().add(new AttributeModifier("class", "disabled"));
		}
		
		
		
		
		
		
		
		
		
		
		
	}
}
