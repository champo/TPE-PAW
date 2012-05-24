package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.grupo1.model.Picture;

public interface PictureRepository {

	@Transactional
	public List<Picture> getPictures(int propertyId);
	
	@Transactional
	public Picture get(int id);

	@Transactional
	public void save(Picture picture);

	@Transactional
	public void delete(Picture picture);

}
