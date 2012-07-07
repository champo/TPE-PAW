package ar.edu.itba.paw.grupo1.web.AddPicture;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
public class AddPicturePage extends BasePage {

	public AddPicturePage(Property property) {
		EntityModel<Property> propertyModel = new EntityModel<Property>(Property.class, property);
		
	}

}
