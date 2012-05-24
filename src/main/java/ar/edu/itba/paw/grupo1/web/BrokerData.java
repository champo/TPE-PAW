package ar.edu.itba.paw.grupo1.web;

public class BrokerData {

	private int id;
	
	private String name;
	
	private String logoExtension;
	
	private int properties;

	public BrokerData(int id, String name, String logoExtension, int properties) {
		super();
		this.id = id;
		this.name = name;
		this.logoExtension = logoExtension;
		this.properties = properties;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLogoExtension() {
		return logoExtension;
	}

	public int getProperties() {
		return properties;
	}
	
}
