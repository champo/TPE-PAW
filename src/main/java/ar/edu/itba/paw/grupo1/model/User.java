package ar.edu.itba.paw.grupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends PersistentEntity{

	public enum UserType {

		REGULAR,
		REAL_ESTATE,
	}
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private String surname;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@Column(nullable = false, length = 20)
	private String phone;
	
	@Column(nullable = false, length = 50)
	private String username;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	@Column(nullable = true, length = 50)
	private String realEstateName;

	@Column(nullable = true, length = 10)
	private String logoExtension;

	User() {
	}
	
	public User(String name, String surname, String email, String phone,
			String username, String password, String realEstateName,
			String logoExtension) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.realEstateName = realEstateName;
		this.logoExtension = logoExtension;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getUsername() {
		return username;
	}

	public String getRealEstateName() {
		return realEstateName;
	}

	public void setRealEstateName(String realEstateName) {
		this.realEstateName = realEstateName;
	}

	public String getLogoExtension() {
		return logoExtension;
	}

	public void setLogoExtension(String logoExtension) {
		this.logoExtension = logoExtension;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	
}
