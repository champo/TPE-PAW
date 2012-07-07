package ar.edu.itba.paw.grupo1.web.EditPicture;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
public class EditPicturePage extends BasePage {

	public EditPicturePage(Property property, Picture picture) {
		
		EntityModel<Property> propertyModel = new EntityModel<Property>(Property.class, property);
		EntityModel<Picture> pictureModel = new EntityModel<Picture>(Picture.class, picture);

	}

}
