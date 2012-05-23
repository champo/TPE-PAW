<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="page-header"><h1>Property detail<c:out value="${fn:escapeXml(id)}" /></h1></div>

<c:if test="${property.reserved}">
	<p class="alert">
		This property is reserved.
	</p>
	<br />
</c:if>

<c:if test="${not empty realEstateName}">
	<p>
		Real estate: <c:out value="${fn:escapeXml(realEstateName)}"></c:out>
		
		<c:if test="${not empty logo}">
			<img class="realEstateLogo" alt="Real estate logo" src="${basePath }/images/${logo}" />
		</c:if>
	</p>
</c:if>

<div class="row">
	<div class="span8">
		<p>
			<strong>Property type:</strong><c:if test="${property.propertyType == 'HOUSE'}"> House</c:if><c:if test="${property.propertyType == 'FLAT'}"> Flat</c:if>
		</p>
		
		<p>
			<strong>Operation type:</strong><c:if test="${property.operationType == 'SELLING'}"> Selling</c:if><c:if test="${property.operationType == 'LEASING'}"> Leasing</c:if>
		</p>
		
		<p>
			<strong>Address:</strong> <c:out value="${fn:escapeXml(property.address)}"></c:out>
		</p>
		
		<p>
			<strong>Neighbourhood:</strong> <c:out value="${fn:escapeXml(property.neighbourhood)}"></c:out>
		</p>
		
		<p>
			<strong>Price:</strong> <c:out value="${fn:escapeXml(property.price)}"></c:out>
		</p>
		
		<p>
			<strong>Rooms:</strong> <c:out value="${fn:escapeXml(property.numRooms)}"></c:out>
		</p>
		
		<p>
			<strong>Indoor space:</strong> <c:out value="${fn:escapeXml(property.indoorSpace)}"></c:out>
		</p>
		
		<p>
			<strong>Outdoor space:</strong> <c:out value="${fn:escapeXml(property.outdoorSpace)}"></c:out>
		</p>
		
		<p>
			<strong>Description:</strong> <c:out value="${fn:escapeXml(property.description)}"></c:out>
		</p>
		
		<p>
			<strong>Antiquity:</strong> <c:out value="${fn:escapeXml(property.antiquity)}"></c:out>
		</p>
		
		<p>
			<strong>Services:</strong>
		
			<ul>
				<c:forEach var="service" items="${services}">
					<c:if test="${service.present == true}">
						<li>
							<spring:message code="${fn:escapeXml(service.name)}"/>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</p>
		
		<p>
			<strong>Rooms:</strong>
		
			<c:if test="${empty property.rooms}">This property has no room(s) description.<br/></c:if>
			<c:if test="${not empty property.rooms}">
				<br />
				<ul>
					<c:forEach var="room" items="${property.rooms}">
						<li>
							<strong><c:out value="${fn:escapeXml(room.name)}"/>:</strong>
							length: <c:out value="${fn:escapeXml(room.length)}"/>,
							width: <c:out value="${fn:escapeXml(room.width)}"/>.
						</li>
					</c:forEach>
				</ul>
			</c:if>
		</p>
		
		<br />
		
		<p>
			<a href="${basePath }/contact?propertyId=${property.id}">Get contact information</a>
			-
			<a href="${basePath }/query/user?id=${property.user.id }">See ${fn:escapeXml(property.user.username) }'s properties</a>
		</p>
	</div>
	<div class="span4">
		<p><strong> View in map:</strong></p>
		<p>
			<img src="http://maps.googleapis.com/maps/api/staticmap?center=${fn:escapeXml(property.address)}&zoom=14&size=300x300&maptype=roadmap&markers=color:red%7C${fn:escapeXml(address)}&sensor=false" />
		</p>
	</div>
</div>

<br />
<br />

<c:if test="${not empty pictures}">
	<div>
		<strong>Pictures of this property:</strong> <br />
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

<br />
<br />

<p>
	This property has been visited <c:out value="${fn:escapeXml(property.visited)}"></c:out> time<c:if test="${property.visited != 1}">s</c:if>.
</div>
