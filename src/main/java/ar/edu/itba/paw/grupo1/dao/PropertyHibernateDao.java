package ar.edu.itba.paw.grupo1.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;

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
		return new ArrayList<Property>();
	}

}
