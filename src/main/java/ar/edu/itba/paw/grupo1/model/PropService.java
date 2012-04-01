package ar.edu.itba.paw.grupo1.model;

public class PropService {

	private int id;
	private String service;
	
	public PropService() {
	
	}
	
	public PropService(int id, String service) {
		this.id = id;
		this.service = service;
	}
	
	public PropService(String service) {
		this.service = service;
	}
	
	public int getId() {
		return id;
	}
	
	public String getService() {
		return service;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setService(String service) {
		this.service = service;
	}
}
