package ar.edu.itba.paw.grupo1.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class EditPictureForm {

	@NotBlank
	@Size(max = 50)
	private String name;
	
	public EditPictureForm() {
	
	}
	
	public String getName() {
		return name;
	}
}
