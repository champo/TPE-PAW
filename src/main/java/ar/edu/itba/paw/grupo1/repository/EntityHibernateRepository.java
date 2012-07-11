package ar.edu.itba.paw.grupo1.repository;

import org.hibernate.SessionFactory;

import ar.edu.itba.paw.grupo1.model.PersistentEntity;

public abstract class EntityHibernateRepository<E extends PersistentEntity> extends GenericHibernateRepository<E> implements EntityRepository<E> {

	public EntityHibernateRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int id) {
		// Warning are suppressed as we are getting an E element
		return (E) sessionFactory.getCurrentSession().get(eClass, id);
	}

}