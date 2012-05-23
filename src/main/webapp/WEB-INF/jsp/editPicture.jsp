<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${not empty noPermissions}">
	<p class="error">You don't have permissions to view this page</p>
</c:if>

<c:if test="${not empty writeError}">
	<p class="error">Error while writing the file. Please try again.</p>
</c:if>

<c:if test="${not empty deleteError}">
	<p class="error">Error while deleting the file. Please try again.</p>
</c:if>

<c:if test="${not empty param.maxUploadSizeError}">
	<p class="error">Error while uploading the file. The maximum file size is <spring:message code="MAXUPLOADSIZE"/> .</p>
</c:if>

<c:if test="${empty noPermissions}">

	<h2>
		<c:if test="${empty edit}">
			Add New Picture
		</c:if>
		<c:if test="${not empty edit}">
			Edit Picture <c:out value="${fn:escapeXml(picture.id)}" />
		</c:if>
	</h2>
	
	<c:set var="url">
		<c:if test="${empty edit}">picture/add/${property.id}</c:if>
		<c:if test="${not empty edit}">picture/edit/${picture.property.id}/${picture.id}</c:if>
	</c:set>
	
	<c:set var="form">
		<c:if test="${empty edit}">newPictureForm</c:if>
		<c:if test="${not empty edit}">editPictureForm</c:if>
	</c:set>
	
	<form:form action="${basePath }/${url}" enctype="multipart/form-data" method="post" commandName="${form}">
		
		<form:errors path="*" cssClass="error"/>
		
		<div>
			<label for="name">Name:</label>
			<form:input path="name" />
		</div>
		
		<c:if test="${empty edit}">
		<div>
			<label for="file">Source:</label>
			<input type="file" name="file" accept="image/*" size="40"/>
		</div>
		</c:if>
		
		<c:if test="${not empty edit}">
			Preview: <br />
			<img class="propPicture" name="picture" src="${basePath }/images/<c:out value="${fn:escapeXml(picture.id)}" /><c:out value="${fn:escapeXml(picture.extension)}" />" alt="Picture #<c:out value="${fn:escapeXml(picture.id)}" />" />
		</c:if>
		
		<div>
			<input type="submit" name="submit" value="Submit" />
		</div>	
		
	</form:form>
	
	<c:if test="${not empty edit}">
		<form action="${basePath }/picture/delete/${picture.property.id}/${picture.id}" method="post">
			<input type="submit" name="delete" value="Delete" />
		</form>
	</c:if>

</c:if>