package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.grupo1.model.User;

public interface UserRepository {

	@Transactional
	public User login(String username, String hash);
	
	@Transactional
	public User get(int userId);
	
	@Transactional
	public User get(String username);
	
	@Transactional
	public User register(User user) throws UserAlreadyExistsException;
	
	@Transactional
	public List<User> getBrokers();
	
	@SuppressWarnings("serial")
	public class UserAlreadyExistsException extends Exception {
		
	}


}
