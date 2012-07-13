package ar.edu.itba.paw.grupo1.web.pages.PropertyDetail;

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
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.model.PropertyState;
import ar.edu.itba.paw.grupo1.model.Room;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.model.User.UserType;
import ar.edu.itba.paw.grupo1.model.support.EntityModel;
import ar.edu.itba.paw.grupo1.repository.PictureRepository;
import ar.edu.itba.paw.grupo1.service.ImageResource;
import ar.edu.itba.paw.grupo1.web.WicketUtils;
import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.pages.Contact.ContactPage;
import ar.edu.itba.paw.grupo1.web.pages.Query.UserPropertiesPage;

@SuppressWarnings("serial")
public class PropertyDetailPage extends BasePage {
	
	@SpringBean
	private PictureRepository pictures;
		
	public PropertyDetailPage(Property property) {
		
		EntityModel<Property> model = new EntityModel<Property>(Property.class, property);
		setDefaultModel(model);
		Set<Room> rooms = property.getRooms();
		List<Picture> picturesList = pictures.getPictures(property);

		IModel<List<Services>> servicesModel = new LoadableDetachableModel<List<Services>>() {
			@Override
			protected List<Services> load() {
				return new ArrayList<Services>(getProperty().getServices()); 
			}
		};
		IModel<List<Room>> roomsModel = initRoomsModel(model);
		IModel<List<Picture>> picturesModel = initPicturesModel(model, pictures);
		
		addLabel("reserved", "property.reserved", null, property.isReserved());
		add(new Label("propertyType", getLocalizer().getString(property.getPropertyType().toString(), this)));
		add(new Label("operationType", getLocalizer().getString(property.getOperationType().toString(), this)));
		add(new Label("address", property.getAddress()));
		add(new Label("neighbourhood", property.getNeighbourhood()));
		add(new Label("currency", property.getCurrency().toString()));
		add(new Label("currencyPerSquare", property.getCurrency().toString()));
		add(new Label("price", Double.toString(property.getPrice())));
		add(new Label("pricePerSquare", Double.toString(property.getPrice() / 
					(property.getIndoorSpace() + property.getOutdoorSpace()))));
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
		
		addRoomsView(rooms, roomsModel);
	
		Link<Property> contactLink = new Link<Property>("contactInfo", model) {
			
			@Override
			public void onClick() {
				setResponsePage(new ContactPage(getModelObject()));
			}
		};
		contactLink.add(new Label("label", getLocalizer().getString("contactInfo", this)));
		add(contactLink, !isMine(property));	
		
		Link<User> queryLink = new Link<User>("seeMore", new EntityModel<User>(User.class, getPropertyOwner())) {
			
			@Override
			public void onClick() {
				setResponsePage(new UserPropertiesPage(getModelObject()));
			}
		};
		queryLink.add(new Label("label", getLocalizer().getString("seeMore", this, new EntityModel<User>(User.class, getPropertyOwner()))));
		add(queryLink);	
				
		String realEstateName = getPropertyOwner().getRealEstateName();
		boolean isRealEstate = getPropertyOwner().getType() == UserType.REAL_ESTATE;
		addLabel("realEstate", isRealEstate);
		
		NonCachingImage logo = new NonCachingImage("realEstateLogo", new AbstractReadOnlyModel() {
			
			@Override 
			public Object getObject() { 
				return new ImageResource(getPropertyOwner().getPhoto(), getPropertyOwner().getLogoExtension()); 
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
				addPropertyPicture(item, "firstPicture", hasReservedBanner);
				addPropertyPicture(item, "picture", !hasReservedBanner);
				WicketUtils.addToContainer(item, new Label("reservedBanner", getLocalizer().getString("reserved", this)), hasReservedBanner);
			}

			private void addPropertyPicture(final ListItem<Picture> item, String id, boolean visibilityCondition) {
				item.add(new NonCachingImage(id, 
		                new AbstractReadOnlyModel() { 
		                    @Override 
		                    public Object getObject() { 
		                        return new ImageResource(item.getModelObject().getData(), item.getModelObject().getExtension()); 
		                    } 
		                }).setVisible(visibilityCondition)); 
			}
		};
		
		add(picturesView, picturesList != null && !picturesList.isEmpty());
		
		addLabel("noPictures", picturesList == null || picturesList.isEmpty());
		add(new Label("visitsCounter", new StringResourceModel("visitsCounter", model)));
		
		addLabel("sold", "property.sold", null, isMine(property) && property.isSold());
		
		ListView<PropertyState> states = new ListView<PropertyState>("states", initStatesModel(model)) {
			
			@Override
			protected void populateItem(ListItem<PropertyState> item) {
				
				item.add(new Label("stateItem", PropertyDetailPage.this.getString("property.stateChange", item.getModel())));
			}
		};
		
		states.setVisible(isMine(property));
		add(states);
	}
	
	@Override
	protected void onBeforeRender() {
		getProperty().visited();
		
		super.onBeforeRender();
	}

	private void addGoogleMapImage(Property property) {
		
		final String mapSource = "http://maps.googleapis.com/maps/api/staticmap?center=" + property.getAddress() 
				+ "&zoom=14&size=300x300&maptype=roadmap&markers=color:red%7C"+ property.getAddress() 
				+ "&sensor=false";
		Image dynamicImage = new NonCachingImage("googleMap");
		dynamicImage.add(new AttributeModifier("src", new AbstractReadOnlyModel<Object>() {

			@Override
			public Object getObject() {
				return mapSource;
			}
		}));
		dynamicImage.setOutputMarkupId(true);
		add(dynamicImage);
	}
	
	private Property getProperty() {
		return (Property) getDefaultModelObject();
	}
	
	private User getPropertyOwner() {
		return getProperty().getUser();
	}
	
	protected IModel<List<PropertyState>> initStatesModel(final IModel<Property> model) {
		
		IModel<List<PropertyState>> picturesModel = new LoadableDetachableModel<List<PropertyState>>() {
			@Override
			protected List<PropertyState> load() {
				return model.getObject().getStates(); 
			}
		};
		return picturesModel;
	}
}
