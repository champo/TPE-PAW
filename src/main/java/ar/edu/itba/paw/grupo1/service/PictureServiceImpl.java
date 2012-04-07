package ar.edu.itba.paw.grupo1.service;

import ar.edu.itba.paw.grupo1.dao.PictureDao;
import ar.edu.itba.paw.grupo1.model.Picture;

public class PictureServiceImpl implements PictureService {
	
	private PictureDao pictureDao;
	
	public PictureServiceImpl(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}
	
	public Picture getById(int id) {
		return pictureDao.get(id);
	}

	public void save(Picture picture) {
		pictureDao.save(picture);
	}

}
