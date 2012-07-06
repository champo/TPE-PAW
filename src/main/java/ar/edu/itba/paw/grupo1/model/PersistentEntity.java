/*
 * Copyright (c) 2008 IT - ITBA -- All rights reserved
 */
package ar.edu.itba.paw.grupo1.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Persistent entity base class.
 * <p>
 * This class is to be extended by classes that should be stored in a persistent location, and do not have a name associated with them. What
 * this class adds, is a unique generated ID.
 * </p>
 */
@MappedSuperclass
public class PersistentEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	protected Integer id;

	public PersistentEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public boolean isNew() {
		return (getId() == null);
	}
}