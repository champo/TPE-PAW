package ar.edu.itba.paw.grupo1.dao;

import ar.edu.itba.paw.grupo1.model.Property;

public interface PropertyDao {

	public Iterable<Property> getAll();
	
	public Property get(int id);

	public void save(Property property);
}
