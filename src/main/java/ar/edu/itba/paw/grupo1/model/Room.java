package ar.edu.itba.paw.grupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "rooms")
public class Room extends PersistentEntity {
	
	@NotNull
	@Size(min=0, max=50)
	@Column(nullable = false, length = 50)
	private String name;
	
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
	
	public Room() {
	}

	public Room(String name, double length, double width, Property property) {
		this.name = name;
		this.length = length;
		this.width = width;
		this.property = property;
	}

	public String getName() {
		return name;
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
	
}
