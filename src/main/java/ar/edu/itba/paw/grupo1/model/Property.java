package ar.edu.itba.paw.grupo1.model;

import java.util.ArrayList;
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
	List<PropService> services = new ArrayList<PropService>();
	List<Picture> pictures = new ArrayList<Picture>();
	private boolean sold;
	private int userId;
	
	public Property() {
	
	}
	
	public Property(int id, int propertyType, int operationType,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, List<PropService> services,
			List<Picture> pictures, boolean sold, int userId) {
		
		this.id = id;
		this.propertyType = propertyType;
		this.operationType = operationType;
		this.neighbourhood = neighbourhood;
		this.price = price;
		this.rooms = rooms;
		this.indoorSpace = indoorSpace;
		this.outdoorSpace = outdoorSpace;
		this.description = description;
		this.services = services;
		this.pictures = pictures;
		this.sold = sold;
		this.userId = userId;
	}
	
	public Property(int propertyType, int operationType,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, List<PropService> services,
			List<Picture> pictures, boolean sold, int userId) {
		
		this.propertyType = propertyType;
		this.operationType = operationType;
		this.neighbourhood = neighbourhood;
		this.price = price;
		this.rooms = rooms;
		this.indoorSpace = indoorSpace;
		this.outdoorSpace = outdoorSpace;
		this.description = description;
		this.services = services;
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
	
	public List<PropService> getServices() {
		return services;
	}
	
	public PropService getService(int number) {
		return services.get(number);
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIndoorSpace(double indoorSpace) {
		this.indoorSpace = indoorSpace;
	}
	
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	
	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}
	
	public void setOutdoorSpace(double outdoorSpace) {
		this.outdoorSpace = outdoorSpace;
	}
	
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	
	public void addPicture(Picture picture) {
		this.pictures.add(picture);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}
	
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	
	public void setServices(List<PropService> services) {
		this.services = services;
	}
	
	public void addService(PropService service) {
		this.services.add(service);
	}
	
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
