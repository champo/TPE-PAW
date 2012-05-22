package ar.edu.itba.paw.grupo1.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PictureForm {

	private MultipartFile file;

	@NotBlank
	@Size(max = 50)
	private String name;

	public PictureForm() {
		
	}
	
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
