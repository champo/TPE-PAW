package ar.edu.itba.paw.grupo1.web.EditProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.model.Room;
import ar.edu.itba.paw.grupo1.repository.PictureRepository;
import ar.edu.itba.paw.grupo1.service.ImageResource;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.web.PropertyFormPanel;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.AddPicture.AddPicturePage;
import ar.edu.itba.paw.grupo1.web.AddRoom.AddRoomPage;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.EditPicture.EditPicturePage;
import ar.edu.itba.paw.grupo1.web.PropertyList.PropertyListPage;

@SuppressWarnings("serial")
@AuthorizeInstantiation(WicketSession.USER)
public class EditPropertyPage extends BasePage{
	
	@SpringBean
	private PictureRepository pictures;
	
	public EditPropertyPage(Property property) {
		
		EntityModel<Property> model = new EntityModel<Property>(Property.class, property);
		Set<Room> rooms = model.getObject().getRooms();
		List<Picture> picturesList = pictures.getPictures(model.getObject());
		
		IModel<List<Room>> roomsModel = initRoomsModel(model);
		IModel<List<Picture>> picturesModel = initPicturesModel(model, pictures);
		
		add(new Label("page.subtitle", getString("page.subtitle", model)));
		
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
        final CheckGroup<Services> group = new CheckGroup<Services>("group", new ArrayList<Services>(property.getServices()));

		Form<Property> form = new Form<Property>("editPropertyForm", new CompoundPropertyModel<Property>(model)) {
			
			@Override
			protected void onSubmit() {
				if (!isMine(getModelObject())) {
					throw new PermissionDeniedException();
				}
				getModelObject().setServices(new HashSet<Services>(group.getModelObject()));
				setResponsePage(PropertyListPage.class);
			}

			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		form.add(new PropertyFormPanel("propertyFormPanel", Arrays.asList(Services.values()), group));
		form.add(feedbackPanel);		
		form.add(new Button("submit", new ResourceModel("Submit")));
		add(form);
		
		addRoomSection(model, rooms, roomsModel);
		addPicturesSection(model, picturesList, picturesModel);
	}

	private void addRoomSection(EntityModel<Property> model, Set<Room> rooms, IModel<List<Room>> roomsModel) {
		Link<Property> addRoomLink = new Link<Property>("addRoom", model) {
			
		     public void onClick() {
		          setResponsePage(new AddRoomPage(getModelObject()));
		     }
		};
		add(addRoomLink);
		
		addRoomsView(rooms, roomsModel);
	}

	private void addPicturesSection(final EntityModel<Property> model,
			List<Picture> picturesList, IModel<List<Picture>> picturesModel) {
		Link<Property> addPictureLink = new Link<Property>("addPicture") {
			
		     public void onClick() {
		          setResponsePage(new AddPicturePage(model.getObject()));
		     }
		};
		add(addPictureLink);
		
		ListView<Picture> picturesView = new ListView<Picture>("pictures", picturesModel) {

			@Override
			protected void populateItem(final ListItem<Picture> item) {
				
				Picture picture = item.getModelObject();				
				item.add(new Label("name", picture.getName()));
				addPropertyPicture(item, "picture", picture, true);
				Link<Property> editPictureLink = new Link<Property>("editDelete") {
					
				     public void onClick() {
				          setResponsePage(new EditPicturePage(model.getObject(), item.getModelObject()));
				     }
				};
				item.add(editPictureLink);
			}

			private void addPropertyPicture(ListItem<Picture> item, String id, Picture picture, boolean visibilityCondition) {
				
				final byte[] data = picture.getData();
				final String extension = picture.getExtension();
				AbstractReadOnlyModel imageModel = new AbstractReadOnlyModel() { 
					
				    @Override 
				    public Object getObject() { 
				        
						return new ImageResource(data, extension); 
				    } 
				};
				item.add(new NonCachingImage("picture", imageModel)); 
			}
		};
		add(picturesView, picturesList != null && !picturesList.isEmpty());
	}
}
