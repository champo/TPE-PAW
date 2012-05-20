package ar.edu.itba.paw.grupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class Picture implements Owned {

	@Id
	@SequenceGenerator(sequenceName = "pictures_seq", name = "pictures_seq")
	@GeneratedValue(generator = "pictures_seq")
	private Integer id;
	
	@ManyToOne
	private Property property;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 10, nullable = false)
	private String extension;
	
	public Picture() {
	
	}
	
	public Picture(int id, Property property, String name, String extension) {
		this.id = id;
		this.property = property;
		this.name = name;
		this.extension = extension;
	}
	
	public Picture(String name, Property property, String extension) {
		this.name = name;
		this.property = property;
		this.extension = extension;
	}
	
	public Integer getId() {
		return id;
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
	
	public void setId(int id) {
		this.id = id;
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

