<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:if test="${empty hasPermissions}">
	<p class="error">You don't have permissions to view this page</p>
</c:if>



<c:if test="${not empty hasPermissions}">
	<h2>
			Add New Room
	</h2>
	
	<c:set var="url">
		property/addRoom/${property.id}
	</c:set>
	
	
	<form:form action="${basePath }/${url }" method="post" commandName="roomForm">
		
		<form:errors path="*" cssClass="error"/>
	
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
</c:if>