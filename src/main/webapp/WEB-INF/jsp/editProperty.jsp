<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="page-header"><h1>
	<c:if test="${empty edit}">
		Add New Property
	</c:if>
	<c:if test="${not empty edit}">
		Edit Property <c:out value="${fn:escapeXml(param.id)}" />
	</c:if>
</h1></div>


<c:set var="url">
<c:if test="${empty edit}">property/add</c:if>
<c:if test="${not empty edit}">property/edit?id=${fn:escapeXml(param.id)}</c:if>
</c:set>

<form:form action="${basePath }/${url }" method="post" commandName="propertyForm">
	
	<form:errors path="*"/>

	<div class="row">
		<div class="span8">
			<p class="row">
				<div class="span3">
					<label for="propertyType">Property type:</label>
					<form:select path="propertyType">
						<form:option value='HOUSE'>House</form:option>
						<form:option value='FLAT'>Flat</form:option>
					</form:select>
				</div>
				
				<div class="span3 offset1">
					<label for="operationType">Operation type:</label>
					<form:select path="operationType">
						<form:option value="SELLING">Selling</form:option>
						<form:option value="LEASING">Leasing</form:option>
					</form:select>
				</div>
			</p>
			
			<p class="row">
				<div class="span3">
					<label for="address">Address:</label>
					<form:input path="address" />
				</div>
				
				<div class="span3 offset1">
					<label for="neighbourhood">Neighbourhood:</label>
					<form:input path="neighbourhood"/>
				</div>
			</p>
			
			<p class="row">
				<div class="span3">
					<label for="price">Price:</label>
					<form:input path="price"/>
				</div>
			</p>
			
			<p class="row">
				<div class="span3">
					<label for="numRooms">Rooms:</label>
					<form:input path="numRooms"/>
				</div>
			</p>
			
			<p class="row">
				<div class="span3">
					<label for="indoorSpace">Indoor Space:</label>
					<form:input path="indoorSpace"/>
				</div>
				
				<div class="span3 offset1">
					<label for="outdoorSpace">Outdoor Space:</label>
					<form:input path="outdoorSpace" />
				</div>
			</p>
			
			<p class="row">
				<div class="span7">
					<label for="description">Description:</label>
					<form:textarea path="description" rows="5" class="span7"/>
				</div>
			</p>
			
			<p class="row">
				<div class="span3">
					<label for="antiquity">Antiquity:</label>
					<form:input path="antiquity" />
				</div>
			</p>
				
			<p class="row">
				<c:set var="count" value="1" />	
				<c:forEach var="service" items="${services}">
					<div class="span2">
						 <label for="services<c:out value="${count}"></c:out>" class="checkbox inline">
							<input id="services<c:out value="${count}"></c:out>" name="services" type="checkbox" value="<c:out value="${fn:escapeXml(service.name)}"></c:out>"<c:if test="${service.present}"> checked="checked"</c:if>/>
						 	<spring:message code="${fn:escapeXml(service.name)}"/>
						 </label> 
					</div>
					<c:set var="count" value="${count + 1}" scope="page"/>
				</c:forEach>
			</p>
			
			<p class="row">&nbsp</p>
			
			<p class="row">
				<div class="span3">
					<input type="submit" name="submit" value="Submit" class="btn btn-primary"/>
				</div>
			</p>
		</div>
		<c:if test="${not empty edit}">
			<div class="span4">
				<p> View in map:</p>
				<p>
					<img src="http://maps.googleapis.com/maps/api/staticmap?center=${fn:escapeXml(address)}&zoom=14&size=300x300&maptype=roadmap&markers=color:red%7C${fn:escapeXml(address)}&sensor=false" />
				</p>
			</div>
		</c:if>
	</div>
</form:form>

<hr />
<br />

<c:if test="${not empty edit}">
	Rooms of this property:
	<br />
	<a href="${basePath }/property/addRoom/<c:out value="${fn:escapeXml(param.id)}" />"> Add new room</a>
	<br />
	<br />
	<ul>
		<c:forEach var="room" items="${rooms}">
			<li>
				<strong><c:out value="${fn:escapeXml(room.name)}" />:</strong> 
				length: <c:out value="${fn:escapeXml(room.length)}" />,
				width: <c:out value="${fn:escapeXml(room.width)}" />
			</li>
		</c:forEach>
	</ul>
	<br />
	
</c:if>

<c:if test="${not empty edit}">
		Pictures of this property:
		<br />
		<a href="${basePath }/picture/add/<c:out value="${fn:escapeXml(param.id)}" />"> Add new </a>
		<br />
		<br />
		<div class="row">
			<c:forEach var="picture" items="${pictures}">
				<div class="span4">
					<p>
						<c:out value="${fn:escapeXml(picture.name)}"></c:out>
					</p>
					<p>
						<img class="propPicture" alt="Image of the property" src="${basePath }/images/<c:out value="${fn:escapeXml(picture.id)}"></c:out><c:out value="${fn:escapeXml(picture.extension)}"></c:out>" />
					</p>
					<p>
						<a href="${basePath }/picture/edit?id=<c:out value="${fn:escapeXml(picture.id)}" />"> Edit or delete </a>  
					</p>
				</div>
			</c:forEach>
		</div>
		<br />
</c:if>
