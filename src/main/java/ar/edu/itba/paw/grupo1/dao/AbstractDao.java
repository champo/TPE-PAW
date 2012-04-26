package ar.edu.itba.paw.grupo1.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public abstract class AbstractDao {

	protected Connection conn;

	public AbstractDao(DriverManagerDataSource dataSource) throws SQLException {
		this.conn = dataSource.getConnection();

	}
	
}
