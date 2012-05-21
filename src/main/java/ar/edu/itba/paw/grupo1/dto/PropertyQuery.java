package ar.edu.itba.paw.grupo1.dto;

import org.hibernate.validator.constraints.Range;

public class PropertyQuery {

	public enum OperationType {
		ANY,
		SELLING,
		LEASING,
	}
	
	public enum PropertyType {
		ANY,
		HOUSE,
		FLAT,
	}
	
	public enum OrderType {
		ASCENDING,
		DESCENDING,
	}

	private OperationType operation = OperationType.ANY;
	private PropertyType property = PropertyType.ANY;
	
	@Range(min=0, max=Integer.MAX_VALUE)
	private Double rangeFrom;

	@Range(min=0, max=Integer.MAX_VALUE)
	private Double rangeTo;

	private OrderType order = OrderType.ASCENDING;

	public PropertyQuery() {
		
	}

	public PropertyQuery(OperationType operation, PropertyType property, double rangeFrom, double rangeTo, OrderType order) {

		this.operation = operation;
		this.property = property;
		this.rangeFrom = rangeFrom;
		this.rangeTo = rangeTo;
		this.order = order;
	}

	public OperationType getOperation() {
		return operation;
	}

	public void setOperation(OperationType operation) {
		this.operation = operation;
	}

	public PropertyType getProperty() {
		return property;
	}

	public void setProperty(PropertyType property) {
		this.property = property;
	}

	public Double getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(Double rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public Double getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(Double rangeTo) {
		this.rangeTo = rangeTo;
	}

	public OrderType getOrder() {
		return order;
	}

	public void setOrder(OrderType order) {
		this.order = order;
	}

}
