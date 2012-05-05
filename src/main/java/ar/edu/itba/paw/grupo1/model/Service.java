package ar.edu.itba.paw.grupo1.model;

import ar.edu.itba.paw.grupo1.model.Property.Services;

public class Service {
	
	private Services name;
	private boolean present;
	
	public Service(Services name, boolean present) {
		this.name = name;
		this. present = present;
	}
	
	public Services getName() {
		return name;
	}
	
	public boolean isPresent() {
		return present;
	}
}
