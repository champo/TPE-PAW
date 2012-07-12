package ar.edu.itba.paw.grupo1.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import ar.edu.itba.paw.grupo1.model.Property.Currency;

@SuppressWarnings("serial")
public class PropertyQuery implements Serializable {

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
	private Currency currency = Currency.$;	
	
	@Range(min=0, max=Integer.MAX_VALUE)
	private Double rangeFrom;

	@Range(min=0, max=Integer.MAX_VALUE)
	private Double rangeTo;

	private OrderType order = OrderType.ASCENDING;
	
	@Range(min=1, max=Integer.MAX_VALUE)
	private int pageNumber = 1;

	
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

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
