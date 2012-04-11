package ar.edu.itba.paw.grupo1.model;

public class Picture {

	private Integer id;
	private int propId;
	private String name;
	private String extension;
	
	public Picture() {
	
	}
	
	public Picture(int id, int propId, String name, String extension) {
		this.id = id;
		this.propId = propId;
		this.name = name;
		this.extension = extension;
	}
	
	public Picture(String name, int propId, String extension) {
		this.name = name;
		this.propId = propId;
		this.extension = extension;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPropId() {
		return propId;
	}

	public void setPropId(int propId) {
		this.propId = propId;
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
}

