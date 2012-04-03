package ar.edu.itba.paw.grupo1.dao;

import java.util.List;

import ar.edu.itba.paw.grupo1.model.Picture;

public interface PictureDao {

	public List<Picture> getPictures(int propertyId);
	
	public Picture get(int id);
	
	public void save(Picture picture);

}
