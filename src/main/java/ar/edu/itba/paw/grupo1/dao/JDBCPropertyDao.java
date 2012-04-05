package ar.edu.itba.paw.grupo1.dao;

import java.sql.Connection;


public class JDBCPropertyDao extends AbstractDao implements PropertyDao  {

	public JDBCPropertyDao(Connection conn) {
		super(conn);
	}

}
