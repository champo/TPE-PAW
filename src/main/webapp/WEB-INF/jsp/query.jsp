<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>Query</h1>

<div>
	<c:if test="${param.unpublished}">
		<p class="error">The property you were trying to access is unpublished. You no longer have to access to it.</p>
	</c:if>
	
	<form:errors path="pageNumber" element="p" cssClass="error" />
</div>

<div>
	<form:form action="${basePath }/query" method="get" commandName="propertyQuery">

		<div>
			<label for="operation">Type of operation: </label>
	
			<form:radiobutton path="operation" value="ANY"/>Any
			<form:radiobutton path="operation" value="SELLING"/>Selling
			<form:radiobutton path="operation" value="LEASING"/>Leasing

			<form:errors path="operation" element="p" cssClass="error" />
		</div>
		

		<div>
			<label for="property">Type of property: </label>
	
			<form:radiobutton path="property" value="ANY"/>Any
			<form:radiobutton path="property" value="FLAT"/>Flat
			<form:radiobutton path="property" value="HOUSE"/>House

			<form:errors path="property" element="p" cssClass="error" />
		</div>

		<div>
			<label for="range">Price</label>
	
			from
			<form:input path="rangeFrom"/>
			to
			<form:input path="rangeTo" type="text"/>
			with
			<form:select path="order">
				<form:option value="ASCENDING">Ascending</form:option>
				<form:option value="DESCENDING">Descending</form:option>
			</form:select>
			order
			
			<form:errors path="rangeFrom" element="p" cssClass="error" />
			<form:errors path="rangeTo" element="p" cssClass="error" />
			<form:errors path="order" element="p" cssClass="error" />
		</div>

		<input type="submit" value="Query" />

	</form:form>
</div>
<br />

<div>
	<c:if test="${not empty queryResults}">
		<c:forEach var="result" items="${queryResults}">
			<p>
				<ul style="list-style:none;">
					<li>
						<c:choose>
							<c:when test="${result.operationType=='SELLING'}">
								Selling	
							</c:when>
							<c:otherwise>
								Leasing
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${result.propertyType=='HOUSE'}">
								house.
							</c:when>
							<c:otherwise>
								flat.
							</c:otherwise>
						</c:choose>
				
		
					<li>Address: ${fn:escapeXml(result.address)}</li>
					<li>Neighbourhood: ${fn:escapeXml(result.neighbourhood)}</li>
					<li>Price: ${result.price}</li>
					<li><a href="${basePath }/property/showDetail?id=${result.id}">See more</a></li>
				</ul>
			</p>
		</c:forEach>
		<p>
			<c:if test="${pageNumber != 1}">
				<a href="${pageURL}1">First</a>
				<a href="${pageURL}${pageNumber - 1}">Previous</a>
			</c:if>
			
			<c:forEach var="page" begin="${paginationFrom}" end="${pageNumber - 1}" step="1">
				<a href="${pageURL}${page}">${page}</a>
			</c:forEach>

			${pageNumber}
			
			<c:forEach var="page" begin="${pageNumber + 1}" end="${paginationTo}" step="1">
				<a href="${pageURL}${page}">${page}</a>
			</c:forEach>

			<c:if test="${pageNumber != lastPage}">
				<a href="${pageURL}${pageNumber + 1}">Next</a>
				<a href="${pageURL}${lastPage}">Last</a>
			</c:if>
		</p>
	</c:if>
</div>
