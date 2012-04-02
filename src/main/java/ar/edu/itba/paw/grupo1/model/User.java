package ar.edu.itba.paw.grupo1.model;

public class User {

	private int id;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String username;
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

	public int getId() {
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
