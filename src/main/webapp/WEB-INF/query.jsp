<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h1>Query</h1>

<c:if test="${param.unpublished}">
	<p class="error">The property you were trying to access is unpublished. You no longer have to access to it.</p>
</c:if>

<div>
	<c:if test="${invalidRange}">
		<p class="error">Invalid price range.</p>
	</c:if>

	<form action="${basePath }/query" method="get">

		<label for="operation">Type of operation: </label>

		<c:choose>
			<c:when test="${operationChecked == 'any'}">
				<input type="radio" name="operation" value="any" checked="checked" />
			</c:when>
			<c:otherwise>
				<input type="radio" name="operation" value="any" />
			</c:otherwise>
		</c:choose>
		Any
		
		<c:choose>
			<c:when test="${operationChecked == 'selling'}">
				<input type="radio" name="operation" value="selling" checked="checked" />
			</c:when>
			<c:otherwise>
				<input type="radio" name="operation" value="selling" />
			</c:otherwise>
		</c:choose>
		Selling
		
		<c:choose>
			<c:when test="${operationChecked == 'leasing'}">
				<input type="radio" name="operation" value="leasing" checked="checked" />
			</c:when>
			<c:otherwise>
				<input type="radio" name="operation" value="leasing" />
			</c:otherwise>
		</c:choose>
		Leasing
		
		<br />

		<label for="property">Type of property: </label>

		<c:choose>
			<c:when test="${propertyChecked == 'any'}">
				<input type="radio" name="property" value="any" checked="checked" />
			</c:when>
			<c:otherwise>
				<input type="radio" name="property" value="any" />
			</c:otherwise>
		</c:choose>
		Any
		
		<c:choose>
			<c:when test="${propertyChecked == 'flat'}">
				<input type="radio" name="property" value="flat" checked="checked" />
			</c:when>
			<c:otherwise>
				<input type="radio" name="property" value="flat" />
			</c:otherwise>
		</c:choose>
		Flat
		
		<c:choose>
			<c:when test="${propertyChecked == 'house'}">
				<input type="radio" name="property" value="house" checked="checked" />
			</c:when>
			<c:otherwise>
				<input type="radio" name="property" value="house" />
			</c:otherwise>
		</c:choose>
		House
		
		<br />

		<label for="range">Price</label>
		from
		<input type="text" name="rangeFrom" value="${fn:escapeXml(rangeFromValue)}" />
		to
		<input type="text" name="rangeTo" value="${fn:escapeXml(rangeToValue)}" />
		<br />
		<input type="submit" value="Query" />

	</form>
</div>
<br />

<div>
	<c:if test="${not empty queryResults}">
		<c:forEach var="result" items="${queryResults}">
			<p>
				<ul style="list-style:none;">
					<li>
						<c:choose>
							<c:when test="${result.operationType==0}">
								Selling	
							</c:when>
							<c:otherwise>
								Leasing
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${result.propertyType==0}">
								house.
							</c:when>
							<c:otherwise>
								flat.
							</c:otherwise>
						</c:choose>
				
		
					<li>Address: ${fn:escapeXml(result.address)}</li>
					<li>Neighbourhood: ${fn:escapeXml(result.neighbourhood)}</li>
					<li>Price: ${result.price}</li>
					<li><a href="${basePath }/propertyDetail?id=${result.id}">See more</a></li>
				</ul>
			</p>
		</c:forEach>
	</c:if>
</div>
