package ar.edu.itba.paw.grupo1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.paw.grupo1.model.Property;



public class JDBCPropertyDao extends AbstractDao implements PropertyDao  {


	public JDBCPropertyDao(Connection conn) {
		super(conn);
	}


	public List<Property> getAll() {

		List<Property> properties = new ArrayList<Property>();
		PreparedStatement statement;

		try {
			statement = conn.prepareStatement("select * from properties");
			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();

				while (myCursor.next()) {
					Property property = createProperty(myCursor);  				
					properties.add(property);
				}
			}
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public Property get(int id) {

		PreparedStatement statement;
		Property property = null;

		try {
			statement = conn.prepareStatement("select * from properties where id = ?");
			statement.setInt(1, id);
			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();
				if (myCursor.next()) {

					property = createProperty(myCursor);
				}
			}
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return property;
	}
	public void save(Property property) {

		try {
			PreparedStatement statement;
			if (property.isNew()) {

				statement = conn.prepareStatement("INSERT INTO properties values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				setPlaceHolders(statement, property);

			} else {
				statement = conn.prepareStatement("UPDATE properties SET propertyType = ?, operationType = ?, neighbourhood = ?, price = ?," +
						"rooms = ?, indoorSpace = ?, outdoorSpace = ?, description = ?, cable = ?, phone = ?, pool = ?, lounge = ?, paddle = ?," +
						"barbecue = ?, sold = ?, userId = ? WHERE id = ?");
				setPlaceHolders(statement, property);
				statement.setInt(17, property.getId());

			}
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setPlaceHolders(PreparedStatement stmt, Property property) throws SQLException {
		stmt.setInt(1, property.getPropertyType());
		stmt.setInt(2, property.getOperationType());
		stmt.setString(3, property.getNeighbourhood());
		stmt.setDouble(4, property.getPrice());
		stmt.setInt(5, property.getRooms());
		stmt.setDouble(6, property.getIndoorSpace());
		stmt.setDouble(7, property.getOutdoorSpace());
		stmt.setString(8, property.getDescription());
		stmt.setBoolean(9, property.isCable());
		stmt.setBoolean(10, property.isPhone());
		stmt.setBoolean(11, property.isPool());
		stmt.setBoolean(12, property.isLounge());
		stmt.setBoolean(13, property.isPaddle());
		stmt.setBoolean(14, property.isBarbecue());
		stmt.setBoolean(15, property.isSold());
		stmt.setInt(16, property.getUserId());
	}


	private Property createProperty(ResultSet cursor) throws SQLException {
		int id = cursor.getInt("id");
		int propertyType = cursor.getInt("propertyType");
		int operationType = cursor.getInt("operationType");
		String neighbourhood = cursor.getString("neighbourhood");
		double price = cursor.getDouble("price");
		int rooms = cursor.getInt("rooms");
		double indoorSpace = cursor.getDouble("indoorSpace");
		double outdoorSpace = cursor.getDouble("outdoorSpace");
		String description = cursor.getString("description");
		boolean sold = cursor.getBoolean("sold");
		int userId = cursor.getInt("userId");

		boolean cable = cursor.getBoolean("cable");
		boolean phone = cursor.getBoolean("phone");
		boolean pool = cursor.getBoolean("pool");
		boolean lounge = cursor.getBoolean("lounge");
		boolean paddle = cursor.getBoolean("paddle");
		boolean barbecue = cursor.getBoolean("barbecue");

		return new Property(id, propertyType, operationType, neighbourhood, price,
				rooms, indoorSpace, outdoorSpace, description, cable, phone,
				pool, lounge, paddle, barbecue, sold, userId);
		
	}
	
}


