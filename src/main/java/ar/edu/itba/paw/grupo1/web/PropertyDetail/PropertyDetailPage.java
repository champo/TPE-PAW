package ar.edu.itba.paw.grupo1.web.PropertyDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.model.Room;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.model.User.UserType;
import ar.edu.itba.paw.grupo1.repository.PictureRepository;
import ar.edu.itba.paw.grupo1.service.ImageResource;
import ar.edu.itba.paw.grupo1.web.WicketUtils;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.Contact.ContactPage;
import ar.edu.itba.paw.grupo1.web.Query.UserPropertiesPage;

@SuppressWarnings("serial")
public class PropertyDetailPage extends BasePage {
	
	@SpringBean
	private PictureRepository pictures;
	
	public PropertyDetailPage(Property property) {
		
		final EntityModel<Property> model = new EntityModel<Property>(Property.class, property);
		final User propertyOwner = property.getUser();
		Set<Room> rooms = property.getRooms();
		List<Picture> picturesList = pictures.getPictures(property);

		property.visited();

		IModel<List<Services>> servicesModel = new LoadableDetachableModel<List<Services>>() {
			@Override
			protected List<Services> load() {
				return new ArrayList<Services>(model.getObject().getServices()); 
			}
		};
		IModel<List<Room>> roomsModel = initRoomsModel(model);
		IModel<List<Picture>> picturesModel = initPicturesModel(model, pictures);
		
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
		
		add(new ListView<Services>("services", servicesModel) {

			@Override
			protected void populateItem(ListItem<Services> item) {
				Services service = item.getModelObject();
				item.add(new Label("label", getLocalizer().getString(service.name(), this)));
			}
		});
		
		addLabel("noRooms", rooms == null || rooms.isEmpty());
		
		ListView<Room> roomsView = new ListView<Room>("rooms", roomsModel) {

			@Override
			protected void populateItem(ListItem<Room> item) {
				Room room = item.getModelObject();
				item.add(new Label("label", room.getName()));
				item.add(new Label("length", Double.toString(room.getLength())));
				item.add(new Label("width", Double.toString(room.getWidth())));
			}
		};
		add(roomsView, rooms != null && !rooms.isEmpty());
				
		Link<Property> contactLink = new Link<Property>("contactInfo", model) {
			
			@Override
			public void onClick() {
				setResponsePage(new ContactPage(getModelObject()));
			}
		};
		contactLink.add(new Label("label", getLocalizer().getString("contactInfo", this)));
		add(contactLink, !isMine(property));	
		
		Link<User> queryLink = new Link<User>("seeMore", new EntityModel<User>(User.class, propertyOwner)) {
			
			@Override
			public void onClick() {
				setResponsePage(new UserPropertiesPage(getModelObject()));
			}
		};
		queryLink.add(new Label("label", getLocalizer().getString("seeMore", this, new PropertyModel<Property>(property, "user"))));
		add(queryLink);	
				
		String realEstateName = propertyOwner.getRealEstateName();
		boolean isRealEstate = propertyOwner.getType() == UserType.REAL_ESTATE;
		addLabel("realEstate", isRealEstate);
		
		NonCachingImage logo = new NonCachingImage("realEstateLogo", new AbstractReadOnlyModel() {
			
			@Override 
			public Object getObject() { 
				return new ImageResource(propertyOwner.getPhoto(), propertyOwner.getLogoExtension()); 
			} 

		});
		
		logo.setVisible(isRealEstate);
		add(logo);
		
		
		addLabel("realEstateName", realEstateName, isRealEstate);
		addGoogleMapImage(property);
		
		addLabel("propertyPictures", picturesList != null && !picturesList.isEmpty());
		
		ListView<Picture> picturesView = new ListView<Picture>("pictures", picturesModel) {

			@Override
			protected void populateItem(ListItem<Picture> item) {
				
				Picture picture = item.getModelObject();
				boolean hasReservedBanner = item.getIndex() == 0 && picture.getProperty().isReserved();
				
				item.add(new Label("name", picture.getName()));
				addPropertyPicture(item, "firstPicture", picture, hasReservedBanner);
				addPropertyPicture(item, "picture", picture, !hasReservedBanner);
				WicketUtils.addToContainer(item, new Label("reservedBanner", getLocalizer().getString("reserved", this)), hasReservedBanner);
			}

			private void addPropertyPicture(ListItem<Picture> item, String id, final Picture picture,	boolean visibilityCondition) {
				item.add(new NonCachingImage(id, 
		                new AbstractReadOnlyModel() { 
		                    @Override 
		                    public Object getObject() { 
		                        // TODO Auto-generated method stub 
		                        return new ImageResource(picture.getData(), picture.getExtension()); 
		                    } 
		                }).setVisible(visibilityCondition)); 
			}
		};
		
		add(picturesView, picturesList != null && !picturesList.isEmpty());
		
		addLabel("noPictures", picturesList == null || picturesList.isEmpty());
		String key = property.getVisited() == 1?"visitsCounter1":"visitsCounter";
		addLabel("visitsCounter", key, model, true);
		
		addLabel("sold", "property.sold", null, isMine(property) && property.isSold());
	}

	private void addLabel(String id, String label, boolean visibilityCondition) {
		add(new Label(id, label), visibilityCondition);	
	}

	private void addLabel(String id, String key, IModel<Property> model, boolean visibilityCondition) {
		add(new Label(id, getLocalizer().getString(key, this, model)), visibilityCondition);				
	}

	private void addLabel(String id, boolean visibilityCondition) {
		addLabel(id, id, null, visibilityCondition);
	}

	private void addGoogleMapImage(Property property) {
		
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
}
