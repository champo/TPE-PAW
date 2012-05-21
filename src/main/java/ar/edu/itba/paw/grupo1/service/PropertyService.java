package ar.edu.itba.paw.grupo1.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;

public interface PropertyService {

	@Transactional
	public List<Property> getProperties(int userId);

	@Transactional
	public Property getById(int id);

	@Transactional
	public void save(Property property, User user);

	@Transactional
	public List<Property> query(PropertyQuery query);

	@Transactional
	public List<Property> getListedProperties(User user);

}
