package ar.edu.itba.paw.grupo1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PropertyState {
	
	enum State {
		ACTIVE,
		RESERVED,
		SOLD,
		CANCELED
	}
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private State newState;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private State oldState;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	PropertyState() {
	}

	public PropertyState(State newState, State oldState) {
		super();
		this.newState = newState;
		this.oldState = oldState;
		this.date = new Date();
	}
	
	public Date getDate() {
		return date;
	}
	
	public State getNewState() {
		return newState;
	}
	
	public State getOldState() {
		return oldState;
	}

}
