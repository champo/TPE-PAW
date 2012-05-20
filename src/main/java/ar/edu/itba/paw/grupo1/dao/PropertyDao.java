package ar.edu.itba.paw.grupo1.dao;

import java.util.List;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;

public interface PropertyDao {

	public List<Property> getProperties(int userId);

	public Property get(int id);

	public void save(Property property);

	public List<Property> query(PropertyQuery query);

	public void update(Property property);
}
