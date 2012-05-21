<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>
	<c:if test="${empty edit}">
		Add New Room
	</c:if>
	<c:if test="${not empty edit}">
		Edit Room <c:out value="${fn:escapeXml(param.id)}" />
	</c:if>
</h2>


<form:form action="${basePath }/${url }" method="post" commandName="roomForm">
	
	<form:errors path="*"/>

	<div>
		<label for="name">Name:</label>
		<form:input path="name" />
	</div>
	
	<div>
		<label for="length">Length:</label>
		<form:input path="length" />
	</div>
	
	<div>
		<label for="width">Width:</label>
		<form:input path="width" />
	</div>
	
	<div>
		<input type="submit" name="submit" value="Submit" />
	</div>	
	
</form:form>