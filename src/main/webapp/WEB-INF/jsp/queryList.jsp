<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div>
	<c:if test="${not empty queryResults}">
		<table class="table">
			<tbody>
				<c:forEach var="result" items="${queryResults}">
					<tr><td>
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
					</td></tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>