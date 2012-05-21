package ar.edu.itba.paw.grupo1.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Room;

@Component
public class RoomForm {

	@NotNull
	@NotBlank
	@Size(min=0, max=50)
	private String name;
	
	@NotNull
	@Range(min=0, max=Integer.MAX_VALUE)
	private double length;
	
	@NotNull
	@Range(min=0, max=Integer.MAX_VALUE)
	private double width;
	
	
	public RoomForm() {
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double lenght) {
		this.length = lenght;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public Room buildRoom(Property property) {
		return new Room(name, length, width, property);
	}
	
}
