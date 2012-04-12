<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h1>Query</h1>

<div>
	<c:if test="${invalidRange}">
		<p class="error">Invalid price range.</p>
	</c:if>

	<form action="/query" method="get">

		<label for="operation">Type of operation: </label> <input type="radio"
			name="operation" value="any" checked="checked">Any <input
			type="radio" name="operation" value="selling" />Selling <input
			type="radio" name="operation" value="leasing" />Leasing <br /> <label
			for="property">Type of property: </label> <input type="radio"
			name="property" value="any" checked="checked">Any <input
			type="radio" name="property" value="flat" />Flat <input type="radio"
			name="property" value="house" />House <br /> <label for="range">Price
			from <input type="text" name="rangeFrom"> to <input
			type="text" name="rangeTo"> <br /> <input type="submit"
			value="Query">
	</form>
</div>
<br />

<div>
	<c:if test="${not empty queryResults}">
		<c:forEach var="result" items="${queryResults}">
			<p><ul styl e="list-style:none;">
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
				
		
				<li>Address: ${result.address}</li>
				<li>Neighbourhood: ${result.neighbourhood}</li>
				<li>Price: ${result.price}</li>
				<li><a href="propertyDetail?id=${result.id}">See more</a></li>
			</ul>
			</p>
			</c:forEach>
	</c:if>
</div>
