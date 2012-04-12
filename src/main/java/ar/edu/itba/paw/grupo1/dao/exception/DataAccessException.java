package ar.edu.itba.paw.grupo1.dao.exception;

import java.sql.SQLException;

@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException {

	private SQLException exception;

	public DataAccessException(SQLException e) {
		this.exception = e;
	}

}
