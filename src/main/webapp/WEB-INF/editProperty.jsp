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

<form action="<c:if test="${empty edit}">addProperty</c:if><c:if test="${not empty edit}">editProperty</c:if>" method="post">
	<input type="hidden" name="id" value="<c:out value="${property.id}" />" />
	<input type="hidden" name="userId" value="<c:out value="${userId}" />" />
	<div class="form-field">
		<label for="propertyType">Property type:</label>
		<select name="propertyType">
		<option value="0" <c:if test="${property.propertyType == 0}">selected="selected"</c:if>>House</option>
		<option value="1" <c:if test="${property.propertyType == 1}">selected="selected"</c:if>>Flat</option>
		</select>
	</div>
	<div class="form-field">
		<label for="operationType">Operation type:</label>
		<select name="operationType">
		<option value="0" <c:if test="${property.operationType == 0}">selected="selected"</c:if>>Selling</option>
		<option value="1" <c:if test="${property.operationType == 1}">selected="selected"</c:if>>Leasing</option>
		</select>
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
		<label for="outdoorSpace">OutDoor Space:</label>
		<input type="text" name="outdoorSpace" value="<c:out value="${property.outdoorSpace}" />" />
	</div>
	<div class="form-field">
		<label for="description">Description:</label>
		<textarea name="description" cols="40" rows="5"><c:out value="${property.description}" /></textarea>
	</div>
	<div class="form-field">
		<label for="cable">Cable:</label>
		<input type="checkbox" name="cable" value="true" <c:if test="${property.cable == true}">checked</c:if>/>
	</div>
	<div class="form-field">
		<label for="phone">Phone:</label>
		<input type="checkbox" name="phone" value="true" <c:if test="${property.phone == true}">checked</c:if>/>
	</div>
	<div class="form-field">
		<label for="pool">Pool:</label>
		<input type="checkbox" name="pool" value="true" <c:if test="${property.pool == true}">checked</c:if>/>
	</div>
	<div class="form-field">
		<label for="lounge">Lounge:</label>
		<input type="checkbox" name="lounge" value="true" <c:if test="${property.lounge == true}">checked</c:if>/>
	</div>
	<div class="form-field">
		<label for="paddle">Paddle:</label>
		<input type="checkbox" name="paddle" value="true" <c:if test="${property.paddle == true}">checked</c:if>/>
	</div>
	<div class="form-field">
		<label for="barbecue">Barbecue:</label>
		<input type="checkbox" name="barbecue" value="true" <c:if test="${property.barbecue == true}">checked</c:if>/>
	</div>
	
	<div class="form-buttons">
		<input type="submit" name="submit" value="Submit" />
	</div>	
	
</form>

<c:if test="${not empty edit}">
		Pictures of this property:
		<br />
		<a href="addPicture?propId=<c:out value="${property.id}" />"> Add new </a>
		<br />
		<c:forEach var="picture" items="${pictures}">
			<c:out value="${picture.name}"></c:out>
			<br />
			<img class="propPicture" alt="Image of the property" src="img/<c:out value="${picture.id}"></c:out>.<c:out value="${picture.extension}"></c:out>" />
			<br />
			<a href="editPicture?id=<c:out value="${picture.id}" />"> Edit </a>  
			<br />
		</c:forEach>
</c:if>
