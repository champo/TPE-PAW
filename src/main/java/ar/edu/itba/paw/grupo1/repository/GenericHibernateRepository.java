package ar.edu.itba.paw.grupo1.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

public class GenericHibernateRepository<E> implements GenericRepository<E> {

	protected SessionFactory sessionFactory;
	protected Class<? extends E> eClass;

	/**
	 * Creates a new instance of a GenericHibernateDao
	 * @param sessionFactory The session factory to be used.
	 */
	@SuppressWarnings("unchecked")
	public GenericHibernateRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

		// Get the generic class
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		this.eClass = (Class<? extends E>) type.getActualTypeArguments()[0];
	}
	
	@Override
	public List<? extends E> getAll() {
		// Warning are suppressed as we are getting an E list
		@SuppressWarnings("unchecked")
		List<? extends E> results = createCriteria().list();
	
		return results;
	}

	/**
	 * Creates a session of criteria.
	 *
	 * @return The criteria.
	 */
	protected Criteria createCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(eClass);
	}

	@Override
	public void delete(E entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public void save(E entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

}