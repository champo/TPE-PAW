<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h2>
	<c:if test="${empty edit}">
		Add New Property
	</c:if>
	<c:if test="${not empty edit}">
		Edit Property <c:out value="${fn:escapeXml(param.id)}" />
	</c:if>
</h2>


<c:set var="url">
<c:if test="${empty edit}">property/add</c:if>
<c:if test="${not empty edit}">property/edit?id=${fn:escapeXml(param.id)}</c:if>
</c:set>

<form:form action="${basePath }/${url }" method="post" commandName="propertyForm">
	
	<form:errors path="*"/>
	
	<div>
		<label for="propertyType">Property type:</label>
		<form:select path="propertyType">
			<form:option value='HOUSE'>House</form:option>
			<form:option value='FLAT'>Flat</form:option>
		</form:select>
	</div>
	
	<div>
		<label for="operationType">Operation type:</label>
		<form:select path="operationType">
			<form:option value="SELLING">Selling</form:option>
			<form:option value="LEASING">Leasing</form:option>
		</form:select>
	</div>
	
	<div>
		<label for="address">Address:</label>
		<form:input path="address" />
	</div>
	
	<div>
		<label for="neighbourhood">Neighbourhood:</label>
		<form:input path="neighbourhood"/>
	</div>
	
	<div>
		<label for="price">Price:</label>
		<form:input path="price"/>
	</div>
	
	<div>
		<label for="numRooms">Rooms:</label>
		<form:input path="numRooms"/>
	</div>
	
	<div>
		<label for="indoorSpace">Indoor Space:</label>
		<form:input path="indoorSpace"/>
	</div>
	
	<div>
		<label for="outdoorSpace">Outdoor Space:</label>
		<form:input path="outdoorSpace" />
	</div>
	
	<div>
		<label for="description">Description:</label>
		<form:textarea path="description" cols="40" rows="5"/>
	</div>
		
	<div>
		<label for="antiquity">Antiquity:</label>
		<form:input path="antiquity" />
	</div>
		
		
	<c:set var="count" value="1" />	
	<c:forEach var="service" items="${services}">
		<span>
			<input id="services<c:out value="${count}"></c:out>" name="services" type="checkbox" value="<c:out value="${fn:escapeXml(service.name)}"></c:out>"<c:if test="${service.present}"> checked="checked"</c:if>/>
			<label for="services<c:out value="${count}"></c:out>"> <spring:message code="${fn:escapeXml(service.name)}"/> </label> 
		</span>
		<c:set var="count" value="${count + 1}" scope="page"/>
	</c:forEach>
	<br />	
		
	<div>
		<input type="submit" name="submit" value="Submit" />
	</div>	
	
</form:form>


<c:if test="${not empty edit}">
	Rooms of this property:
	<br />
	<a href="${basePath }/property/addRoom/<c:out value="${fn:escapeXml(param.id)}" />"> Add new room</a>
	<c:forEach var="room" items="${rooms}">
		<br />
		<c:out value="${fn:escapeXml(room.name)}" />. 
		Largo: <c:out value="${fn:escapeXml(room.length)}" />
		Ancho: <c:out value="${fn:escapeXml(room.width)}" />
	</c:forEach>
	<br />
	
</c:if>

<c:if test="${not empty edit}">
		<br />
		Pictures of this property:
		<br />
		<a href="${basePath }/picture/add/<c:out value="${fn:escapeXml(param.id)}" />"> Add new </a>
		<br />
		<c:forEach var="picture" items="${pictures}">
			<c:out value="${fn:escapeXml(picture.name)}"></c:out>
			<br />
			<img class="propPicture" alt="Image of the property" src="${basePath }/images/<c:out value="${fn:escapeXml(picture.id)}"></c:out><c:out value="${fn:escapeXml(picture.extension)}"></c:out>" />
			<br />
			<a href="${basePath }/picture/edit/<c:out value="${fn:escapeXml(param.id)}" />/<c:out value="${fn:escapeXml(picture.id)}" />"> Edit or delete </a>  
			<br />
		</c:forEach>
		
		<br/ >
		<p> View in map:</p>
		<img src="http://maps.googleapis.com/maps/api/staticmap?center=${fn:escapeXml(address)}&zoom=14&size=300x300&maptype=roadmap
		&markers=color:red%7C${fn:escapeXml(address)}&sensor=false" />
</c:if>