<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${not empty noPermissions}">
	<p class="error">You don't have permissions to view this page</p>
</c:if>

<c:if test="${not empty noPermissions}">
	<p class="error">Unexpected error processing the file. Please contact an administrator.</p>
</c:if>

<c:if test="${not empty writeError}">
	<p class="error">Error while writing the file. Please try again.</p>
</c:if>

<c:if test="${empty noPermissions}">
<c:if test="${empty fatal}">
<h2>
	<c:if test="${empty edit}">
		Add New Picture
	</c:if>
	<c:if test="${not empty edit}">
		Edit Picture <c:out value="${fn:escapeXml(picture.id)}" />
	</c:if>
</h2>

<form action="<c:if test="${empty edit}">addPicture</c:if><c:if test="${not empty edit}">editPicture</c:if>" <c:if test="${empty edit}">enctype="multipart/form-data"</c:if> method="post">
	<input type="hidden" name="propId" value="<c:out value="${fn:escapeXml(picture.propId)}" />" />
	<input type="hidden" name="id" value="<c:out value="${fn:escapeXml(picture.id)}" />" />
	<input type="hidden" name="extension" value="<c:out value="${fn:escapeXml(picture.extension)}" />" />
	<div>
		<label for="name">Name:</label>
		<input type="text" name="name" value="<c:out value="${fn:escapeXml(picture.name)}" />" />
	</div>
	<c:if test="${not empty nameError}">
			<br />
			<p class="error">Error: The name was empty.</p>
			<br />
	</c:if>
	
	<c:if test="${empty edit}">
	<div>
		<label for="file">Source:</label>
		<input type="file" name="file" accept="image/*" size="40"/>
		
		<c:if test="${not empty fileError}">
			<br />
			<p class="error">That is not a valid file.</p>
			<br />
		</c:if>
		<c:if test="${not empty extensionError}">
			<br />
			<p class="error">The file is not a picture. .gif, .png and .jpg are accepted.</p>
			<br />
		</c:if>
	</div>
	</c:if>
	
	<c:if test="${not empty edit}">
		Preview: <br />
		<img class="propPicture" name="picture" src="images/<c:out value="${fn:escapeXml(picture.id)}" /><c:out value="${fn:escapeXml(picture.extension)}" />" alt="Picture #<c:out value="${fn:escapeXml(picture.id)}" />" />
	</c:if>
	
	<div>
		<input type="submit" name="submit" value="Submit" />
	</div>	
	
</form>

<c:if test="${not empty edit}">
	<form action="editPicture" method="post">
		<input type="hidden" name="id" value="<c:out value="${fn:escapeXml(picture.id)}" />" />
		<input type="hidden" name="propId" value="<c:out value="${fn:escapeXml(picture.propId)}" />" />
		<input type="submit" name="delete" value="Delete" />
	</form>
</c:if>

</c:if>
</c:if>