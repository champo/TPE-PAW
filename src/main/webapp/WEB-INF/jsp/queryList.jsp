<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
	</c:if>
</div>