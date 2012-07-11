package ar.edu.itba.paw.grupo1.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.model.Ad;

@Repository
public class AdHibernateRepository extends GenericHibernateRepository<Ad>
		implements AdRepository {
	
	private final Random rnd = new Random();

	@Autowired
	public AdHibernateRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public Ad getRandomAd() {
		List<? extends Ad> ads = getAll();
		if (ads.size() == 0) {
			return null; 
		}
		
		int[] places = new int[ads.size()];
		int total = 0;
		for (int i = 0; i < ads.size(); i++) {
			
			Ad ad = ads.get(i);
			
			places[i] = total;
			total += ad.getWeight();
		}
		
		int next = (rnd.nextInt(total - 1) % total) + 1;
		int pos = Arrays.binarySearch(places, next);
		if (pos < 0) {
			pos = -pos - 1;
		}
		
		return ads.get(pos - 1);
	}

}
