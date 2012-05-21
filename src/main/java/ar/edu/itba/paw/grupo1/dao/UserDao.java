package ar.edu.itba.paw.grupo1.dao;

import ar.edu.itba.paw.grupo1.model.User;

public interface UserDao {

	public User login(String username, String hash);
	
	public User get(int userId);
	
	public User register(User user) throws UserAlreadyExistsException;
	
	public class UserAlreadyExistsException extends Exception {
		
	}

}
