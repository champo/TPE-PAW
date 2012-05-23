package ar.edu.itba.paw.grupo1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class IsImageFileValidator implements ConstraintValidator<IsImageFile, Object>{

	private static final Logger logger = Logger.getLogger(FilenameNotBlankValidator.class);
	
	@Override
	public void initialize(IsImageFile arg0) {
		
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		
		if (obj == null) {
			return false;
		}
		
		MultipartFile file;
		String extension;
		
		try {
			file = (MultipartFile) obj;
		} catch (ClassCastException e) {
			logger.warn("Failed to cast object to MultipartFile. Annotation field must be of type MultipartFile.", e);
			return false;
		}
		
		if (!file.getOriginalFilename().contains(".")) {
			logger.warn("Failed to obtain file extension. Annotation field must be a file with a compatible image extension. (.jpg, .jpeg, .gif, .png)");
			return false;
		} else {
			extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			if (!extension.equals(".jpg") && !extension.equals(".png") && !extension.equals(".jpeg") && !extension.equals(".gif")) {
				logger.warn("Wrong file extension. Annotation field must be a file with a compatible image extension. (.jpg, .jpeg, .gif, .png)");
				return false;
			}
		}
		return true;
	}

}
