<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<h2>Property <c:out value="${fn:escapeXml(id)}" /></h2>

<div>
	Property type:<c:if test="${propertyType == 0}">house</c:if><c:if test="${propertyType == 1}">flat</c:if>
</div>

<div>
	Operation type:<c:if test="${operationType == 0}">selling</c:if><c:if test="${operationType == 1}">leasing</c:if>
</div>

<div>
	Address: <c:out value="${fn:escapeXml(address)}"></c:out>
</div>

<div>
	Neighbourhood: <c:out value="${fn:escapeXml(neighbourhood)}"></c:out>
</div>

<div>
	Price: <c:out value="${fn:escapeXml(price)}"></c:out>
</div>

<div>
	Rooms: <c:out value="${fn:escapeXml(rooms)}"></c:out>
</div>

<div>
	Indoor space: <c:out value="${fn:escapeXml(indoorSpace)}"></c:out>
</div>

<div>
	Outdoor space: <c:out value="${fn:escapeXml(outdoorSpace)}"></c:out>
</div>

<div>
	Description: <c:out value="${fn:escapeXml(description)}"></c:out>
</div>

<div>
	Antiquity: <c:out value="${fn:escapeXml(antiquity)}"></c:out>
</div>

<div>
	<h4>Services</h4>
</div>

<div>
	Cable: <c:if test="${cable}">Yes</c:if>
</div>

<div>
	Phone: <c:if test="${phone}">Yes</c:if>
</div>

<div>
	Pool: <c:if test="${pool}">Yes</c:if>
</div>

<div>
	Lounge: <c:if test="${lounge}">Yes</c:if>
</div>

<div>
	Paddle: <c:if test="${paddle}">Yes</c:if>
</div>

<div>
	Barbecue: <c:if test="${barbecue}">Yes</c:if>
</div>

<div>
	<a href="${basePath }/contact?propertyId=${id}">Get contact information</a>
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
<img src="http://maps.googleapis.com/maps/api/staticmap?center=${fn:escapeXml(address)}&zoom=14&size=300x300&maptype=roadmap
&markers=color:red%7C${fn:escapeXml(address)}&sensor=false" />
