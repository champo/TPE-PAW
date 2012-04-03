package ar.edu.itba.paw.grupo1.dao;

import java.sql.Connection;
import java.util.List;

import ar.edu.itba.paw.grupo1.model.Picture;


public class JDBCPictureDao extends AbstractDao implements PictureDao {

	public JDBCPictureDao(Connection conn) {
		super(conn);
	}

	public List<Picture> getPictures(int propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Picture get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Picture picture) {
		// TODO Auto-generated method stub
		
	}

}
