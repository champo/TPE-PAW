package ar.edu.itba.paw.grupo1.model;

public class PropertyQuery {

	private String operation;
	private String property;
	private double rangeFrom;
	private double rangeTo;

	public PropertyQuery(String operation, String property, double rangeFrom, double rangeTo) {

		this.operation = operation;
		this.property = property;
		this.rangeFrom = rangeFrom;
		this.rangeTo = rangeTo;
	}

	public String getOperation() {
		return operation;
	}

	public String getProperty() {
		return property;
	}

	public double getRangeFrom() {
		return rangeFrom;
	}

	public double getRangeTo() {
		return rangeTo;
	}
}
