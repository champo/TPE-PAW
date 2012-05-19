package ar.edu.itba.paw.grupo1.dto;

public class PropertyQuery {

	private String operation;
	private String property;
	private double rangeFrom;
	private double rangeTo;
	private String order;

	public PropertyQuery(String operation, String property, double rangeFrom, double rangeTo, String order) {

		this.operation = operation;
		this.property = property;
		this.rangeFrom = rangeFrom;
		this.rangeTo = rangeTo;
		this.order = order;
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

	public String getOrder() {
		return order;
	}
}
