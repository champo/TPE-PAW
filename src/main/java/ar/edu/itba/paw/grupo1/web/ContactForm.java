package ar.edu.itba.paw.grupo1.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class ContactForm {

	@NotBlank
	@Size(max = 49)
	private String name;
	
	@NotBlank
	@Size(max = 49)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 19)
	@Pattern(regexp = "^ *([0-9](-?[ 0-9])*[0-9])? *$")
	private String phone;

	@Size(max = 999)
	private String comment;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
