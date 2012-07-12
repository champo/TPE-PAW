package ar.edu.itba.paw.grupo1.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import ar.edu.itba.paw.grupo1.model.PropertyState.State;
import ar.edu.itba.paw.grupo1.service.exception.ModelNotValidException;

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
	
	public enum Currency {
		$,
		U$S
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
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Currency currency = Currency.$;
	
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
	private boolean published = true;
	
	@Column(nullable = false)
	private boolean reserved;
	
	@Column(nullable = false)
	private boolean sold;
	
	@Column(nullable = false)
	private int visited = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy="property")
	private Set<Room> rooms = new HashSet<Room>();
	
	@Column(nullable = false)
	@ElementCollection
	private List<PropertyState> states = new ArrayList<PropertyState>(); 
	
	Property() {
	}
	
	public Property(PropertyType propertyType, OperationType operationType, String address,
			String neighbourhood, double price, int rooms,
			double indoorSpace, double outdoorSpace, 
			String description, int antiquity, Set<Services> services,
			User user, Currency currency) {
		
		int max = Integer.MAX_VALUE;
		
		if (address == null || address.length() > 50 || neighbourhood == null ||
				neighbourhood.length() > 50 || price < 0 || rooms < 0 ||
				indoorSpace < 0 || outdoorSpace < 0 || description == null ||
				description.length() > 1000 || antiquity < 0 || price > max ||
				rooms > max || indoorSpace > max || outdoorSpace > max ||
				antiquity > max) {
			throw new ModelNotValidException();
		}
		
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
		this.user = user;
		this.currency = currency;
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
		if (address == null || address.length() > 50) {
			throw new ModelNotValidException();
		}
		this.address = address;
	}

	public void setNeighbourhood(String neighbourhood) {
		if (neighbourhood == null || neighbourhood.length() > 50) {
			throw new ModelNotValidException();
		}
		this.neighbourhood = neighbourhood;
	}

	public void setPrice(double price) {
		if (price < 0 || price > Integer.MAX_VALUE) {
			throw new ModelNotValidException();
		}
		this.price = price;
	}

	public void setNumRooms(int numRooms) {
		if (numRooms < 0 || numRooms > Integer.MAX_VALUE) {
			throw new ModelNotValidException();
		}
		this.numRooms = numRooms;
	}

	public void setIndoorSpace(double indoorSpace) {
		if (indoorSpace < 0 || indoorSpace > Integer.MAX_VALUE) {
			throw new ModelNotValidException();
		}
		this.indoorSpace = indoorSpace;
	}

	public void setOutdoorSpace(double outdoorSpace) {
		if (outdoorSpace < 0 || outdoorSpace > Integer.MAX_VALUE) {
			throw new ModelNotValidException();
		}
		this.outdoorSpace = outdoorSpace;
	}

	public void setDescription(String description) {
		if (description == null || description.length() > 1000) {
			throw new ModelNotValidException();
		}
		this.description = description;
	}

	public void setAntiquity(int antiquity) {
		if (antiquity < 0 || antiquity > Integer.MAX_VALUE) {
			throw new ModelNotValidException();
		}
		this.antiquity = antiquity;
	}

	public void setServices(Set<Services> services) {
		this.services = services;
	}

	public void publish() {
		State oldState = getCurrentState();
		published = true;
		changeState(oldState);
	}
	
	public void unpublish() {
		State oldState = getCurrentState();
		published = false;
		changeState(oldState);
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}	
	
	public void reserve() {
		State oldState = getCurrentState();
		reserved = true;
		changeState(oldState);
	}
	
	public void unreserve() {
		State oldState = getCurrentState();
		reserved = false;
		changeState(oldState);
	}
	
	public Set<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	
	public boolean isSold() {
		return sold;
	}
	
	public void sell() {
		State oldState = getCurrentState();
		sold = true;
		changeState(oldState);
	}

	private void changeState(State oldState) {
		State newState = getCurrentState();
		
		if (newState != oldState) {
			states.add(new PropertyState(newState, oldState));
		}
	}
	
	private State getCurrentState() {
		State oldState = State.ACTIVE;
		if (!published) {
			oldState = State.CANCELED;
		} else if (sold) {
			oldState = State.SOLD;
		} else if (reserved) {
			oldState = State.RESERVED;
		}
		return oldState;
	}
	
	public List<PropertyState> getStates() {
		return states;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
