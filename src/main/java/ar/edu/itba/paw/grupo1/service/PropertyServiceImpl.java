package ar.edu.itba.paw.grupo1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.grupo1.dao.PropertyDao;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;

@Service
public class PropertyServiceImpl implements PropertyService {

	private PropertyDao propertyDao;

	@Autowired
	public PropertyServiceImpl(PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}

	public Property getById(int id) {
		return propertyDao.get(id);
	}

	public void save(Property property, User user) {

		if (user.getId() == property.getUser().getId()) {
			
			if (property.getId() == null) {
				propertyDao.save(property);
			} else {
				propertyDao.update(property);
			}
			
		} else {
			throw new PermissionDeniedException();
		}
	}

	public List<Property> getProperties(int userId) {
		return propertyDao.getProperties(userId);
	}

	public List<Property> query(PropertyQuery query) {
		return propertyDao.query(query);
	}

	@Override
	public List<Property> getListedProperties(User user) {
		return propertyDao.getListProperties(user);
	}
}
