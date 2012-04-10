<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h2>
	<c:if test="${empty edit}">
		Add New Picture
	</c:if>
	<c:if test="${not empty edit}">
		Edit Picture <c:out value="${picture.id}" />
	</c:if>
</h2>

<form action="<c:if test="${empty edit}">addPicture</c:if><c:if test="${not empty edit}">editPicture</c:if>" <c:if test="${empty edit}">enctype="multipart/form-data2</c:if> method="post">
	<input type="hidden" name="propId" value="<c:out value="${picture.propId}" />" />
	<input type="hidden" name="id" value="<c:out value="${picture.id}" />" />
	<input type="hidden" name="extension" value="<c:out value="${picture.extension}" />" />
	<div class="form-field">
		<label for="name">Name:</label>
		<input type="text" name="name" value="<c:out value="${picture.name}" />" />
	</div>
	
	<c:if test="${empty edit}">
	<div class="form-field">
		<label for="file">Source:</label>
		<input type="file" name="file" accept="image/*" size="40"/>
	</div>
	</c:if>
	
	<c:if test="${not empty edit}">
		
		Preview: <img class="preview" src="img/<c:out value="${picture.id}" />" alt="Picture #<c:out value="${picture.id}" />" />
	</c:if>
	
	<div class="form-buttons">
		<input type="submit" name="submit" value="Submit" />
	</div>	
	
</form>

<c:if test="${not empty edit}">
	<form action="deletePicture" method="post">
		<input type="hidden" value=<c:out value="${picture.id}" />" />
		<input type="submit" name="delete" value="Delete" />
	</form>
</c:if>