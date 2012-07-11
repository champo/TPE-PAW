package ar.edu.itba.paw.grupo1.web.Query;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.dto.PaginatedList;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.OperationType;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.OrderType;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery.PropertyType;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.QueryListPanel;
import ar.edu.itba.paw.grupo1.web.WicketUtils;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
public class QueryPage extends BasePage {

	private transient Integer lastPage;
	private transient Integer paginationFrom;
	private transient Integer paginationTo;

	private transient Integer resultsPerPage = 10;

	private transient List<Integer> previousPages = new ArrayList<Integer>();
	private transient List<Integer> nextPages = new ArrayList<Integer>();
	private transient PropertyQuery propertyQuery;
	
	@SpringBean
	private PropertyRepository properties;
	
	public QueryPage() {
		this(1, null, null, null);
	}
	
	public QueryPage(boolean unpublished) {
		//show that the property is no longer published, the user cannot acces it
	}

	public QueryPage(final Integer pagenumber, PropertyQuery propertyQuery, Integer from, Integer to) {

		addLabel("unpublished",false);
		if (propertyQuery == null) {
			propertyQuery = new PropertyQuery();
		}
		this.propertyQuery = propertyQuery;
		paginationFrom = from;
		paginationTo = to;
		previousPages = new ArrayList<Integer>();
		if (paginationFrom != null) {
			for (int i = paginationFrom; i < pagenumber; i++) {
				previousPages.add(i);
			}
		}
		nextPages = new ArrayList<Integer>();
		if (paginationTo != null) {
			for (int i = pagenumber + 1; i < paginationTo + 1; i++) {
				nextPages.add(i);
			}
		}
		
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		
		Form<PropertyQuery> form = new Form<PropertyQuery>("queryForm", new CompoundPropertyModel<PropertyQuery>(propertyQuery)) {
			
			@Override
			protected void onSubmit() {
				
				PaginatedList paginatedList = properties.query(getModelObject(), resultsPerPage);
				lastPage = paginatedList.getLastPage();
				
				int pagenumber = getModelObject().getPageNumber();
				paginationFrom = Math.max(1, getModelObject().getPageNumber() - 5);
				paginationTo = Math.min(paginatedList.getLastPage(), getModelObject().getPageNumber() + 4);
				
				feedbackPanel.setVisible(false);
				setResponsePage(new QueryPage(pagenumber, getModelObject(), paginationFrom, paginationTo));
			}

			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		
		WicketUtils.addRadioChoice(form, "property", PropertyType.values());
		WicketUtils.addRadioChoice(form, "operation", OperationType.values());
		
		form.add(new TextField<Double>("rangeFrom"));
		form.add(new TextField<Double>("rangeTo"));
		WicketUtils.addDropDownMenu(form, "order", OrderType.values());

		form.add(feedbackPanel);		
		form.add(new Button("submit", new ResourceModel("submit")));
		add(form);

		QueryListPanel queryListPanel = new QueryListPanel("queryListPanel", propertyQuery, resultsPerPage);
		add(queryListPanel, queryListPanel.size() != 0);
		
		addPageLink("firstPage", pagenumber != 1, pagenumber);
		addPageLink("previousPage", pagenumber != 1, pagenumber - 1);
		
		add(new ListView<Integer>("previousPages", new PropertyModel<List<Integer>>(this, "previousPages")) {
			
			@Override
			protected void populateItem(final ListItem<Integer> item) {
				
				Link<Integer> link = new Link<Integer>("page") {
					
					@Override
					public void onClick() {
						setResponsePage(new QueryPage(item.getModelObject(), propertyQuery(), paginationFrom, paginationTo));
					}
				};
				addLabelToLink(link, item.getModelObject().toString());
				item.add(link);
			}
		});
		
		Link<PropertyQuery> currentPageLink = new Link<PropertyQuery>("currentPage") {

			@Override
			public void onClick() {	
				
			}
		};
		addLabelToLink(currentPageLink, pagenumber.toString());
		add(currentPageLink);
		
		
		add(new ListView<Integer>("nextPages", new PropertyModel<List<Integer>>(this, "nextPages")) {
			@Override
			protected void populateItem(final ListItem<Integer> item) {
				
				Link<Integer> link = new Link<Integer>("page") {
					
					@Override
					public void onClick() {
						setResponsePage(new QueryPage(item.getModelObject(), propertyQuery(), paginationFrom, paginationTo));
					}
				};
				addLabelToLink(link, item.getModelObject().toString());
				item.add(link);
			}
		});
		
		addPageLink("lastPage", pagenumber != lastPage, lastPage);
		addPageLink("nextPage", pagenumber != lastPage, pagenumber + 1);
	}

	private void addPageLink(String id, final boolean condition, final Integer pagenumber) {

		WebMarkupContainer liTag = new WebMarkupContainer(id);
		Link<PropertyQuery> link = new Link<PropertyQuery>("link") {

			@Override
			public void onClick() {
				if (condition) {
					setResponsePage(new QueryPage(pagenumber, propertyQuery(), paginationFrom, paginationTo));
				}
			}
		};
		
		if (!condition) {
			liTag.add(new AttributeModifier("class", "disabled"));
		}
		liTag.add(link);
		addLabelToLink(link, getLocalizer().getString(id, this));
		add(liTag);
	}
	
	private PropertyQuery propertyQuery() {
		return propertyQuery;
	}
}
