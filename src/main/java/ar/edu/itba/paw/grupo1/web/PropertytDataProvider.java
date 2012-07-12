package ar.edu.itba.paw.grupo1.web;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.support.EntityModel;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;

@SuppressWarnings("serial")
public class PropertytDataProvider implements IDataProvider<Property> {

	private PropertyQuery propertyQuery;
	private PropertyRepository properties;
	
	public PropertytDataProvider(PropertyQuery propertyQuery, PropertyRepository properties) {
		this.propertyQuery = propertyQuery;
		this.properties = properties;
	}
	
	@Override
	public void detach() {
		
	}

	@Override
	public Iterator<? extends Property> iterator(int first, int count) {
		return getPropertyList(first, count).iterator();
	}

	private List<Property> getPropertyList(int first, int count) {
		return properties.query(propertyQuery).subList(first, first + count);
	}

	@Override
	public int size() {
		
		properties.get(1);
		List<Property> list = properties.query(propertyQuery);
		return list == null?0:list.size();
	}

	@Override
	public IModel<Property> model(Property property) {
		return new EntityModel<Property>(Property.class, property);
	}

}
