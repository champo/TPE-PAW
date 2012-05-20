package ar.edu.itba.paw.grupo1.web;

public class Service {
	
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
}
