<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h1>Query</h1>

<div>
	<c:if test="${invalidRange}">
		<p class="error">Invalid price range.</p>
	</c:if>

	<form action="/query" method="get">

		<label for="operation">Type of operation: </label>
		<input type="radio" name="operation" value="any" ${operationAnyChecked} />Any
		<input type="radio" name="operation" value="selling" ${operationSellingChecked} />Selling
		<input type="radio" name="operation" value="leasing" ${operationLeasingChecked} />Leasing
		<br />
		<label for="property">Type of property: </label>
		<input type="radio" name="property" value="any" ${propertyAnyChecked} />Any
		<input type="radio" name="property" value="flat" ${propertyFlatChecked} />Flat
		<input type="radio"	name="property" value="house" ${propertyHouseChecked} />House
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
			<p><ul style="list-style:none;">
				<li><c:choose>
						<c:when test="${result.operationType==0}">
						Selling	
					</c:when>
						<c:otherwise>
						Leasing
					</c:otherwise>
					</c:choose> <c:choose>
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
				<li><a href="propertyDetail?id=${result.id}">See more</a></li>
			</ul>
			</p>
			</c:forEach>
	</c:if>
</div>
