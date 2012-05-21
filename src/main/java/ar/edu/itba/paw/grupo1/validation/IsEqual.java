package ar.edu.itba.paw.grupo1.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsEqualValidator.class)
@Documented
public @interface IsEqual {

	String message() default "{constraints.isEqual}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String first();
	
	String second();
}
