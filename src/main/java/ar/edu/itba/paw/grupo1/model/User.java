package ar.edu.itba.paw.grupo1.model;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import ar.edu.itba.paw.grupo1.service.exception.ModelNotValidException;

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
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	@Column(nullable = true, length = 50)
	private String realEstateName;

	@Column(nullable = true, length = 10)
	private String logoExtension;
	
	@Column
	@Lob
	private byte[] photo = null;

	User() {
	}
	
	protected User(String name, String surname, String email, String phone,
			String username, String password, UserType type, String realEstateName,
			String logoExtension, byte[] photo) {
		if (name == null || name.length() > 50 || surname == null || 
				surname.length() > 50 || email == null || email.length() > 50 || 
				!Pattern.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)", email) || 
				phone == null || phone.length() > 20 ||
				!Pattern.matches("^ *[0-9](-?[ 0-9])*[0-9] *$", phone) ||
				username == null || username.length() > 50 || password == null || 
				password.length() != 64 || 
				(logoExtension != null && (logoExtension.length() > 10 || realEstateName == null || photo == null )) ||
				(realEstateName != null && (realEstateName.length() > 50 || logoExtension == null || photo == null)) ||
				(photo != null && (realEstateName == null || logoExtension == null))) {
			throw new ModelNotValidException();
		}
		
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.type = type;
		this.realEstateName = realEstateName;
		this.logoExtension = logoExtension;
		this.photo = photo;
	}

	public User(String name, String surname, String email, String phone,
			String username, String hash) {
		this(name, surname, email, phone, username, hash, UserType.REGULAR, null, null, null);
	}

	public User(String name, String surname, String email, String phone,
			String username, String hash, String realStateName,
			String logoExtension, byte[] photo) {
		this(name, surname, email, phone, username, hash, UserType.REAL_ESTATE, realStateName, logoExtension, photo);
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

	public String getLogoExtension() {
		return logoExtension;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public byte[] getPhoto() {
		return photo;
	}
	
	public UserType getType() {
		return type;
	}
}
