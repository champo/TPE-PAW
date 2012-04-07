<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h2>
	<c:if test="${empty edit}">
		Add New Property
	</c:if>
	<c:if test="${not empty edit}">
		Edit Property <c:out value="${property.id}" />
	</c:if>
</h2>

<form action="<c:if test=${"empty edit"}>addProperty</c:if><c:if test=${"not empty edit"}>addProperty</c:if>" method="post">
	<input type="hidden" name="id" value="<c:out value="${property.id}" />" />
	<div class="form-field">
		<label for="propertyType">Property type:</label>
		<select name="propertyType"></select>
		<option value="0" <c:if test=${property.propertyType == 0}>selected="selected"</c:if>>House</option>
		<option value="1" <c:if test=${property.propertyType == 1}>selected="selected"</c:if>>Flat</option>
	</div>
	<div class="form-field">
		<label for="operationoype">Operation type:</label>
		<select name="operationType"></select>
		<option value="0" <c:if test=${property.operationType == 0}>selected="selected"</c:if>>Selling</option>
		<option value="1" <c:if test=${property.operationType == 1}>selected="selected"</c:if>>Leasing</option>
	</div>
	<div class="form-field">
		<label for="neighbourhood">Neighbourhood:</label>
		<input type="text" name="neighbourhood" value="<c:out value="${property.neighbourhood}" />" />
	</div>
	<div class="form-field">
		<label for="price">Price:</label>
		<input type="text" name="price" value="<c:out value="${property.price}" />" />
	</div>
	<div class="form-field">
		<label for="price">Rooms:</label>
		<input type="text" name="rooms" value="<c:out value="${property.rooms}" />" />
	</div>
		<div class="form-field">
		<label for="indoorSpace">Indoor Space:</label>
		<input type="text" name="indoorSpace" value="<c:out value="${property.indoorSpace}" />" />
	</div>
	<div class="form-field">
		<label for="indoorSpace">OutDoor Space:</label>
		<input type="text" name="indoorSpace" value="<c:out value="${property.outdoorSpace}" />" />
	</div>
	<div class="form-field">
		<label for="description">Description:</label>
		<textarea name="description" cols="40" rows="5"><c:out value="${property.description}" /></textarea>
	</div>
	<div class="form-field">
		<label for="indoorSpace">OutDoor Space:</label>
		<input type="text" name="indoorSpace" value="<c:out value="${property.outdoorSpace}" />" />
	</div>
	<div class="form-field">
		<label for="cable">Cable:</label>
		<input type="checkbox" name="cable" value="true"/>
	</div>
	<div class="form-field">
		<label for="phone">Phone:</label>
		<input type="checkbox" name=phone value="true"/>
	</div>
	<div class="form-field">
		<label for="pool">Pool:</label>
		<input type="checkbox" name=pool value="true"/>
	</div>
	<div class="form-field">
		<label for="lounge">Lounge:</label>
		<input type="checkbox" name=lounge value="true"/>
	</div>
	<div class="form-field">
		<label for="paddle">Paddle:</label>
		<input type="checkbox" name=paddle value="true"/>
	</div>
	<div class="form-field">
		<label for="barbecue">Barbecue:</label>
		<input type="checkbox" name=barbecue value="true" <c:if test=${property.barbecue == 0}>selected="selected"</c:if>/>
	</div>
	
	<div class="form-buttons">
		<input type="submit" name="submit" value="Submit" />
	</div>	
	
</form>