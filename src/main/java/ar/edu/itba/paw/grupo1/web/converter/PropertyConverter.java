package ar.edu.itba.paw.grupo1.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;

public class PropertyConverter implements Converter<String, Property> {
	
	@Autowired
	private PropertyRepository propertyRepository;

	@Override
	public Property convert(String source) {
		if (source == null) {
			return null;
		}
		
		try {
			return propertyRepository.get(Integer.parseInt(source));
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
