package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.dto.PaginatedList;
import ar.edu.itba.paw.grupo1.dto.PropertyQuery;
import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;

@Repository
public class PropertyHibernateDao extends EntityHibernateRepository<Property>
		implements PropertyRepository {

	@Autowired
	public PropertyHibernateDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Property> getProperties(User user) {
		
		Criteria criteria = createCriteria()
			.add(Restrictions.eq("user.id", user.getId()));
		criteria.addOrder(Order.asc("id"));
		return (List<Property>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList query(PropertyQuery query, int resultsPerPage) {
		
		Criteria criteria = createCriteria()
			.add(Restrictions.eq("published", true))
			.add(Restrictions.eq("sold", false));

		switch (query.getOperation()) {
			case SELLING:
				criteria.add(Restrictions.eq("operationType", Property.OperationType.SELLING));
				break;
			case LEASING:
				criteria.add(Restrictions.eq("operationType", Property.OperationType.LEASING));
				break;
		}

		switch (query.getProperty()) {
			case HOUSE:
				criteria.add(Restrictions.eq("propertyType", Property.PropertyType.HOUSE));
				break;
			case FLAT:
				criteria.add(Restrictions.eq("propertyType", Property.PropertyType.FLAT));
				break;
		}

		if (query.getRangeFrom() != null) {
			criteria.add(Restrictions.ge("price", query.getRangeFrom()));
		}

		if (query.getRangeTo() != null) {
			criteria.add(Restrictions.le("price", query.getRangeTo()));
		}

		int rows = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		int lastPage = (rows + resultsPerPage - 1) / resultsPerPage;

		criteria.setProjection(null).setResultTransformer(Criteria.ROOT_ENTITY);
		
		switch (query.getOrder()) {
			case ASCENDING:
				criteria.addOrder(Order.asc("price"));
				break;
			case DESCENDING:
				criteria.addOrder(Order.desc("price"));
				break;
		}
	
		criteria.setFirstResult((query.getPageNumber() - 1)*resultsPerPage);
		criteria.setMaxResults(resultsPerPage);

		return new PaginatedList(criteria.list(), lastPage);
	}

	@Override
	public List<Property> getListedProperties(User user) {
		
		Criteria criteria = createCriteria()
			.add(Restrictions.eq("user", user))
			.add(Restrictions.eq("published", true))
			.add(Restrictions.eq("sold", false));
	
		return (List<Property>) criteria.list();
	}

}
