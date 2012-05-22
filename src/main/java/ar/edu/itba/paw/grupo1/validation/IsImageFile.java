package ar.edu.itba.paw.grupo1.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsImageFileValidator.class)
@Documented
public @interface IsImageFile {

	String message() default "{constraints.isImageFile}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
