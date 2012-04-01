package ar.edu.itba.it.paw.grupo1.dao;

import java.sql.Connection;


public class JDBCUserDao extends AbstractDao implements UserDao {

	public JDBCUserDao(Connection conn) {
		super(conn);
	}

}
