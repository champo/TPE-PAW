package ar.edu.itba.paw.grupo1.repository;

import ar.edu.itba.paw.grupo1.model.Ad;

public interface AdRepository extends GenericRepository<Ad> {
	
	public Ad getRandomAd();

}
