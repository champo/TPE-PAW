package ar.edu.itba.paw.grupo1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@SequenceGenerator(sequenceName = "users_seq", name = "users_seq")
	@GeneratedValue(generator = "users_seq")
	private Integer id;
	
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
	
	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
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
}
