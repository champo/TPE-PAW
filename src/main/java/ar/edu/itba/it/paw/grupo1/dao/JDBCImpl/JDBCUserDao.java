package ar.edu.itba.it.paw.grupo1.dao.JDBCImpl;

import java.sql.Connection;

import ar.edu.itba.it.paw.grupo1.dao.interfaces.UserDao;

public class JDBCUserDao extends AbstractDao implements UserDao {

	public JDBCUserDao(Connection conn) {
		super(conn);
	}

}
