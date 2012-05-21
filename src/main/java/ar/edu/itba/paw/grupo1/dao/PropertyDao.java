package ar.edu.itba.paw.grupo1.dao;

import java.util.List;

import ar.edu.itba.paw.grupo1.dto.PaginatedList;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;

public interface PropertyDao {

	public List<Property> getProperties(int userId);

	public Property get(int id);

	public void save(Property property);

	public PaginatedList query(PropertyQuery query, int resultsPerPage);

	public void update(Property property);

	public List<Property> getListProperties(User user);
}
