package ar.edu.itba.paw.grupo1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.grupo1.dao.PropertyDao;
import ar.edu.itba.paw.grupo1.dto.PaginatedList;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;

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

	public void save(Property property) {
		if (property.getId() == null) {
			propertyDao.save(property);
		} else {
			propertyDao.update(property);
		}
	}

	public List<Property> getProperties(int userId) {
		return propertyDao.getProperties(userId);
	}

	public PaginatedList query(PropertyQuery query, int resultsPerPage) {
		return propertyDao.query(query, resultsPerPage);
	}

	@Override
	public List<Property> getListedProperties(User user) {
		return propertyDao.getListProperties(user);
	}
}
