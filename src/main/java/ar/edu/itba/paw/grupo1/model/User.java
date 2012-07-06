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

	public User() {
		
	}
	
	public User(int id, String name, String surname,
			String email, String phone, String username,
			String password) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}
	
	public User(String name, String surname, String email, 
			String phone, String username, String password) {
		
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
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

	public User(Integer id, String name, String surname, String email,
			String phone, String username, String password,
			String realEstateName, String logoExtension) {
		this.id = id;
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

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!(obj instanceof User)) {
			System.out.println("instanceof");
			return false;
		}
		
		User other = (User) obj;
		if (getId() == null || other.getId() == null) {
			System.out.println("yougotanull");
			return false;
		} else {
			System.out.println("got to the end");
			return getId().equals(other.getId());
		}
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	
}
