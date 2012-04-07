package ar.edu.itba.paw.grupo1.service;

import ar.edu.itba.paw.grupo1.model.Picture;

public interface PictureService {

	public Picture getById(int id);

	public void save(Picture picture);
}