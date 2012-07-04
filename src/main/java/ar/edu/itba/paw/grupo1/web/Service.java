package ar.edu.itba.paw.grupo1.web;

import java.io.Serializable;

import ar.edu.itba.paw.grupo1.model.Property.Services;

@SuppressWarnings("serial")
public class Service implements Serializable {
	
	private String name;
	private boolean present;
	
	public Service(String name, boolean present) {
		this.name = name;
		this.present = present;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isPresent() {
		return present;
	}
	
	public Services ToEnum() {
		
		for (Services s: Services.values()) {
			if (s.name().equals(name)) {
				return s;
			}
		}
		return null;
	}
}
