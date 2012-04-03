package ar.edu.itba.paw.grupo1.model;

public class Picture {

	private Integer id;
	private int propId;
	private String name;
	private String source;
	
	public Picture() {
	
	}
	
	public Picture(int id, int propId, String name, String source) {
		this.id = id;
		this.propId = propId;
		this.name = name;
		this.source = source;
	}
	
	public Picture(String name, String source) {
		this.name = name;
		this.source = source;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSource() {
		return source;
	}
	
	public int getPropId() {
		return propId;
	}

}

