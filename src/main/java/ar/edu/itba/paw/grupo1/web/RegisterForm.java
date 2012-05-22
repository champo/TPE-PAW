package ar.edu.itba.paw.grupo1.web;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.service.HashingService;
import ar.edu.itba.paw.grupo1.validation.IsEqual;

@IsEqual(first = "password", second = "passwordConfirm")
public class RegisterForm {

	@NotBlank
	@Length(max = 50)
	private String name;
	
	@NotBlank
	@Length(max = 50)
	private String surname;
	
	@NotBlank
	@Length(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Length(max = 20)
	@Pattern(regexp = "^ *[0-9](-?[ 0-9])*[0-9] *$")
	private String phone;
	
	@NotBlank
	@Length(max = 50)
	private String username;
	
	@NotBlank
	private String password;

	private String passwordConfirm;

	@Length(max = 50)
	private String realEstateName;
	
	private String logoExtension;
	
	public User build() {
		return new User(name, surname, email, phone, username, HashingService.hash(password), realEstateName, logoExtension);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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
	
	
}
