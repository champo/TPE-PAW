package ar.edu.itba.paw.grupo1.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "properties")
public class Property implements Owned {

	public enum PropertyType { 
		HOUSE, 
		FLAT 
	}
	
	public enum OperationType { 
		SELLING, 
		LEASING 
	}
	
	public enum Services {
		CABLE, 
		PHONE, 
		POOL, 
		LOUNGE, 
		PADDLE, 
		BARBECUE,
		TENNIS,
		SECURITY,
		LAUNDRY,
		SOLARIUM
	}

	@Id
	@SequenceGenerator(sequenceName = "properties_seq", name = "properties_seq")
	@GeneratedValue(generator = "properties_seq")
	private Integer id;
	
	@Column(nullable = false)
	private PropertyType propertyType = PropertyType.HOUSE;

	@Column(nullable = false)
	private OperationType operationType = OperationType.SELLING;
	
	@Column(nullable = false, length = 50)
	private String address;
	
	@Column(nullable = false, length = 50)
	private String neighbourhood;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private int rooms;
	
	@Column(nullable = false)
	private double indoorSpace;
	
	@Column(nullable = false)
	private double outdoorSpace;
	
	@Column(nullable = false, length = 1000)
	private String description; //Optional
	
	@Column(nullable = false)
	private int antiquity;
	
	@ElementCollection
	private Set<Services> services = new HashSet<Services>();
	
	@Column(nullable = false)
	private boolean published;
	
	@Column(nullable = false)
	private boolean reserved;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	public Property() {
	}
	
	public Property(Integer id, PropertyType propertyType, OperationType operationType, String address,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, int antiquity, Set<Services> services,
			boolean published, User user, boolean reserved) {
		
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
		this.user = user;
		this.reserved = reserved;
	}
	
	public Property(PropertyType propertyType, OperationType operationType, String address,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, int antiquity, Set<Services> services, 
			boolean published, User user, boolean reserved) {
		
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
		this.user = user;
		this.reserved = reserved;
	}
	
	public boolean isReserved() {
		return reserved;
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
	
	public boolean isPublished() {
		return published;
	}
	
	public User getUser() {
		return user;
	}

	public String getAddress() {
		return address;
	}	
	
	public int getAntiquity() {
		return antiquity;
	}	
	
	public Set<Services> getServices() {
		return services;
	}
	
	public void setType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public void setIndoorSpace(double indoorSpace) {
		this.indoorSpace = indoorSpace;
	}

	public void setOutdoorSpace(double outdoorSpace) {
		this.outdoorSpace = outdoorSpace;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAntiquity(int antiquity) {
		this.antiquity = antiquity;
	}

	public void setServices(Set<Services> services) {
		this.services = services;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isNew() {
		return id == null;
	}
	
	public void publish() {
		published = true;
	}
	
	public void unpublish() {
		published = false;
	}	
	
	public void reserve() {
		reserved = true;
	}
	
	public void unreserve() {
		reserved = false;
	}
}
