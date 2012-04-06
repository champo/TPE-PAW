package ar.edu.itba.paw.grupo1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


public class JDBCUserDao extends AbstractDao implements UserDao {
	
	private static final String UNIQUE_VIOLATION_STATE = "23505";
	private static Logger logger = Logger.getLogger(JDBCUserDao.class);

	public JDBCUserDao(Connection conn) {
		super(conn);
	}

	@Override
	public void register(String name, String surname, String email,
			String phone, String username, String password)
			throws UserAlreadyExistsException {
		
		
		try {
			PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO users (name, surname, email, phone, username, password)" 
					+ " VALUES(?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
			);
			
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setString(3, email);
			stmt.setString(4, phone);
			stmt.setString(5, username);
			stmt.setString(6, password);
			
			stmt.execute();
			
		} catch (SQLException e) {
			
			if (UNIQUE_VIOLATION_STATE.equals(e.getSQLState())) {
				throw new UserAlreadyExistsException();
			} else {
				logger.warn("Caught SQLException while trying to register user.", e);
			}
		}
	}

}
