<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h2>
	<c:if test="${empty edit}">
		Add New Property
	</c:if>
	<c:if test="${not empty edit}">
		Edit Property <c:out value="${fn:escapeXml(id)}" />
	</c:if>
</h2>

<form action="${basePath }/<c:if test="${empty edit}">property/add</c:if><c:if test="${not empty edit}">property/edit</c:if>" method="post">
	<input type="hidden" name="id" value="<c:out value="${fn:escapeXml(id)}" />" />	
	
	<div>
		<label for="propertyType">Property type:</label>
		<select name="propertyType">
		<option value="0" <c:if test="${propertyType == 'HOUSE'}">selected="selected"</c:if>>House</option>
		<option value="1" <c:if test="${propertyType == 'FLAT'}">selected="selected"</c:if>>Flat</option>
		</select>
	</div>
	
	<div>
		<label for="operationType">Operation type:</label>
		<select name="operationType">
		<option value="0" <c:if test="${operationType == 'SELLING'}">selected="selected"</c:if>>Selling</option>
		<option value="1" <c:if test="${operationType == 'LEASING'}">selected="selected"</c:if>>Leasing</option>
		</select>
	</div>
	
	<div>
		<label for="address">Address:</label>
		<input type="text" name="address" value="<c:out value="${fn:escapeXml(address)}" />" />
	</div>
	<c:if test="${addressEmpty }">
		<p class="error">The field 'address' cannot be empty.</p>
	</c:if>
	<c:if test="${addressBadLength }">
		<p class="error">The field 'address' has to be shorter than 50 characters.</p>
	</c:if>
	
	<div>
		<label for="neighbourhood">Neighbourhood:</label>
		<input type="text" name="neighbourhood" value="<c:out value="${fn:escapeXml(neighbourhood)}" />" />
	</div>
	<c:if test="${neighbourhoodEmpty }">
		<p class="error">The field 'neighbourhood' cannot be empty.</p>
	</c:if>
	<c:if test="${neighbourhoodBadLength }">
		<p class="error">The field 'neighbourhood' has to be shorter than 50 characters.</p>
	</c:if>
	
	<div>
		<label for="price">Price:</label>
		<input type="text" name="price" value="<c:out value="${fn:escapeXml(price)}" />" />
	</div>
	<c:if test="${priceEmpty }">
		<p class="error">The field 'price' cannot be empty.</p>
	</c:if>
	<c:if test="${priceInvalidFormat }">
		<p class="error">The field 'price' must be numeric.</p>
	</c:if>
	<c:if test="${priceOutOfRange }">
		<p class="error">The field 'price' has to be a positive number under <c:out value="${integerMaxValue}"></c:out>.</p>
	</c:if>
	
	<div>
		<label for="rooms">Rooms:</label>
		<input type="text" name="rooms" value="<c:out value="${fn:escapeXml(rooms)}" />" />
	</div>
	<c:if test="${roomsEmpty }">
		<p class="error">The field 'rooms' cannot be empty.</p>
	</c:if>
	<c:if test="${roomsInvalidFormat }">
		<p class="error">The field 'rooms' must be numeric.</p>
	</c:if>
	<c:if test="${roomsOutOfRange }">
		<p class="error">The field 'rooms' has to be a positive number under <c:out value="${integerMaxValue}"></c:out>.</p>
	</c:if>
	
	<div>
		<label for="indoorSpace">Indoor Space:</label>
		<input type="text" name="indoorSpace" value="<c:out value="${fn:escapeXml(indoorSpace)}" />" />
	</div>
	<c:if test="${indoorSpaceEmpty }">
		<p class="error">The field 'indoorSpace' cannot be empty.</p>
	</c:if>
	<c:if test="${indoorSpaceInvalidFormat }">
		<p class="error">The field 'indoorSpace' must be numeric.</p>
	</c:if>
	<c:if test="${indoorSpaceOutOfRange }">
		<p class="error">The field 'indoorSpace' has to be a positive number under <c:out value="${integerMaxValue}"></c:out>.</p>
	</c:if>
	
	<div>
		<label for="outdoorSpace">Outdoor Space:</label>
		<input type="text" name="outdoorSpace" value="<c:out value="${fn:escapeXml(outdoorSpace)}" />" />
	</div>
	<c:if test="${outdoorSpaceEmpty }">
		<p class="error">The field 'outdoorSpace' cannot be empty.</p>
	</c:if>
	<c:if test="${outdoorSpaceInvalidFormat }">
		<p class="error">The field 'outdoorSpace' must be numeric.</p>
	</c:if>
	<c:if test="${outdoorSpaceOutOfRange }">
		<p class="error">The field 'outdoorSpace' has to be a positive number under <c:out value="${integerMaxValue}"></c:out>.</p>
	</c:if>
	
	<div>
		<label for="description">Description:</label>
		<textarea name="description" cols="40" rows="5"><c:out value="${fn:escapeXml(description)}" /></textarea>
	</div>
	<c:if test="${descriptionBadLength}">
		<p class="error">The field 'description' has to be shorter than 1000 characters.</p>
	</c:if>
		
	<div>
		<label for="antiquity">Antiquity:</label>
		<input type="text" name="antiquity" value="<c:out value="${fn:escapeXml(antiquity)}" />" />
	</div>
	<c:if test="${antiquityEmpty}">
		<p class="error">The field 'antiquity' cannot be empty.</p>
	</c:if>
	<c:if test="${antiquityInvalidFormat}">
		<p class="error">The field 'antiquity' must be numeric.</p>
	</c:if>
	<c:if test="${antiquityOutOfRange}">
		<p class="error">The field 'antiquity' has to be a positive number under <c:out value="${integerMaxValue}"></c:out>.</p>
	</c:if>
	
	<c:forEach var="service" items="${services}">
	<div>
		<label for="<c:out value="${fn:escapeXml(service.name)}"></c:out>"><c:out value="${fn:escapeXml(service.name)}"></c:out>:</label>
		<input type="checkbox" name="<c:out value="${fn:escapeXml(service.name)}"></c:out>" value="true" <c:if test="${service.present == true}">checked</c:if>/>
	</div>
	</c:forEach>
		
	<div>
		<input type="submit" name="submit" value="Submit" />
	</div>	
	
</form>

<c:if test="${not empty edit}">
		Pictures of this property:
		<br />
		<a href="${basePath }/picture/add?propId=<c:out value="${fn:escapeXml(id)}" />"> Add new </a>
		<br />
		<c:forEach var="picture" items="${pictures}">
			<c:out value="${fn:escapeXml(picture.name)}"></c:out>
			<br />
			<img class="propPicture" alt="Image of the property" src="${basePath }/images/<c:out value="${fn:escapeXml(picture.id)}"></c:out><c:out value="${fn:escapeXml(picture.extension)}"></c:out>" />
			<br />
			<a href="${basePath }/picture/edit?id=<c:out value="${fn:escapeXml(picture.id)}" />"> Edit or delete </a>  
			<br />
		</c:forEach>
		
		<br/ >
		<p> View in map:</p>
		<img src="http://maps.googleapis.com/maps/api/staticmap?center=${fn:escapeXml(address)}&zoom=14&size=300x300&maptype=roadmap
		&markers=color:red%7C${fn:escapeXml(address)}&sensor=false" />
</c:if>