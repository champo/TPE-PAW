package ar.edu.itba.paw.grupo1.service;

import java.util.List;

import ar.edu.itba.paw.grupo1.dao.PropertyDao;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.exception.PermissionDeniedException;

public class PropertyServiceImpl implements PropertyService {

	private PropertyDao propertyDao;

	public PropertyServiceImpl(PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}

	public Property getById(int id) {
		return propertyDao.get(id);
	}

	public void save(Property property, User user) {

		if (property.getId() == null
				|| propertyDao.checkOwnership(user.getId(), property.getId())) {
			propertyDao.save(property);
		} else {
			throw new PermissionDeniedException();
		}
	}

	public List<Property> getProperties(int userId) {
		return propertyDao.getProperties(userId);
	}

	public boolean checkOwner(int propId, User user) {
		return propertyDao.checkOwnership(user.getId(), propId);
	}

	public List<Property> query(String operation, String property,
			double rangeFrom, double rangeTo) {
		return propertyDao.query(operation, property, rangeFrom, rangeTo);
	}
}
