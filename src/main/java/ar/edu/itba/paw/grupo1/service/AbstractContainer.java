package ar.edu.itba.paw.grupo1.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Container for services & DAOs. 
 * 
 * Acts as both an abstract factory and a container.
 * It contains and serves instances of objects that have factory methods defined.
 * 
 * @author jpcivile
 */
public abstract class AbstractContainer {
	
	private Map<Class<?>, Object> instances;
	
	public AbstractContainer() {
		instances = new HashMap<Class<?>, Object>();
	}
	
	/**
	 * Get an instance of a given interface.
	 * 
	 * @param <E> The type of the interface.
	 * @param cls The interface Class object.
	 * 
	 * @return The instance, if possible
	 */
	public <E> E getObject(Class<E> cls) {

		E instance = (E) instances.get(cls);
		if (instance != null) {
			return instance;
		}
		
		try {
			return buildWithMethod(cls);
		} catch (InvalidParameterException e) {
			return null;
		}
		
	}

	/**
	 * Try to build an object using a factory method.
	 * 
	 * @param <E> The type of the object to build.
	 * @param cls The class that represents the object.
	 * 
	 * @throws InvalidParameterException A method was found, but building wasnt possible
	 */
	private <E> E buildWithMethod(Class<E> cls) throws InvalidParameterException {
		
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (cls.equals(method.getReturnType())) {
				
				Class<?>[] paramTypes = method.getParameterTypes();
				Object[] params = new Object[paramTypes.length];
				
				for (int i = 0; i < paramTypes.length; i++) {
					
					// Attempt to use ourselves to build everything that it needs
					Object param = getObject(paramTypes[i]);
					if (param == null) {
						System.out.println("Found a builder method but it wasnt possible to build: " + method.toString());
						
						// We can't build this! Bail out!
						throw new InvalidParameterException();
					} else {
						params[i] = param;
					}
				}
				
				try {
					Object instance = method.invoke(this, params);
					if (instance != null) {
						instances.put(cls, instance);
						return (E) instance;
					}
					
				} catch (IllegalAccessException e) {
					// This shouldn't happen, I am the class, it will obey
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
}
