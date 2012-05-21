package ar.edu.itba.paw.grupo1.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.UserService;

@Component
public class UserConverter implements Converter<String, User> {

	@Autowired
	private UserService userService;
	
	@Override
	public User convert(String source) {

		if (source == null || source.isEmpty()) {
			return null;
		}
		
		try {
			return userService.get(Integer.parseInt(source));
		} catch (NumberFormatException e) {
			return null;
		}
		
	}

}
