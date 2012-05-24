package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.model.Picture;

@Repository
public class PictureHibernateDao extends GenericHibernateRepository<Picture> implements
		PictureRepository {

	@Autowired
	public PictureHibernateDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<Picture> getPictures(int propertyId) {
		Criteria criteria = createCriteria()
				.add(Restrictions.eq("property.id", propertyId));
		
		return criteria.list();
	}

}
