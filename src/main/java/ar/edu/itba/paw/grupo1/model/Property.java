package ar.edu.itba.paw.grupo1.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "properties")
public class Property extends PersistentEntity implements Owned {

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
	private int numRooms;
	
	@Column(nullable = false)
	private double indoorSpace;
	
	@Column(nullable = false)
	private double outdoorSpace;
	
	@Column(nullable = false, length = 1000)
	private String description; //Optional
	
	@Column(nullable = false)
	private int antiquity;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Services> services = new HashSet<Services>();
	
	@Column(nullable = false)
	private boolean published;
	
	@Column(nullable = false)
	private boolean reserved;
	
	@Column(nullable = false)
	private int visited;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy="property")
	private Set<Room> rooms = new HashSet<Room>();
	
	public Property() {
	}
	
	public Property(PropertyType propertyType, OperationType operationType, String address,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, int antiquity, Set<Services> services,
			boolean published, User user, boolean reserved, int visited) {
		this.propertyType = propertyType;
		this.operationType = operationType;
		this.address = address;
		this.neighbourhood = neighbourhood;
		this.price = price;
		this.numRooms = rooms;
		this.indoorSpace = indoorSpace;
		this.outdoorSpace = outdoorSpace;
		this.description = description;
		this.antiquity = antiquity;
		this.services = services;
		this.published = published;
		this.user = user;
		this.reserved = reserved;
		this.visited = visited;
	}
	
	public boolean isReserved() {
		return reserved;
	}
	
	public int getVisited() {
		return visited;
	}
	
	public void visited() {
		visited++;
	}
	
	public String getDescription() {
		return description;
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
	
	public int getNumRooms() {
		return numRooms;
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

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
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
	
	public void publish() {
		published = true;
	}
	
	public void unpublish() {
		published = false;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}	
	
	public void reserve() {
		reserved = true;
	}
	
	public void unreserve() {
		reserved = false;
	}
	
	public Set<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
}
