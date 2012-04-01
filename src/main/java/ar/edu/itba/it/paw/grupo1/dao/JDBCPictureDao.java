package ar.edu.itba.it.paw.grupo1.dao;

import java.sql.Connection;


public class JDBCPictureDao extends AbstractDao implements PictureDao {

	public JDBCPictureDao(Connection conn) {
		super(conn);
	}

}
