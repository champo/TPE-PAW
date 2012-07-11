package ar.edu.itba.paw.grupo1.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.paw.grupo1.model.Property.Currency;
import ar.edu.itba.paw.grupo1.model.Property.OperationType;
import ar.edu.itba.paw.grupo1.model.Property.PropertyType;
import ar.edu.itba.paw.grupo1.model.PropertyState.State;

public class PropertyTest {

	@Test
	public void updateVisitCountTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, null, Currency.$);
		
		
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
				"description", 1, null, null, Currency.$);
		
		assertState(property, State.ACTIVE);
		
		property.unpublish();
		
		assertState(property, State.CANCELED);
		Assert.assertFalse(property.isPublished());
		
		property.publish();
		
		assertState(property, State.ACTIVE);
		Assert.assertTrue(property.isPublished());
	}
	
	@Test
	public void unpublishTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, null, Currency.$);
		
		assertState(property, State.ACTIVE);
		Assert.assertTrue(property.isPublished());
		
		property.unpublish();
		
		assertState(property, State.CANCELED);
		Assert.assertFalse(property.isPublished());
	}
	
	@Test
	public void reserveTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, null, Currency.$);
		
		assertState(property, State.ACTIVE);
		Assert.assertFalse(property.isReserved());
		
		property.reserve();
		
		assertState(property, State.RESERVED);
		Assert.assertTrue(property.isReserved());
	}
		
	@Test
	public void unreserveTest() {
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, null, Currency.$);
		
		assertState(property, State.ACTIVE);
		
		property.reserve();
		
		assertState(property, State.RESERVED);
		Assert.assertTrue(property.isReserved());
		
		property.unreserve();
		
		assertState(property, State.ACTIVE);
		Assert.assertFalse(property.isReserved());
	}
	
	@Test
	public void sellTest() {
		
		final Property property = new Property(PropertyType.FLAT,
				OperationType.SELLING, "address", "neighbourhood", 1, 1, 1, 1,
				"description", 1, null, null, Currency.$);
		
		assertState(property, State.ACTIVE);
		Assert.assertFalse(property.isSold());
		
		property.sell();
		
		assertState(property, State.SOLD);
		Assert.assertTrue(property.isSold());
		
	}

	
	private void assertState(Property prop, State state) {
		
		List<PropertyState> states = prop.getStates();
		if (states.isEmpty()) {
			Assert.assertEquals(state, State.ACTIVE);
		} else {
			Assert.assertEquals(state, states.get(states.size() - 1).getNewState());
		}
		
	}
}
