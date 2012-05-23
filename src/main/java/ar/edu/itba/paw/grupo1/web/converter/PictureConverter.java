package ar.edu.itba.paw.grupo1.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.service.PictureService;

public class PictureConverter implements Converter<String, Picture> {

	@Autowired
	private PictureService pictureService;

	
	@Override
	public Picture convert(String source) {
		if (source == null) {
			return null;
		}
		
		try {
			return pictureService.getById(Integer.parseInt(source));
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
