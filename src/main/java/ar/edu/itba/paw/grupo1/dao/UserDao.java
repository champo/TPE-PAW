package ar.edu.itba.paw.grupo1.dao;

import ar.edu.itba.paw.grupo1.model.User;

public interface UserDao {

	public User register(String name, String surname, String email, String phone, String username, String password) throws UserAlreadyExistsException;
	
	public User login(String username, String hash);
	
	public User get(int userId);
	
	public class UserAlreadyExistsException extends Exception {
		
	}
}
