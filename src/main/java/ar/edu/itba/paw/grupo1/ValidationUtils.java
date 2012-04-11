package ar.edu.itba.paw.grupo1;

public class ValidationUtils {

	public static boolean isEmail(String str) {
		return str != null && str.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
	}
	
	public static boolean isPhoneNumber(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isWithinLength(String value, int min, int max) {
		return value.length() >= min && value.length() <= max;
	}
}
