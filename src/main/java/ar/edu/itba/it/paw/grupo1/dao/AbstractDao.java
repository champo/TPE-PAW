package ar.edu.itba.it.paw.grupo1.dao;

import java.sql.Connection;

public abstract class AbstractDao {

	private Connection conn;

	public AbstractDao(Connection conn) {
		this.conn = conn;

	}
	
}
