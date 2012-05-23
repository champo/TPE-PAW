<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="page-header"><h1>Properties list</h1></div>

<a href="${basePath }/property/add">Add new property</a>

<br />
<br />

<c:if test="${empty properties}"><p class="alert">You have no properties loaded in our system.<p></c:if>

<table class="table">
	<tbody>
		<c:forEach items="${properties}" var="property">
			<tr><td>
				<a href="<c:url value="/property/showDetail"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>">
					Property <c:out value="${fn:escapeXml(property.id)}" />
				</a>
				<br />
				<span style="font-size: 15px;">
					<c:out value="${fn:escapeXml(property.description)}" />
				</span>
				<a style="float: right;margin:0px 10px;" href="<c:url value="/property/edit"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>"> edit </a>
	
				<c:if test="${!property.published}"><a style="float: right;margin:0px 10px;" href="<c:url value="/property/publish"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>">publish</a></c:if>
				<c:if test="${property.published}"><a style="float: right;margin:0px 10px;" href="<c:url value="/property/unpublish"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>">unpublish</a></c:if>
				<c:if test="${!property.reserved}"><a style="float: right;margin:0px 10px;" href="<c:url value="/property/reserve"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>">reserve</a></c:if>
				<c:if test="${property.reserved}"><a style="float: right;margin:0px 10px;" href="<c:url value="/property/unreserve"><c:param name="id" value="${fn:escapeXml(property.id)}" /></c:url>">unreserve</a></c:if>
			</tr></td>
		</c:forEach>
	</tbody>
</table>
