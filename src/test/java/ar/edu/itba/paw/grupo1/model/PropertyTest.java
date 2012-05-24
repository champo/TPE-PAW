package ar.edu.itba.paw.grupo1.model;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;

public class PropertyTest {

	@Test
	public void updateVisitCountTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, true, null, true, 0);
		
		
		Assert.assertTrue(property.getVisited() == 0);
		
		property.visited();
		
		Assert.assertTrue(property.getVisited() == 1);

		property.visited();
		property.visited();

		Assert.assertTrue(property.getVisited() == 3);

	}
	
	@Test
	public void publishTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, false, null, true, 0);
		
		
		Assert.assertFalse(property.isPublished());
		
		property.publish();
		
		Assert.assertTrue(property.isPublished());
	}
	
	@Test
	public void unpublishTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, true, null, true, 0);
		
		
		Assert.assertTrue(property.isPublished());
		
		property.unpublish();
		
		Assert.assertFalse(property.isPublished());
	}
	
	@Test
	public void reserveTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, true, null, false, 0);
		
		
		Assert.assertFalse(property.isReserved());
		
		property.reserve();
		
		Assert.assertTrue(property.isReserved());
	}
		
	@Test
	public void unreserveTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, true, null, true, 0);
		
		
		Assert.assertTrue(property.isReserved());
		
		property.unreserve();
		
		Assert.assertFalse(property.isReserved());
	}

}
