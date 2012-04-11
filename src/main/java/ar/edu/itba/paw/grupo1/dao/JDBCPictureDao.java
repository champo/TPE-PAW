package ar.edu.itba.paw.grupo1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					String extension = myCursor.getString("extension");
					pictures.add(new Picture(id, propertyId, name, extension));
				}
			}
			statement.close();
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
					String extension = myCursor.getString("extension");

					response = new Picture(id, propertyId, name, extension);
				}
			}
			statement.close();
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
			statement = conn.prepareStatement("update pictures set name = ?, propertyId = ?, extension = ? where id = ?)");
			statement.setString(1, picture.getName());
			statement.setInt(2, picture.getPropId());
			statement.setString(3, picture.getExtension());
			statement.setInt(4, picture.getId());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void insert(Picture picture) {
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("insert (name, propertyId, extension) into pictures values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, picture.getName());
			statement.setInt(2, picture.getPropId());
			statement.setString(3, picture.getExtension());
			
			statement.execute();
			
			ResultSet result = statement.getGeneratedKeys();
			result.next();
			picture.setId(result.getInt("id"));
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void delete(int id) {
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("delete from pictures where id = ?)");
			statement.setInt(1, id);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
