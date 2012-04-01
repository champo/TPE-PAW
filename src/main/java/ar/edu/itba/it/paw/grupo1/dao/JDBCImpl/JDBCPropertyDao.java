package ar.edu.itba.it.paw.grupo1.dao.JDBCImpl;

import java.sql.Connection;

import ar.edu.itba.it.paw.grupo1.dao.interfaces.PropertyDao;

public class JDBCPropertyDao extends AbstractDao implements PropertyDao  {

	public JDBCPropertyDao(Connection conn) {
		super(conn);
	}

}
