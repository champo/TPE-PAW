package ar.edu.itba.paw.grupo1.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FilenameNotBlankValidator.class)
public @interface FilenameNotBlank {
	
	String message() default "{constraints.filenameNotBlank}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
