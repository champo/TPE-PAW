package ar.edu.itba.paw.grupo1.web;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Services;
import ar.edu.itba.paw.grupo1.model.User;

@Component
public class PropertyForm {

	private PropertyType propertyType;
	
	private OperationType operationType = OperationType.SELLING;
	
	@NotEmpty
	@NotNull
	@Size(min=0, max=50)
	private String address;
	
	@NotEmpty
	@NotNull
	@Size(min=0, max=50)
	private String neighbourhood;
	
	@NotNull
	private double price;
	
	@NotNull
	@Range(min=1, max=Integer.MAX_VALUE)
	private int rooms;
	
	@NotNull
	@Range(min=0, max=Integer.MAX_VALUE)
	private double indoorSpace;
	
	@NotNull
	@Range(min=0, max=Integer.MAX_VALUE)
	private double outdoorSpace;
	
	@Size(min=0, max=1000)
	private String description; //Optional
	
	@NotNull
	@Range(min=0, max=Integer.MAX_VALUE)
	private int antiquity;
	
	@NotNull
	private Set<Services> services = new HashSet<Services>();

	public PropertyForm() {
	}
	
	public PropertyForm(Property property) {
		propertyType = property.getPropertyType();
		operationType = property.getOperationType();
		address = property.getAddress();
		neighbourhood = property.getNeighbourhood();
		price = property.getPrice();
		rooms = property.getRooms();
		indoorSpace = property.getIndoorSpace();
		outdoorSpace = property.getOutdoorSpace();
		description = property.getDescription();
		antiquity = property.getAntiquity();
		services = property.getServices();
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public double getIndoorSpace() {
		return indoorSpace;
	}

	public void setIndoorSpace(double indoorSpace) {
		this.indoorSpace = indoorSpace;
	}

	public double getOutdoorSpace() {
		return outdoorSpace;
	}

	public void setOutdoorSpace(double outdoorSpace) {
		this.outdoorSpace = outdoorSpace;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAntiquity() {
		return antiquity;
	}

	public void setAntiquity(int antiquity) {
		this.antiquity = antiquity;
	}

	public Set<Services> getServices() {
		return services;
	}

	public void setServices(Set<Services> services) {
		this.services.addAll(services);
	}

	public Property build(User user) {
		return new Property(propertyType, operationType, address,
				neighbourhood, price, rooms, indoorSpace, outdoorSpace,
				description, antiquity, services, true, user, false);
	}
	
	public void update(Property property) {
		property.setAddress(address);
		property.setAntiquity(antiquity);
		property.setDescription(description);
		property.setIndoorSpace(indoorSpace);
		property.setOutdoorSpace(outdoorSpace);
		property.setNeighbourhood(neighbourhood);
		property.setPrice(price);
		property.setOperationType(operationType);
		property.setRooms(rooms);
		property.setType(propertyType);
		property.setServices(services);
	}
}
