/**
 * 
 */
package ar.edu.itba.paw.grupo1;

import ar.edu.itba.paw.grupo1.service.AbstractContainer;

/**
 * Container implementation.
 * 
 * @author jpcivile
 */
public class ApplicationContainer extends AbstractContainer {

	private static ApplicationContainer self;
	
	private ApplicationContainer() {
		super();
	}
	
	/**
	 * Get an instance of the container.
	 * 
	 * @return the working instance
	 */
	public synchronized static ApplicationContainer getInstance() {
		
		if (self == null) {
			self = new ApplicationContainer();
		}
		
		return self;
	}
	
	/**
	 * Get an instance for a given interface.
	 * 
	 * @param <E> The type of the interface.
	 * @param cls The interface Class Object.
	 * 
	 * @return The instance
	 */
	public static <E> E get(Class<E> cls) {
		return getInstance().getObject(cls);
	}
}
