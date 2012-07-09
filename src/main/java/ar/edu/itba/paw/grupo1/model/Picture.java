package ar.edu.itba.paw.grupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class Picture extends PersistentEntity implements Owned {
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Property property;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 10, nullable = false)
	private String extension;
	
	public Picture() {
	}
	
	public Picture(String name, Property property, String extension) {
		this.name = name;
		this.property = property;
		this.extension = extension;
	}
	
	public String getName() {
		return name;
	}
	
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public User getUser() {
		return property.getUser();
	}
}

