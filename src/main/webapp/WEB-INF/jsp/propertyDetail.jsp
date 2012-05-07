<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<h2>Property <c:out value="${fn:escapeXml(id)}" /></h2>

<div>
	Property type:<c:if test="${property.propertyType == 'HOUSE'}"> House</c:if><c:if test="${property.propertyType == 'FLAT'}"> Flat</c:if>
</div>

<div>
	Operation type:<c:if test="${property.operationType == 'SELLING'}"> Selling</c:if><c:if test="${property.operationType == 'LEASING'}"> Leasing</c:if>
</div>

<div>
	Address: <c:out value="${fn:escapeXml(property.address)}"></c:out>
</div>

<div>
	Neighbourhood: <c:out value="${fn:escapeXml(property.neighbourhood)}"></c:out>
</div>

<div>
	Price: <c:out value="${fn:escapeXml(property.price)}"></c:out>
</div>

<div>
	Rooms: <c:out value="${fn:escapeXml(property.rooms)}"></c:out>
</div>

<div>
	Indoor space: <c:out value="${fn:escapeXml(property.indoorSpace)}"></c:out>
</div>

<div>
	Outdoor space: <c:out value="${fn:escapeXml(property.outdoorSpace)}"></c:out>
</div>

<div>
	Description: <c:out value="${fn:escapeXml(property.description)}"></c:out>
</div>

<div>
	Antiquity: <c:out value="${fn:escapeXml(property.antiquity)}"></c:out>
</div>

<div>
	<h4>Services</h4>
</div>

<c:forEach var="service" items="${services}">
	<c:if test="${service.present == true}">
		<div>
			<c:out value="${fn:escapeXml(service.name)}"></c:out>
		</div>
	</c:if>
</c:forEach>
<br />

<div>
	<a href="${basePath }/contact?propertyId=${property.id}">Get contact information</a>
</div>

<c:if test="${not empty pictures}">
	<div>
		Pictures of this property: <br />
		<c:forEach var="picture" items="${pictures}">
			<c:out value="${fn:escapeXml(picture.name)}"></c:out>
			<br />
			<img class="propPicture" alt="Image of the property" src="${basePath }/images/<c:out value="${fn:escapeXml(picture.id)}"></c:out><c:out value="${fn:escapeXml(picture.extension)}"></c:out>" />
			<br />
		</c:forEach>
	</div>
</c:if>

<c:if test="${empty pictures}">
	This property has no pictures.
</c:if>

<br/ >
<p> View in map:</p>
<img src="http://maps.googleapis.com/maps/api/staticmap?center=${fn:escapeXml(property.address)}&zoom=14&size=300x300&maptype=roadmap
&markers=color:red%7C${fn:escapeXml(address)}&sensor=false" />
