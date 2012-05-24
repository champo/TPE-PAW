package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

public interface GenericRepository<E> {

	/**
	 * Retrieve an instance with the given {@code id}
	 *
	 * @param id The id of the {@link E}
	 *
	 * @return {@code E}
	 */
	E get(int id);

	/**
	 * Returns all the {@code E} instances
	 *
	 * @return list {@link List<E>} with all {@code E} instances
	 */
	List<? extends E> getAll();

	/**
	 * Deletes the given entity
	 *
	 * @param entity
	 *            to be deleted
	 */
	void delete(E entity);

	/**
	 * Updates an existant entity.
	 * @param entity to be updated.
	 */
	void update(E entity);
	
	void save(E entity);
}