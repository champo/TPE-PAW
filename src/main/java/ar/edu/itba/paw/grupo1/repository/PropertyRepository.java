package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.grupo1.dto.PaginatedList;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;

public interface PropertyRepository {

	@Transactional
	public List<Property> getProperties(User user);

	@Transactional
	public Property get(int id);

	@Transactional
	public void save(Property property);

	@Transactional
	public PaginatedList query(PropertyQuery query, int resultsPerPage);

	@Transactional
	public void update(Property property);

	@Transactional
	public List<Property> getListedProperties(User user);
}
