package ar.edu.itba.paw.grupo1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Ad implements Serializable {

	@Column(nullable = false)
	@Id
	private String clientName;
	
	@Column(nullable = false)
	@Id
	private String url;
	
	@Column
	@Id
	private int weight;
	
	Ad() {
	}
	
	public String getClientName() {
		return clientName;
	}

	public String getUrl() {
		return url;
	}

	public int getWeight() {
		return weight;
	}

}
