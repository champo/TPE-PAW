package ar.edu.itba.paw.grupo1.model;

public class Picture {

	private int id;
	private String name;
	private String source;
	
	public Picture() {
	
	}
	
	public Picture(int id, String name, String source) {
		this.id = id;
		this.name = name;
		this.source = source;
	}
	
	public Picture(String name, String source) {
		this.name = name;
		this.source = source;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
}

