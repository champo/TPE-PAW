package ar.edu.itba.paw.grupo1.service;

import java.util.List;

import ar.edu.itba.paw.grupo1.model.Picture;

public interface PictureService {

	public Picture getById(int id);

	public void save(Picture picture);

	public List<Picture> getByPropId(int propertyId);
}