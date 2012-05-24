package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.model.User;

@Repository
public class UserHibernateRepository extends GenericHibernateRepository<User> implements
		UserRepository {

	@Autowired
	public UserHibernateRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User register(User user) throws UserAlreadyExistsException {
		
		Criteria criteria = createCriteria()
			.add(Restrictions.eq("username", user.getUsername()));
		if (criteria.list().size() > 0) {
			throw new UserAlreadyExistsException();
		}
		
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
