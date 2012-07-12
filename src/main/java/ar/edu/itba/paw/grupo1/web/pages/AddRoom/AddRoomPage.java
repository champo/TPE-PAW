package ar.edu.itba.paw.grupo1.web.pages.AddRoom;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Room;
import ar.edu.itba.paw.grupo1.model.Room.RoomsType;
import ar.edu.itba.paw.grupo1.model.support.EntityModel;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.web.RoomFormPanel;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.pages.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.pages.EditProperty.EditPropertyPage;

@SuppressWarnings("serial")
@AuthorizeInstantiation(WicketSession.USER)
public class AddRoomPage extends BasePage {

	
	private transient RoomsType roomsCombo;
	private transient double length;
	private transient double width;
	
	public AddRoomPage(Property property) {
		
		setDefaultModel(new EntityModel<Property>(Property.class, property));
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		
		Form<AddRoomPage> form = new Form<AddRoomPage>("addPropertyForm", new CompoundPropertyModel<AddRoomPage>(this)) {
			
			@Override
			protected void onSubmit() {
				
				Property property = getProperty();
				Room room = new Room(roomsCombo, length, width, property);
				if (!isMine(property)) {
					throw new PermissionDeniedException();
				}
				property.addRoom(room);
				
				setResponsePage(new EditPropertyPage(property));
			}

			@Override
			protected void onError() {
				super.onError();
				feedbackPanel.setVisible(true);
			}
		};
		form.add(new RoomFormPanel("roomFormPanel"));
		form.add(feedbackPanel);		
		form.add(new Button("submit", new ResourceModel("Submit")));
		add(form);
	}

	protected Property getProperty() {
		return (Property) getDefaultModelObject();
	}

	
}
