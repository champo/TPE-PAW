package ar.edu.itba.paw.grupo1.model;


public class Property {

	public enum PropertyType { HOUSE, FLAT }
	public enum OperationType { SELLING, LEASING }
	
	private Integer id;
	private PropertyType propertyType;
	private OperationType operationType;
	private String address;
	private String neighbourhood;
	private double price;
	private int rooms;
	private double indoorSpace;
	private double outdoorSpace;
	private String description; //Optional
	private int antiquity;
	private boolean cable;
	private boolean phone;
	private boolean pool;
	private boolean lounge;
	private boolean paddle;
	private boolean barbecue;
	private boolean published;
	private int userId;
	
	public Property() {
	
	}
	
	public Property(Integer id, PropertyType propertyType, OperationType operationType, String address,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, int antiquity, boolean cable, boolean phone,
			boolean pool, boolean lounge, boolean paddle, 
			boolean barbecue, boolean published, int userId) {
		
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
		this.barbecue = barbecue;
		this.paddle = paddle;
		this.pool = pool;
		this.phone = phone;
		this.cable = cable;
		this.lounge = lounge;
		this.published = published;
		this.userId = userId;
	}
	
	public Property(PropertyType propertyType, OperationType operationType, String address,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, int antiquity, boolean cable, boolean phone,
			boolean pool, boolean lounge, boolean paddle, 
			boolean barbecue, boolean published, int userId) {
		
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
		this.barbecue = barbecue;
		this.paddle = paddle;
		this.pool = pool;
		this.phone = phone;
		this.cable = cable;
		this.lounge = lounge;
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
		return barbecue;
	}
	
	public boolean isCable() {
		return cable;
	}
	
	public boolean isLounge() {
		return lounge;
	}
	
	public boolean isPaddle() {
		return paddle;
	}
	
	public boolean isPhone() {
		return phone;
	}
	
	public boolean isPool() {
		return pool;
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
}
