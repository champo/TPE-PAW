package ar.edu.itba.paw.grupo1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FilenameNotBlankValidator implements ConstraintValidator<FilenameNotBlank, Object> {

	private static final Logger logger = Logger.getLogger(FilenameNotBlankValidator.class);
	
	@Override
	public void initialize(FilenameNotBlank constraintAnnotation) {

	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		
		if (obj == null) {
			return false;
		}
		
		MultipartFile file;
		
		try {
			file = (MultipartFile) obj;
		} catch (ClassCastException e) {
			logger.warn("Failed to cast object to MultipartFile. Annotation field must be of type MultipartFile.", e);
			return false;
		}
		
		if (file.getOriginalFilename().equals("")) {
			return false;
		}
		
		return true;
	}

}
