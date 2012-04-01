package ar.edu.itba.it.paw.grupo1.dao.JDBCImpl;

import java.sql.Connection;

import ar.edu.itba.it.paw.grupo1.dao.interfaces.PictureDao;

public class JDBCPictureDao extends AbstractDao implements PictureDao {

	public JDBCPictureDao(Connection conn) {
		super(conn);
	}

}
