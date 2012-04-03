package ar.edu.itba.paw.grupo1.model;

public class Service {

	private int id;
	private String service;
	private int propId;
	
	public Service() {
	
	}
	
	public Service(int id, int propId, String service) {
		this.id = id;
		this.propId = propId;
		this.service = service;
	}
	
	public Service(String service) {
		this.service = service;
	}
	
	public int getId() {
		return id;
	}
	
	public String getService() {
		return service;
	}

	public int getPropId() {
		return propId;
	}
}
