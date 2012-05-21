package ar.edu.itba.paw.grupo1.service;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;

public interface UserService {

	@Transactional
	public User login(String username, String password);

	@Transactional
	public User get(int userId);

	@Transactional
	public User loginWithHash(String username, String hash);

	@Transactional
	public User register(User user) throws UserAlreadyExistsException;
	
}
