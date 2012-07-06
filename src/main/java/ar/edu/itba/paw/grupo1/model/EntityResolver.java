package ar.edu.itba.paw.grupo1.model;

/**
 * Simple entity resolver that delegates finding entities to the underlying storage
 */
public interface EntityResolver {
	/**
	 * Fetch an entity
	 * @param <T> The type of the entity
	 * @param type The type of the entity
	 * @param id The identifier of the entity
	 * @return The entity requested
	 */
	public <T> T fetch(Class<T> type, Integer id);
	
	/**
	 * Returns the id associated with an object
	 * @param object The object whose id is to be extracted
	 * @return The extracted id
	 * @throws IllegalStateException if the object is not persistent
	 */
	public Integer getId(Object o);
}
