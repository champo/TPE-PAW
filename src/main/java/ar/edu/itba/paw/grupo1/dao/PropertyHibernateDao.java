package ar.edu.itba.paw.grupo1.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;

@Repository
public class PropertyHibernateDao extends GenericHibernateDao<Property>
		implements PropertyDao {

	@Autowired
	public PropertyHibernateDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Property> getProperties(int userId) {
		
		Criteria criteria = createCriteria()
			.add(Restrictions.eq("user.id", userId));
		
		return (List<Property>) criteria.list();
	}

	@Override
	public List<Property> query(PropertyQuery query) {
		
		Criteria criteria = createCriteria().add(Restrictions.eq("published", true));

		if ("selling".equals(query.getOperation())) {
			criteria.add(Restrictions.eq("opertionType", 0));
		} else if ("leasing".equals(query.getOperation())) {
			criteria.add(Restrictions.eq("opertionType", 1));
		}

		if ("house".equals(query.getProperty())) {
			criteria.add(Restrictions.eq("propertyType", 0));
		} else if ("flat".equals(query.getProperty())) {
			criteria.add(Restrictions.eq("propertyType", 1));
		}
		
		if ("ascending".equals(query.getOrder())) {
			criteria.addOrder(Order.asc("price"));
		} else if ("descending".equals(query.getOrder())) {
			criteria.addOrder(Order.desc("price"));
		}
		
		criteria.add(Restrictions.ge("price", query.getRangeFrom()));
		criteria.add(Restrictions.le("price", query.getRangeTo()));
		
		return criteria.list();
	}

}
