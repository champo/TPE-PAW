package ar.edu.itba.paw.grupo1.model;

import java.util.HashSet;
import java.util.Set;


public class Property {

	public enum PropertyType { HOUSE, FLAT }
	public enum OperationType { SELLING, LEASING }
	public enum Services {Cable, Phone, Pool, Lounge, Paddle, Barbecue }	
	private Integer id;
	private PropertyType propertyType = PropertyType.HOUSE;
	private OperationType operationType = OperationType.SELLING;
	private String address;
	private String neighbourhood;
	private double price;
	private int rooms;
	private double indoorSpace;
	private double outdoorSpace;
	private String description; //Optional
	private int antiquity;
	private Set<Services> services = new HashSet<Services>();
	private boolean published;
	private int userId;
	
	public Property() {
	
	}
	
	public Property(Integer id, PropertyType propertyType, OperationType operationType, String address,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, int antiquity, Set<Services> services,
			boolean published, int userId) {
		
		this.id = id;
		this.propertyType = propertyType;
		this.operationType = operationType;
		this.address = address;
		this.neighbourhood = neighbourhood;
		this.price = price;
		this.rooms = rooms;
		this.indoorSpace = indoorSpace;
		this.outdoorSpace = outdoorSpace;
		this.description = description;
		this.antiquity = antiquity;
		this.services = services;
		this.published = published;
		this.userId = userId;
	}
	
	public Property(PropertyType propertyType, OperationType operationType, String address,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, int antiquity, Set<Services> services, 
			boolean published, int userId) {
		
		this.propertyType = propertyType;
		this.operationType = operationType;
		this.address = address;
		this.neighbourhood = neighbourhood;
		this.price = price;
		this.rooms = rooms;
		this.indoorSpace = indoorSpace;
		this.outdoorSpace = outdoorSpace;
		this.description = description;
		this.antiquity = antiquity;
		this.services = services;
		this.published = published;
		this.userId = userId;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public Integer getId() {
		return id;
	}
	
	public double getIndoorSpace() {
		return indoorSpace;
	}
	
	public String getNeighbourhood() {
		return neighbourhood;
	}
	
	public OperationType getOperationType() {
		return operationType;
	}
	
	public double getOutdoorSpace() {
		return outdoorSpace;
	}
	
	public double getPrice() {
		return price;
	}
	
	public PropertyType getPropertyType() {
		return propertyType;
	}
	
	public int getRooms() {
		return rooms;
	}
	
	public boolean isBarbecue() {
		if (services.contains(Services.Barbecue)) {
			return true;
		}
		return false;
	}
	
	public boolean isCable() {
		if (services.contains(Services.Cable)) {
			return true;
		}
		return false;
	}
	
	public boolean isLounge() {
		if (services.contains(Services.Lounge)) {
			return true;
		}
		return false;
	}
	
	public boolean isPaddle() {
		if (services.contains(Services.Paddle)) {
			return true;
		}
		return false;
	}
	
	public boolean isPhone() {
		if (services.contains(Services.Phone)) {
			return true;
		}
		return false;
	}
	
	public boolean isPool() {
		if (services.contains(Services.Pool)) {
			return true;
		}
		return false;
	}
	
	public boolean isPublished() {
		return published;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getAddress() {
		return address;
	}	
	
	public int getAntiquity() {
		return antiquity;
	}	
	
	public boolean isNew() {
		return id == null;
	}

	public void publish() {
		published = true;
		return;
	}
	
	public void unpublish() {
		published = false;
		return;
	}	
	
	public Set<Services> getServices() {
		return services;
	}
}
