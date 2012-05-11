package ar.edu.itba.paw.grupo1.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.web.PropertyForm;

@Controller
public class AbstractPropertyController extends BaseController {

	public AbstractPropertyController() {
		super();
	}

	protected Property buildProperty(HttpServletRequest req,
			HttpServletResponse resp) {
				
		boolean error = false;

		error |= !checkIntegerParameter(req, "propertyType", 0, 1);
		error |= !checkIntegerParameter(req, "operationType", 0, 1);
		error |= !checkParameter(req, "address", 0, 50);
		error |= !checkParameter(req, "neighbourhood", 0, 50);
		error |= !checkDoubleParameter(req, "price", 0, Integer.MAX_VALUE);
		error |= !checkIntegerParameter(req, "rooms", 1, Integer.MAX_VALUE);
		error |= !checkDoubleParameter(req, "indoorSpace", 0, Integer.MAX_VALUE);
		error |= !checkDoubleParameter(req, "outdoorSpace", 0, Integer.MAX_VALUE);
		error |= !checkParameter(req, "description", 0, 1000, true); //This is an optional field.
		error |= !checkIntegerParameter(req, "antiquity", 0, Integer.MAX_VALUE);

		if (error) {
			return null;
		}
	
		int propertyType = Integer.parseInt(req.getParameter("propertyType"));
		int operationType = Integer.parseInt(req.getParameter("operationType"));
		String address = req.getParameter("address");
		String neighbourhood = req.getParameter("neighbourhood");
		double price = Double.parseDouble(req.getParameter("price"));
		int rooms = Integer.parseInt(req.getParameter("rooms"));
		double indoorSpace = Double.parseDouble(req.getParameter("indoorSpace"));
		double outdoorSpace = Double.parseDouble(req.getParameter("outdoorSpace"));
		String description = req.getParameter("description");
		int antiquity = Integer.parseInt(req.getParameter("antiquity"));
		Set<Services> services = new HashSet<Services>();
		for (Services service : Services.values()) {
			if (req.getParameter(service.toString()) != null) {
				services.add(service);
			}
		} 
		boolean published = true; // default value
		
		int userId = getLoggedInUser(req).getId();
			
		Integer id = null;
		if (!req.getParameter("id").equals("")) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		
		return new Property(id, PropertyType.values()[propertyType], OperationType.values()[operationType], address, neighbourhood, price, rooms, 
					indoorSpace, outdoorSpace, description, antiquity, services, published, userId);
	}
	
	
	protected void setPropertyAttributes(ModelAndView mav, Property property) {
		
		PropertyForm propertyForm = new PropertyForm();
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
		
//		mav.addObject("id", property.getId());
//		mav.addObject("neighbourhood", );
//		mav.addObject("price", );
//		mav.addObject("rooms", );
//		mav.addObject("indoorSpace", );
//		mav.addObject("outdoorSpace", );
//		mav.addObject("description", );
//		mav.addObject("antiquity", );
//		mav.addObject("propertyServices", property.getServices());
		
	}
	
	
//	protected void setPropertyAttributes(HttpServletRequest req) {
//		
//		req.setAttribute("id", req.getParameter("id"));
//		req.setAttribute("propertyType", req.getParameter("propertyType"));
//		req.setAttribute("operationType", req.getParameter("operationType"));
//		req.setAttribute("address", req.getParameter("address"));
//		req.setAttribute("neighbourhood", req.getParameter("neighbourhood"));
//		req.setAttribute("price", req.getParameter("price"));
//		req.setAttribute("rooms", req.getParameter("rooms"));
//		req.setAttribute("indoorSpace", req.getParameter("indoorSpace"));
//		req.setAttribute("outdoorSpace", req.getParameter("outdoorSpace"));
//		req.setAttribute("description", req.getParameter("description"));
//		req.setAttribute("antiquity", req.getParameter("antiquity"));
//	}
}
