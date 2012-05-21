package ar.edu.itba.paw.grupo1.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;

public class IsEqualValidator implements ConstraintValidator<IsEqual, Object> {
	
	private static final Logger logger = Logger.getLogger(IsEqualValidator.class);

	private String first;
	
	private String second;
	
	@Override
	public void initialize(IsEqual constraintAnnotation) {
		first = constraintAnnotation.first();
		second = constraintAnnotation.second();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return false;
		}
		
		Object firstValue = getField(value, first);
		Object secondValue = getField(value, second);
		
		if (firstValue == null && secondValue == null) {
			return true;
		} else if (firstValue == null) {
			return false;
		} else {
			return firstValue.equals(secondValue);
		}
	}
	
	private Object getField(Object obj, String name) {
		
		try {
			String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			Method getter = obj.getClass().getDeclaredMethod(methodName);
			return getter.invoke(obj);
		} catch (SecurityException e) {
			logger.warn("Failed to get value for field when trying to check equality", e);
		} catch (IllegalArgumentException e) {
			logger.warn("Failed to get value for field when trying to check equality", e);
		} catch (IllegalAccessException e) {
			logger.warn("Failed to get value for field when trying to check equality", e);
		} catch (NoSuchMethodException e) {
			logger.warn("Failed to get value for field when trying to check equality", e);
		} catch (InvocationTargetException e) {
			logger.warn("Failed to get value for field when trying to check equality", e);
		}
		
		return null;
	}

}
