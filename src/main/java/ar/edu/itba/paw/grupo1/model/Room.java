package ar.edu.itba.paw.grupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "rooms")
public class Room extends PersistentEntity {
	
	public enum RoomsType {
		BEDROOM,
		KITCHEN,
		BATHROOM,
		LIVINGROOM,
		PLAYROOM,
		YARD,
		OTHER
	}
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(nullable = false)
	private RoomsType room;
	
	@NotNull
	@Range(min=0, max=Integer.MAX_VALUE)
	@Column(nullable = false)
	private double length;
	
	@NotNull
	@Range(min=0, max=Integer.MAX_VALUE)
	@Column(nullable = false)
	private double width;

	@ManyToOne
	private Property property;
	
	Room() {
	}

	public Room(RoomsType room, double length, double width, Property property) {
		
		int max = Integer.MAX_VALUE;
		
		if (length < 0 || length > max || width < 0 || width > max) {
			throw new ModelNotValidException();
		}
		
		this.room = room;
		this.length = length;
		this.width = width;
		this.property = property;
	}

	public double getLength() {
		return length;
	}

	public double getWidth() {
		return width;
	}

	public Property getProperty() {
		return property;
	}
	
	public RoomsType getRoom() {
		return room;
	}
	
}
