package ar.edu.itba.paw.grupo1.model;

import java.util.List;

public class Property {

	private int id;
	private int propertyType; //0 is house, 1 is flat
	private int operationType; //0 is for selling, 1 is for leasing
	private String neighbourhood;
	private double price;
	private int rooms;
	private double indoorSpace;
	private double outdoorSpace;
	private String description; //Optional
	private boolean cable;
	private boolean phone;
	private boolean pool;
	private boolean lounge;
	private boolean paddle;
	private boolean barbecue;
	List<Picture> pictures;
	private boolean sold;
	private int userId;
	
	public Property() {
	
	}
	
	public Property(int id, int propertyType, int operationType,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, boolean cable, boolean phone,
			boolean pool, boolean lounge, boolean paddle, 
			boolean barbecue, List<Picture> pictures, 
			boolean sold, int userId) {
		
		this.id = id;
		this.propertyType = propertyType;
		this.operationType = operationType;
		this.neighbourhood = neighbourhood;
		this.price = price;
		this.rooms = rooms;
		this.indoorSpace = indoorSpace;
		this.outdoorSpace = outdoorSpace;
		this.description = description;
		this.barbecue = barbecue;
		this.paddle = paddle;
		this.pool = pool;
		this.phone = phone;
		this.cable = cable;
		this.lounge = lounge;
		this.pictures = pictures;
		this.sold = sold;
		this.userId = userId;
	}
	
	public Property(int propertyType, int operationType,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, boolean cable, boolean phone,
			boolean pool, boolean lounge, boolean paddle, 
			boolean barbecue, List<Picture> pictures, 
			boolean sold, int userId) {
		
		this.propertyType = propertyType;
		this.operationType = operationType;
		this.neighbourhood = neighbourhood;
		this.price = price;
		this.rooms = rooms;
		this.indoorSpace = indoorSpace;
		this.outdoorSpace = outdoorSpace;
		this.description = description;
		this.barbecue = barbecue;
		this.paddle = paddle;
		this.pool = pool;
		this.phone = phone;
		this.cable = cable;
		this.lounge = lounge;
		this.pictures = pictures;
		this.sold = sold;
		this.userId = userId;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public int getId() {
		return id;
	}
	
	public double getIndoorSpace() {
		return indoorSpace;
	}
	
	public String getNeighbourhood() {
		return neighbourhood;
	}
	
	public int getOperationType() {
		return operationType;
	}
	
	public double getOutdoorSpace() {
		return outdoorSpace;
	}
	
	public List<Picture> getPictures() {
		return pictures;
	}
	
	public Picture getPicture(int number) {
		return pictures.get(number);
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getPropertyType() {
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
	
	public boolean isSold() {
		return sold;
	}
	
	public int getUserId() {
		return userId;
	}
		
}
