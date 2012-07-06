package ar.edu.itba.paw.grupo1.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransientObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@SuppressWarnings("unchecked")
public class HibernateEntityResolver implements EntityResolver {

	private final SessionFactory sessionFactory;

	@Autowired
	public HibernateEntityResolver(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public <T> T fetch(Class<T> type, Integer id) {
		try {
			return (T) getSession().get(type, id);
		} catch (HibernateException ex) {
			throw new HibernateException("Problem while fetching (" + type.getSimpleName() + ", " + id.toString() + ")", ex);
		}
	}

	@Override
	public Integer getId(final Object object) {
		Assert.isInstanceOf(PersistentEntity.class, object, "This entity resolver only hanldes objects implementing PersistentEntity");
		try {
			getSession().flush();
			Integer id = ((PersistentEntity) object).getId();
			if (id == null) {
				throw new TransientObjectException("Object doesn't have an id associated!");
			}
			return id;
		} catch (HibernateException ex) {
			throw new HibernateException("Problem while retrieving id for " + object.toString(), ex);
		}
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
