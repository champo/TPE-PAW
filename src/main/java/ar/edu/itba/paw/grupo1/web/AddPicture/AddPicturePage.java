package ar.edu.itba.paw.grupo1.web.AddPicture;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import ar.edu.itba.paw.grupo1.model.EntityModel;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.web.WicketSession;
import ar.edu.itba.paw.grupo1.web.Base.BasePage;

@SuppressWarnings("serial")
@AuthorizeInstantiation(WicketSession.USER)
public class AddPicturePage extends BasePage {

	public AddPicturePage(Property property) {
		EntityModel<Property> propertyModel = new EntityModel<Property>(Property.class, property);
		
	}

}
