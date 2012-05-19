package ar.edu.itba.paw.grupo1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.PropertyService;

@Controller
public abstract class AbstractPictureController extends BaseController {

	protected PropertyService propertyService;
	
	public AbstractPictureController(PropertyService propertyService) {
		super();
		this.propertyService = propertyService;
	}

	protected Picture buildPicture(HttpServletRequest req,
			HttpServletResponse resp) {

		int propId = -1;
		Property prop = null;
		
		try {
			propId = Integer.parseInt(req.getParameter("propId"));
		} catch (NumberFormatException e) {
			return null;
		}
		
		prop = propertyService.getById(propId);
		if (prop == null) {
			return null;
		}
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String extension = req.getParameter("extension");
		
		return new Picture(id, prop, name, extension);
	}
}
