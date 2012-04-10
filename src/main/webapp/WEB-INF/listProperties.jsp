<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<h1>Properties list</h1>

<a href="addProperty">[Add new property]</a>

<ul style="padding:0;">
	<c:set var="row" value="0" />
	<c:forEach items="${properties}" var="property">
		<li class="<c:if test="${row % 2 == 0}">odd</c:if><c:if test="${row % 2 != 0}">even</c:if><c:set var="row" value="${row + 1}" />" >
			<a href="<c:url value="propertyDetail"><c:param name="id" value="${property.id}" /></c:url>">
				<c:out value="${property.id}" />
			</a>
			<br />
			<span style="font-size: 15px;">
				<c:out value="${property.description}" />
			</span>
			<a style="float: right;" href="<c:url value="editProperty"><c:param name="id" value="${property.id}" />
				</c:url>"> edit </a>
		</li>
	</c:forEach>
</ul>