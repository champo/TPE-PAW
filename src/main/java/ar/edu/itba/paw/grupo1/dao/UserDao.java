package ar.edu.itba.paw.grupo1.dao;

import ar.edu.itba.paw.grupo1.model.User;

public interface UserDao {

	public void register(String name, String surname, String email, String phone, String username, String password) throws UserAlreadyExistsException;
	
	public User login(String username, String hash);
	
	public class UserAlreadyExistsException extends Exception {
		
	}

}
