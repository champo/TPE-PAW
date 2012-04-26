package ar.edu.itba.paw.grupo1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.grupo1.dao.PictureDao;
import ar.edu.itba.paw.grupo1.model.Picture;

@Service
public class PictureServiceImpl implements PictureService {
	
	private PictureDao pictureDao;
	
	@Autowired
	public PictureServiceImpl(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}
	
	public Picture getById(int id) {
		return pictureDao.get(id);
	}

	public void save(Picture picture) {
		if (picture.getName().length() > 50 || picture.getExtension().length() > 10) {
			return;
		}
		pictureDao.save(picture);
	}

	public List<Picture> getByPropId(int propertyId) {
		return pictureDao.getPictures(propertyId);
	}
	
	public void delete(int id) {
		pictureDao.delete(id);	
	}

}
