package ar.edu.itba.paw.grupo1.web.AddRoom;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Room;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;
import ar.edu.itba.paw.grupo1.web.RoomFormPanel;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;
import ar.edu.itba.paw.grupo1.web.EditProperty.EditPropertyPage;

@SuppressWarnings("serial")
public class AddRoomPage extends BasePage {

	
	private transient String name;
	private transient double length;
	private transient double width;
	
	@SpringBean
	private PropertyRepository properties;
	
	public AddRoomPage(Property property) {
		
//		final EntityModel<Property> model = new EntityModel<Property>(Property.class, property);
		final Integer id = property.getId();
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setVisible(false);
		
		Form<AddRoomPage> form = new Form<AddRoomPage>("addPropertyForm", new CompoundPropertyModel<AddRoomPage>(this)) {
			
			@Override
			protected void onSubmit() {
				
//				Property property = model.getObject();
				Property property = properties.get(id);
				Room room = new Room(name, length, width, property);
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

}
