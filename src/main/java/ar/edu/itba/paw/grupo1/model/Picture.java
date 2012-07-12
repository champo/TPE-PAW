package ar.edu.itba.paw.grupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
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
	
	@Column(nullable = false)
	@Lob
	private byte[] data = null;
	
	Picture() {
	}
	
	public Picture(String name, Property property, String extension, byte[] data) {
		if (name == null || name.length() > 50 || extension == null ||
				extension.length() > 10 || data == null) {
			throw new ModelNotValidException();
		}
		this.name = name;
		this.property = property;
		this.extension = extension;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || name.length() > 50) {
			throw new ModelNotValidException();
		}
		this.name = name;
	}
	
	public Property getProperty() {
		return property;
	}

	public String getExtension() {
		return extension;
	}
	
	public User getUser() {
		return property.getUser();
	}
	
	public byte[] getData() {
		return data;
	}
}

