<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<h2>Property <c:out value="${property.id}" /></h2>

<div class="propertyType">
	Property type:<c:if test="${property.propertyType == 0}">house</c:if><c:if test="${property.propertyType == 1}">flat</c:if>
</div>

<div class="operationType">
	Operation type:<c:if test="${property.operationType == 0}">selling</c:if><c:if test="${property.operationType == 1}">leasing</c:if>
</div>

<div class="neighbourhood">
	Neighbourhood: <c:out value="${property.neighbourhood}"></c:out>
</div>

<div class="price">
	Price: <c:out value="${property.price}"></c:out>
</div>

<div class="rooms">
	Rooms: <c:out value="${property.rooms}"></c:out>
</div>

<div class="indoorSpace">
	Indoor space: <c:out value="${property.indoorSpace}"></c:out>
</div>

<div class="outdoorSpace">
	Outdoor space: <c:out value="${property.outdoorSpace}"></c:out>
</div>

<div class="description">
	Description: <c:out value="${property.description}"></c:out>
</div>

<div class="services">
	<h4>Services</h4>
</div>

<div class="cable">
	Cable: <c:if test="${property.cable}">Yes</c:if>
</div>

<div class="phone">
	Phone: <c:if test="${property.phone}">Yes</c:if>
</div>

<div class="pool">
	Pool: <c:if test="${property.pool}">Yes</c:if>
</div>

<div class="lounge">
	Lounge: <c:if test="${property.lounge}">Yes</c:if>
</div>

<div class="paddle">
	Paddle: <c:if test="${property.paddle}">Yes</c:if>
</div>

<div class="barbecue">
	Barbecue: <c:if test="${property.barbecue}">Yes</c:if>
</div>

<c:if test="${not empty pictures}">
	<div class="pictures">
		Pictures of this property: <br />
		<c:forEach var="picture" items="${pictures}">
			<c:out value="${picture.name}"></c:out>
			<br />
			<img class="propPicture" alt="Image of the property" src="images/<c:out value="${picture.id}"></c:out><c:out value="${picture.extension}"></c:out>" />
			<br />
		</c:forEach>
	</div>
</c:if>

<c:if test="${empty pictures}">
	This property has no pictures.
</c:if>