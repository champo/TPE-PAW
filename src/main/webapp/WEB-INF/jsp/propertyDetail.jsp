<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<h2>Property <c:out value="${fn:escapeXml(id)}" /></h2>

<c:if test="${property.reserved}">
	<div>
		This property is reserved.
	</div>
	<br />
</c:if>

<c:if test="${not empty realEstateName}">
	<div>
		Real estate: <c:out value="${fn:escapeXml(realEstateName)}"></c:out>
		
		<c:if test="${not empty logo}">
			<img class="realEstateLogo" alt="Real estate logo" src="${basePath }/images/${logo}" />
		</c:if>
	</div>
</c:if>

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
	Rooms: <c:out value="${fn:escapeXml(property.numRooms)}"></c:out>
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
			<spring:message code="${fn:escapeXml(service.name)}"/>
		</div>
	</c:if>
</c:forEach>
<br />

<div>
	<h4>Rooms</h4>
</div>

<c:if test="${empty property.rooms}">This property has no room(s) description.<br/></c:if>
<c:if test="${not empty property.rooms}">
	<c:forEach var="room" items="${property.rooms}">
		<c:out value="${fn:escapeXml(room.name)}"/>. 
		Length: <c:out value="${fn:escapeXml(room.length)}"/>. 
		Width: <c:out value="${fn:escapeXml(room.width)}"/>. 
		<br/>
	</c:forEach>
</c:if>
<br/>

<div>
	<a href="${basePath }/contact?propertyId=${property.id}">Get contact information</a>
	-
	<a href="${basePath }/query/user?id=${property.user.id }">See ${fn:escapeXml(property.user.username) }'s properties</a>
</div>

<c:if test="${not empty pictures}">
	<div>
		Pictures of this property: <br />
		<c:set var="countPictures" value="1" />	
		<c:forEach var="picture" items="${pictures}">
			<c:out value="${fn:escapeXml(picture.name)}"></c:out>
			<br />
			<c:if test="${countPictures == 1 && property.reserved}">			
				<div class="reserved">
			</c:if>
			<img class="propPicture" alt="Image of the property" src="${basePath }/images/<c:out value="${fn:escapeXml(picture.id)}"></c:out><c:out value="${fn:escapeXml(picture.extension)}"></c:out>" />
			<c:if test="${countPictures == 1 && property.reserved}">
				<h4 class="reserved"> Reserved </h4>
				</div>
			</c:if>
			<br />
			<c:set var="countPictures" value="${countPictures + 1}" scope="page"/>
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
