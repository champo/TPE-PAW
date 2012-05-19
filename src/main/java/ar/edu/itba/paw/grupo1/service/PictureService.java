package ar.edu.itba.paw.grupo1.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.grupo1.model.Picture;

public interface PictureService {

	@Transactional
	public Picture getById(int id);

	@Transactional
	public void save(Picture picture);

	@Transactional
	public List<Picture> getByPropId(int propertyId);

	@Transactional
	public void delete(Picture picture);
}