package ar.edu.itba.paw.grupo1.dto;

import java.util.List;

import ar.edu.itba.paw.grupo1.model.Property;

public class PaginatedList {

	private List<Property> list;
	private int lastPage;

	public PaginatedList(List<Property> list, int lastPage) {
		this.list = list;
		this.lastPage = lastPage;
	}

	public List<Property> getList() {
		return list;
	}
	public int getLastPage() {
		return lastPage;
	}
}
