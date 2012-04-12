<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<h1>Properties list</h1>

<a href="addProperty">[Add new property]</a>

<c:if test="${empty properties}"><p>You have no properties loaded in our system.<p></c:if>

<ul style="padding:0;">
	<c:set var="row" value="0" />
	<c:forEach items="${properties}" var="property">
		<li class="<c:if test="${row % 2 == 0}">odd</c:if><c:if test="${row % 2 != 0}">even</c:if><c:set var="row" value="${row + 1}" />" >
			<a href="<c:url value="propertyDetail"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>">
				<c:out value="${fn:escapeXml(property.id)}" />
			</a>
			<br />
			<span style="font-size: 15px;">
				<c:out value="${fn:escapeXml(property.description)}" />
			</span>
			<a style="float: right;margin:0px 10px;" href="<c:url value="editProperty"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>"> edit </a>

			<c:if test="${!property.published}"><a style="float: right;margin:0px 10px;" href="<c:url value="publishProperty"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>">publish</a></c:if>
			<c:if test="${property.published}"><a style="float: right;margin:0px 10px;" href="<c:url value="unpublishProperty"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>">unpublish</a></c:if>
		</li>
	</c:forEach>
</ul>