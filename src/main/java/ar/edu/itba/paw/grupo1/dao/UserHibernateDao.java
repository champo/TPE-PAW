package ar.edu.itba.paw.grupo1.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.model.User;

@Repository
public class UserHibernateDao extends GenericHibernateDao<User> implements
		UserDao {

	@Autowired
	public UserHibernateDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User register(String name, String surname, String email,
			String phone, String username, String password)
			throws UserAlreadyExistsException {
		
		User user = new User(name, surname, email, phone, username, password);
		save(user);
		
		return user;
	}

	@Override
	public User login(String username, String hash) {
		Criteria criteria = createCriteria()
			.add(Restrictions.eq("username", username))
			.add(Restrictions.eq("password", hash))
			.setMaxResults(1);
		
		List<User> result = criteria.list();
		if (result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}

}
