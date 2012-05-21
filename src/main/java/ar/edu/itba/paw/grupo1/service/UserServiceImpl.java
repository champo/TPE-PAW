package ar.edu.itba.paw.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.grupo1.dao.UserDao;
import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	
	@Override
	public User register(User user) throws UserAlreadyExistsException {
		return userDao.register(user);
	}

	@Override
	public User login(String username, String password) {
		return loginWithHash(username, HashingService.hash(password));
	}

	@Override
	public User get(int userId) {
		return userDao.get(userId);
	}

	@Override
	public User loginWithHash(String username, String hash) {
		return userDao.login(username, hash);
	}

}
