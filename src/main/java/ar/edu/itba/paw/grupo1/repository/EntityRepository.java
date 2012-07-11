package ar.edu.itba.paw.grupo1.repository;

import ar.edu.itba.paw.grupo1.model.PersistentEntity;


public interface EntityRepository<E extends PersistentEntity> extends GenericRepository<E> {

	/**
	 * Retrieve an instance with the given {@code id}
	 *
	 * @param id The id of the {@link E}
	 *
	 * @return {@code E}
	 */
	E get(int id);
}