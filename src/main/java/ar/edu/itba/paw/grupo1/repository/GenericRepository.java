package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

public interface GenericRepository<E> {

	/**
	 * Returns all the {@code E} instances
	 *
	 * @return list {@link List<E>} with all {@code E} instances
	 */
	public abstract List<? extends E> getAll();

	/**
	 * Deletes the given entity
	 *
	 * @param entity
	 *            to be deleted
	 */
	public abstract void delete(E entity);

	public abstract void save(E entity);

}