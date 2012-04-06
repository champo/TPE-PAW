package ar.edu.itba.paw.grupo1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.paw.grupo1.model.Picture;


public class JDBCPictureDao extends AbstractDao implements PictureDao {

	public JDBCPictureDao(Connection conn) {
		super(conn);
	}


	public List<Picture> getPictures(int propertyId) {

		List<Picture> pictures = new ArrayList<Picture>();
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("select * from pictures where propertyId = ?");

			statement.setInt(1, propertyId);
			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();

				while (myCursor.next()) {
					int id = myCursor.getInt("id");
					String name = myCursor.getString("name");
					String source = myCursor.getString("source");

					pictures.add(new Picture(id, propertyId, name, source));
				}
			}
			statement.close();
//			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pictures;
	}

	public Picture get(int id) {
		Picture response = null;
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("select * from pictures where id = ?");

			statement.setInt(1, id);
			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();

				if (myCursor.next()) {
					int propertyId = myCursor.getInt("propertyId");
					String name = myCursor.getString("name");
					String source = myCursor.getString("source");

					response = new Picture(id, propertyId, name, source);
				}
			}
			statement.close();
//			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}

	public void save(Picture picture) {
		if (picture.getId() == null) {
			insert(picture);
		} else {
			update(picture);
		}
	}


	private void update(Picture picture) {
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("update pictures set name = ?, source = ?, propertyId = ? where id = ?)");

			statement.setString(1, picture.getName());
			statement.setString(2, picture.getSource());
			statement.setInt(3, picture.getPropId());
			statement.setInt(4, picture.getId());

			statement.execute();
			statement.close();
//			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void insert(Picture picture) {
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("insert (name, source, propertyId) into pictures values(?,?,?)");

			statement.setString(1, picture.getName());
			statement.setString(2, picture.getSource());
			statement.setInt(3, picture.getPropId());

			statement.execute();
			statement.close();
//			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}