package ar.edu.itba.paw.grupo1.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.validation.FilenameNotBlank;
import ar.edu.itba.paw.grupo1.validation.IsImageFile;

@Component
public class NewPictureForm {

	@FilenameNotBlank
	@IsImageFile
	private MultipartFile file;

	@NotBlank
	@Size(max = 50)
	private String name;

	public NewPictureForm() {
		
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
    public MultipartFile getFile() {
        return file;
    }
    
	public String getName() {
		return name;
	}

	public void build(Picture picture, String extension, Property property) {
		
		picture.setExtension(extension);
		picture.setName(name);
		picture.setProperty(property);	
		
	}
}
