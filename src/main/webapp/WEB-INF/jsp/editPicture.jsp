<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${not empty noPermissions}">
	<p class="alert alert-error">You don't have permissions to view this page</p>
</c:if>

<c:if test="${not empty fatal}">
	<p class="alert alert-error">Unexpected error processing the file. Please contact an administrator.</p>
</c:if>

<c:if test="${not empty writeError}">
	<p class="alert alert-error">Error while writing the file. Please try again.</p>
</c:if>

<c:if test="${not empty deleteError}">
	<p class="alert alert-error">Error while deleting the file. Please try again.</p>
</c:if>

<c:if test="${empty noPermissions}">
<c:if test="${empty fatal}">

<div class="page-header"><h1>
	<c:if test="${empty edit}">
		Add New Picture
	</c:if>
	<c:if test="${not empty edit}">
		Edit Picture <c:out value="${fn:escapeXml(picture.id)}" />
	</c:if>
</h1></div>

<form action="${basePath }/<c:if test="${empty edit}">picture/add</c:if><c:if test="${not empty edit}">picture/edit</c:if>" <c:if test="${empty edit}">enctype="multipart/form-data"</c:if> method="post">
	<input type="hidden" name="propId" value="<c:out value="${fn:escapeXml(picture.property.id)}" />" />
	<input type="hidden" name="id" value="<c:out value="${fn:escapeXml(picture.id)}" />" />
	<input type="hidden" name="extension" value="<c:out value="${fn:escapeXml(picture.extension)}" />" />
	
	<div class="row">
		<div class="span3">
			<label for="name">Name:</label>
			<input type="text" name="name" value="<c:out value="${fn:escapeXml(picture.name)}" />" />
			<c:if test="${not empty nameError}">
					<p class="alert alert-error">Error: The name cannot be empty nor longer than 50 characters.</p>
			</c:if>
		</div>
	
		<c:if test="${empty edit}">
			<div class="span3 offset1">
				<label for="file">Source:</label>
				<input type="file" name="file" accept="image/*" size="40"/>
				
				<c:if test="${not empty fileError}">
					<br />
					<p class="alert alert-error">That is not a valid file.</p>
					<br />
				</c:if>
				<c:if test="${not empty extensionError}">
					<br />
					<p class="alert alert-error">The file is not a picture. .gif, .png and .jpg are accepted.</p>
					<br />
				</c:if>
			</div>
		</c:if>
	</div>
	
	<c:if test="${not empty edit}">
		<br/>
		<p>
			Preview: <br />
			<img class="propPicture" name="picture" src="${basePath }/images/<c:out value="${fn:escapeXml(picture.id)}" /><c:out value="${fn:escapeXml(picture.extension)}" />" alt="Picture #<c:out value="${fn:escapeXml(picture.id)}" />" />
		</p>
	</c:if>
	
	<br />
	<br />
	<div>
		<input type="submit" name="submit" value="Submit" class="btn btn-primary"/>
	</div>	
	
</form>

<c:if test="${not empty edit}">
	<form action="${basePath }/picture/edit" method="post">
		<input type="hidden" name="id" value="<c:out value="${fn:escapeXml(picture.id)}" />" />
		<input type="submit" name="delete" value="Delete" class="btn"/>
	</form>
</c:if>

</c:if>
</c:if>