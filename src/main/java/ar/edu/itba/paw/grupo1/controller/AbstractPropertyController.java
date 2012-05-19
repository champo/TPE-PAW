package ar.edu.itba.paw.grupo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.web.PropertyForm;

@Controller
public class AbstractPropertyController extends BaseController {

	public AbstractPropertyController() {
		super();
	}
	
	protected Property buildProperty(int id, PropertyForm propertyForm, User user) {	
		//Remember published defaults to true. Then MAY change according to the db representation
		return new Property(id, propertyForm.getPropertyType(), propertyForm.getOperationType(), propertyForm.getAddress(), 
				propertyForm.getNeighbourhood(), propertyForm.getPrice(), propertyForm.getRooms(), propertyForm.getIndoorSpace(), 
				propertyForm.getOutdoorSpace(), propertyForm.getDescription(), propertyForm.getAntiquity(), 
				propertyForm.getServices(), true, user); 
	}
	
	protected Property buildProperty(PropertyForm propertyForm, User user) {
		//Remember published defaults to true. Then MAY change according to the db representation
		return new Property(propertyForm.getPropertyType(), propertyForm.getOperationType(), propertyForm.getAddress(), 
				propertyForm.getNeighbourhood(), propertyForm.getPrice(), propertyForm.getRooms(), propertyForm.getIndoorSpace(), 
				propertyForm.getOutdoorSpace(), propertyForm.getDescription(), propertyForm.getAntiquity(), 
				propertyForm.getServices(), true, user);
	} 
	
	protected void setPropertyAttributes(ModelAndView mav, Property property, PropertyForm propertyForm) {
		
		propertyForm.setPropertyType(property.getPropertyType());
		propertyForm.setOperationType(property.getOperationType());
		propertyForm.setAddress(property.getAddress());
		propertyForm.setNeighbourhood(property.getNeighbourhood());
		propertyForm.setPrice(property.getPrice());
		propertyForm.setRooms(property.getRooms());
		propertyForm.setIndoorSpace(property.getIndoorSpace());
		propertyForm.setOutdoorSpace(property.getOutdoorSpace());
		propertyForm.setDescription(property.getDescription());
		propertyForm.setAntiquity(property.getAntiquity());
		propertyForm.setServices(property.getServices());
				
		mav.addObject("propertyForm", propertyForm);
	}
}
