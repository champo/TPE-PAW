package ar.edu.itba.paw.grupo1.service;

import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;

public interface UserService {

	public void register(String name, String surname, String email, String phone, String username, String password) throws UserAlreadyExistsException;

	public User login(String username, String password);
	
}
