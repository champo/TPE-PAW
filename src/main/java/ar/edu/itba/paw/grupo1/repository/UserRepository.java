package ar.edu.itba.paw.grupo1.repository;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.grupo1.model.User;

public interface UserRepository {

	@Transactional
	public User login(String username, String hash);
	
	@Transactional
	public User get(int userId);
	
	@Transactional
	public User register(User user) throws UserAlreadyExistsException;
	
	public class UserAlreadyExistsException extends Exception {
		
	}

}
