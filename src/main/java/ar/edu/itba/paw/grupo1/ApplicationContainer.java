/**
 * 
 */
package ar.edu.itba.paw.grupo1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import ar.edu.itba.paw.grupo1.dao.JDBCPictureDao;
import ar.edu.itba.paw.grupo1.dao.JDBCPropertyDao;
import ar.edu.itba.paw.grupo1.dao.JDBCUserDao;
import ar.edu.itba.paw.grupo1.dao.PictureDao;
import ar.edu.itba.paw.grupo1.dao.PropertyDao;
import ar.edu.itba.paw.grupo1.dao.UserDao;
import ar.edu.itba.paw.grupo1.service.AbstractContainer;
import ar.edu.itba.paw.grupo1.service.EmailService;
import ar.edu.itba.paw.grupo1.service.EmailServiceImpl;
import ar.edu.itba.paw.grupo1.service.PictureService;
import ar.edu.itba.paw.grupo1.service.PictureServiceImpl;
import ar.edu.itba.paw.grupo1.service.PropertyService;
import ar.edu.itba.paw.grupo1.service.PropertyServiceImpl;
import ar.edu.itba.paw.grupo1.service.UserService;
import ar.edu.itba.paw.grupo1.service.UserServiceImpl;

/**
 * Container implementation.
 * 
 * @author jpcivile
 */
public class ApplicationContainer extends AbstractContainer {

	private static ApplicationContainer self;
	
	private static Logger logger = Logger.getLogger(ApplicationContainer.class);
	
	private Properties config;
	
	private ApplicationContainer() {
		super();
		
		config = new Properties();
		try {
			config.load(getClass().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			logger.warn("The configuration for ApplicationContainer couldn't be loaded.", e);
			//We just leave config empty, and let the builders deal with it
		}
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
	
	protected UserService buildUserService(UserDao userDao) {
		return new UserServiceImpl(userDao);
	}
	
	protected UserDao buildUserDao(Connection conn) {
		return new JDBCUserDao(conn);
	}
	
	protected PropertyService buildPropertyService(PropertyDao propertyDao) {
		return new PropertyServiceImpl(propertyDao);
	}
	
	protected PropertyDao buildPropertyDao(Connection conn) {
		return new JDBCPropertyDao(conn);
	}
	
	protected PictureService buildPictureService(PictureDao pictureDao) {
		return new PictureServiceImpl(pictureDao);
	}
	
	protected PictureDao buildPictureDao(Connection conn) {
		return new JDBCPictureDao(conn);
	}
	
	protected EmailService buildEmailService() {
		return new EmailServiceImpl(config);
	}
	
	protected Connection buildConnection() {
		
		if (!config.containsKey("db.url") || !config.containsKey("db.user") || !config.containsKey("db.pass")) {
			logger.error("Database configuration missing. Bailing on the build.");
			return null;
		}
		
		try {
			Class.forName(config.getProperty("db.class"));
			return DriverManager.getConnection(config.getProperty("db.url"), config.getProperty("db.user"), config.getProperty("db.pass"));
		} catch (ClassNotFoundException e) {
			logger.fatal("The JDBC connector class could not be loaded.", e);
		} catch (SQLException e) {
			logger.fatal("Failed to build a connection.", e);
		}
		
		return null;
	}
}
