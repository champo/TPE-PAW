package ar.edu.itba.paw.grupo1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.dao.exception.DataAccessException;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.Service;
import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.Property.Services;

@Repository
public class JDBCPropertyDao extends AbstractDao implements PropertyDao {

	private static Logger logger = Logger.getLogger(JDBCPropertyDao.class);

	@Autowired
	public JDBCPropertyDao(DriverManagerDataSource dataSource) throws SQLException {
		super(dataSource);
	}

	public List<Property> getProperties(int userId) {

		List<Property> properties = new ArrayList<Property>();
		PreparedStatement statement;

		try {
			statement = conn
					.prepareStatement("select * from properties where userId = ?");
			statement.setInt(1, userId);
			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();

				while (myCursor.next()) {
					Property property = buildProperty(myCursor);
					properties.add(property);
				}
			}
			statement.close();

		} catch (SQLException e) {
			logger.warn("Caught SQLException while trying to obtain user's registered properties.", e);
			throw new DataAccessException(e);
		}
		return properties;
	}

	public Property get(int id) {

		PreparedStatement statement;
		Property property = null;

		try {
			statement = conn
					.prepareStatement("select * from properties where id = ?");
			statement.setInt(1, id);
			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();
				if (myCursor.next()) {

					property = buildProperty(myCursor);
				}
			}
			statement.close();

		} catch (SQLException e) {
			logger.warn("Caught SQLException while trying to obtain property.", e);
			throw new DataAccessException(e);
		}

		return property;
	}

	public void save(Property property) {

		try {
			PreparedStatement statement;
			if (property.isNew()) {

				statement = conn
						.prepareStatement("INSERT INTO properties (propertytype, operationtype, address,"
								+ " neighbourhood, price, rooms, indoorspace, outdoorspace, description, antiquity,"
								+ " cable, phone, pool, lounge, paddle, barbecue, published, userid) "
								+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				setPlaceHolders(statement, property);

			} else {
				statement = conn
						.prepareStatement("UPDATE properties SET propertyType = ?, operationType = ?, address = ?, "
								+ "neighbourhood = ?, price = ?, rooms = ?, indoorSpace = ?, outdoorSpace = ?, description = ?, "
								+ "antiquity = ?, cable = ?, phone = ?, pool = ?, lounge = ?, paddle = ?, barbecue = ?, published = ?, "
								+ "userId = ? WHERE id = ?");
				setPlaceHolders(statement, property);
				statement.setInt(19, property.getId());

			}
			statement.execute();

		} catch (SQLException e) {
			logger.warn("Caught SQLException while trying to save property.", e);
			throw new DataAccessException(e);
		}
	}

	private void setPlaceHolders(PreparedStatement stmt, Property property)
			throws SQLException {

		stmt.setInt(1, property.getPropertyType().ordinal());
		stmt.setInt(2, property.getOperationType().ordinal());
		stmt.setString(3, property.getAddress());
		stmt.setString(4, property.getNeighbourhood());
		stmt.setDouble(5, property.getPrice());
		stmt.setInt(6, property.getRooms());
		stmt.setDouble(7, property.getIndoorSpace());
		stmt.setDouble(8, property.getOutdoorSpace());
		stmt.setString(9, property.getDescription());
		stmt.setInt(10, property.getAntiquity());
		Set<Services> services = property.getServices();
		stmt.setBoolean(11, isCable(services));
		stmt.setBoolean(12, isPhone(services));
		stmt.setBoolean(13, isPool(services));
		stmt.setBoolean(14, isLounge(services));
		stmt.setBoolean(15, isPaddle(services));
		stmt.setBoolean(16, isBarbecue(services));
		stmt.setBoolean(17, property.isPublished());
		stmt.setInt(18, property.getUserId());
	}

	private boolean isCable(Set<Services> services) {
		return services.contains(Services.CABLE);
	}
	
	private boolean isPhone(Set<Services> services) {
		return services.contains(Services.PHONE);
	}
	
	private boolean isPool(Set<Services> services) {
		return services.contains(Services.POOL);
	}
	
	private boolean isLounge(Set<Services> services) {
		return services.contains(Services.LOUNGE);
	}
	
	private boolean isPaddle(Set<Services> services) {
		return services.contains(Services.PADDLE);
	}
	
	private boolean isBarbecue(Set<Services> services) {
		return services.contains(Services.BARBECUE);
	}

	private Property buildProperty(ResultSet cursor) throws SQLException {

		int id = cursor.getInt("id");
		int propertyType = cursor.getInt("propertyType");
		int operationType = cursor.getInt("operationType");
		String address = cursor.getString("address");
		String neighbourhood = cursor.getString("neighbourhood");
		double price = cursor.getDouble("price");
		int rooms = cursor.getInt("rooms");
		double indoorSpace = cursor.getDouble("indoorSpace");
		double outdoorSpace = cursor.getDouble("outdoorSpace");
		String description = cursor.getString("description");
		int antiquity = cursor.getInt("antiquity");
		boolean published = cursor.getBoolean("published");
		int userId = cursor.getInt("userId");
		Set<Services> services = new HashSet<Services>();
		if (cursor.getBoolean("cable") == true) {
			services.add(Services.CABLE);
		}
		if (cursor.getBoolean("pool") == true) {
			services.add(Services.POOL);
		}
		if (cursor.getBoolean("phone") == true) {
			services.add(Services.PHONE);
		}
		if (cursor.getBoolean("lounge") == true) {
			services.add(Services.LOUNGE);
		}
		if (cursor.getBoolean("paddle") == true) {
			services.add(Services.PADDLE);
		}
		if (cursor.getBoolean("barbecue") == true) {
			services.add(Services.BARBECUE);
		}

		return new Property(id, PropertyType.values()[propertyType], 
				OperationType.values()[operationType], address,
				neighbourhood, price, rooms, indoorSpace, outdoorSpace,
				description, antiquity, services, published, userId);

	}

	public boolean checkOwnership(Integer userId, Integer propertyId) {

		PreparedStatement statement;

		try {
			statement = conn
					.prepareStatement("select * from properties where userId = ? and id = ?");
			statement.setInt(1, userId);
			statement.setInt(2, propertyId);

			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();

				if (myCursor.next()) {
					return true;
				}
			}
			statement.close();

		} catch (SQLException e) {
			logger.warn("Caught SQLException while trying to make check property ownership.", e);
			throw new DataAccessException(e);
		}
		return false;
	}

	public int getUser(int id) {
		int userId = 0;

		PreparedStatement statement;

		try {
			statement = conn
					.prepareStatement("select userId from properties where id = ?");
			statement.setInt(1, id);

			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();

				if (myCursor.next()) {
					return myCursor.getInt("userId");
				}
			}
			statement.close();

		} catch (SQLException e) {
			logger.warn("Caught SQLException while trying to get user id.", e);
			throw new DataAccessException(e);
		}
		return userId;
	}

	public List<Property> query(PropertyQuery query) {

		String queryString = "SELECT * FROM properties WHERE published = true AND ";

		if (query.getOperation().equals("selling")) {
			queryString += "operationType = 0 AND ";
		} else if (query.getOperation().equals("leasing")) {
			queryString += "operationType = 1 AND ";
		}

		if (query.getProperty().equals("house")) {
			queryString += "propertyType = 0 AND ";
		} else if (query.getProperty().equals("flat")) {
			queryString += "propertyType = 1 AND ";
		}

		queryString += "price >= " + query.getRangeFrom() + " AND price <= " + query.getRangeTo();

		List<Property> properties = new ArrayList<Property>();
		PreparedStatement statement;

		try {
			statement = conn.prepareStatement(queryString);

			if (statement.execute()) {
				ResultSet myCursor = statement.getResultSet();

				while (myCursor.next()) {
					Property prop = buildProperty(myCursor);
					properties.add(prop);
				}
			}
			statement.close();

		} catch (SQLException e) {
			logger.warn("Caught SQLException while trying to make search query.", e);
			throw new DataAccessException(e);
		}

		return properties;
	}
}
