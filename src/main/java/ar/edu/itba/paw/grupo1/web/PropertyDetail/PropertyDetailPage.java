package ar.edu.itba.paw.grupo1.web.PropertyDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.model.Room;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.PictureRepository;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.web.WicketUtils;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.Contact.ContactPage;
import ar.edu.itba.paw.grupo1.web.Query.QueryPage;

@SuppressWarnings("serial")
public class PropertyDetailPage extends BasePage {

	@SpringBean
	private PropertyRepository properties;
	
	@SpringBean
	private PictureRepository pictures;
	
	@SuppressWarnings("unused")
	private ArrayList<Services> servicesList;
	
	@SuppressWarnings("unused")
	private ArrayList<Room> roomsList;

	private List<Picture> picturesList;

	private Property property;
	
	public PropertyDetailPage(PageParameters pars) {
		
		property = properties.get(pars.get("id").toInt());
		User propertyOwner = property.getUser();
		property.visited();

		addLabel("reserved", "property.reserved", null, property.isReserved());
		add(new Label("propertyType", getLocalizer().getString(property.getPropertyType().toString(), this)));
		add(new Label("operationType", getLocalizer().getString(property.getOperationType().toString(), this)));
		add(new Label("address", property.getAddress()));
		add(new Label("neighbourhood", property.getNeighbourhood()));
		add(new Label("price", Double.toString(property.getPrice())));
		add(new Label("numRooms", Integer.toString(property.getNumRooms())));
		add(new Label("indoorSpace", Double.toString(property.getIndoorSpace())));
		add(new Label("outdoorSpace", Double.toString(property.getOutdoorSpace())));
		add(new Label("description", property.getDescription()));
		add(new Label("antiquity", Integer.toString(property.getAntiquity())));
		
		servicesList = new ArrayList<Services>(property.getServices());		
		add(new ListView<Services>("services", new PropertyModel<List<Services>>(this, "servicesList")) {

			@Override
			protected void populateItem(ListItem<Services> item) {
				Services service = item.getModelObject();
				item.add(new Label("label", getLocalizer().getString(service.name(), this)));
			}
		});
		
		Set<Room> rooms = property.getRooms();
		roomsList = new ArrayList<Room>(property.getRooms());
		addLabel("noRooms", rooms == null || rooms.isEmpty());
		
		ListView<Room> roomsView = new ListView<Room>("rooms", new PropertyModel<List<Room>>(this, "roomsList")) {

			@Override
			protected void populateItem(ListItem<Room> item) {
				Room room = item.getModelObject();
				item.add(new Label("label", room.getName()));
				item.add(new Label("length", Double.toString(room.getLength())));
				item.add(new Label("width", Double.toString(room.getWidth())));
			}
		};
		add(roomsView, rooms != null && !rooms.isEmpty());
				
		addLink("contactInfo", ContactPage.class, null, null, !propertyOwner.equals(getSignedInUser()));
		
		PageParameters params = new PageParameters();
		params.add("id", propertyOwner.getId());
		addLink("seeMore", QueryPage.class, params, new PropertyModel<Property>(property, "user"), true);
		
		String realEstateName = propertyOwner.getRealEstateName();
		boolean isRealEstate = realEstateName != null && !realEstateName.isEmpty();
		addLabel("realEstate", isRealEstate);
		
		String logoFilename = "logo_" + propertyOwner.getId() + propertyOwner.getLogoExtension();
		add(new Image("realEstateLogo", new ContextRelativeResource("/images/" + logoFilename)), isRealEstate);
		
		addLabel("realEstateName", realEstateName, isRealEstate);
		addGoogleMapImage();
		
		picturesList = pictures.getPictures(property);
		addLabel("propertyPictures", picturesList != null && !picturesList.isEmpty());
		
		ListView<Picture> picturesView = new ListView<Picture>("pictures", new PropertyModel<List<Picture>>(this, "picturesList")) {

			@Override
			protected void populateItem(ListItem<Picture> item) {
				
				Picture picture = item.getModelObject();
				boolean hasReservedBanner = item.getIndex() == 0 && property.isReserved();
				
				item.add(new Label("name", picture.getName()));
				addPropertyPicture(item, "firstPicture", picture, hasReservedBanner);
				addPropertyPicture(item, "picture", picture, !hasReservedBanner);
				WicketUtils.addToContainer(item, new Label("reservedBanner", getLocalizer().getString("reserved", this)), hasReservedBanner);
			}

			private void addPropertyPicture(ListItem<Picture> item, String id, Picture picture,	boolean visibilityCondition) {
				String filePath = "/images/" + picture.getId() + "" + picture.getExtension();
				WicketUtils.addToContainer(item, new Image(id, new ContextRelativeResource(filePath)), visibilityCondition);
			}
		};
		add(picturesView, picturesList != null && !picturesList.isEmpty());
		
		addLabel("noPictures", picturesList == null || picturesList.isEmpty());
		String key = property.getVisited() == 1?"visitsCounter1":"visitsCounter";
		addLabel("visitsCounter", key, new PropertyModel<Property>(this, "property"), true);
	}

	private void addLabel(String id, String label, boolean visibilityCondition) {
		add(new Label(id, label), visibilityCondition);	
	}

	private void addLabel(String id, String key, PropertyModel<Property> model, boolean visibilityCondition) {
		add(new Label(id, getLocalizer().getString(key, this, model)), visibilityCondition);				
	}

	private void addLabel(String id, boolean visibilityCondition) {
		addLabel(id, id, null, visibilityCondition);
	}

	private void addGoogleMapImage() {
		
		final String mapSource = "http://maps.googleapis.com/maps/api/staticmap?center=" + property.getAddress() 
				+ "&zoom=14&size=300x300&maptype=roadmap&markers=color:red%7C"+ property.getAddress() 
				+ "&sensor=false";
		Image dynamicImage = new NonCachingImage("googleMap");
		dynamicImage.add(new AttributeModifier("src", new AbstractReadOnlyModel<Object>() {

			@Override
			public final Object getObject() {
				return mapSource;
			}
		}));
		dynamicImage.setOutputMarkupId(true);
		add(dynamicImage);
	}

	private void addLink(String id, Class<? extends Page> clazz, PageParameters params, 
			PropertyModel<Property> model, boolean visibilityCondition) {
		
		BookmarkablePageLink<Void> link;
		if (params == null) {
			link = new BookmarkablePageLink<Void>(id, clazz); 
		} else {
			link = new BookmarkablePageLink<Void>(id, clazz, params);
		}
		link.add(new Label("label", getLocalizer().getString(id, this, model)));
		add(link, visibilityCondition);		
	}	
}
