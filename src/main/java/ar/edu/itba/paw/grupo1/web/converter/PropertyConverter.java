package ar.edu.itba.paw.grupo1.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.service.PropertyService;

public class PropertyConverter implements Converter<String, Property> {
	
	@Autowired
	private PropertyService propertyService;

	@Override
	public Property convert(String source) {
		if (source == null) {
			return null;
		}
		
		try {
			return propertyService.getById(Integer.parseInt(source));
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
