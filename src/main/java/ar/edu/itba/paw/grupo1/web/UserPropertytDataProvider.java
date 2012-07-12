package ar.edu.itba.paw.grupo1.web;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.model.support.EntityModel;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;

@SuppressWarnings("serial")
public class UserPropertytDataProvider implements IDataProvider<Property> {

	private transient PropertyRepository properties;
	private transient User user;

	public UserPropertytDataProvider(User user, PropertyRepository properties) {
		this.user = user;
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
		return properties.getListedProperties(user).subList(first, first + count);
	}

	@Override
	public int size() {
		return properties.getListedProperties(user).size();
	}

	@Override
	public IModel<Property> model(Property property) {
		return new EntityModel<Property>(Property.class, property);
	}

}
