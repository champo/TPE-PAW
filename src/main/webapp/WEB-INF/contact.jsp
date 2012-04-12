<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div>
	<h2>Contact form for ${fn:escapeXml(address)} - ${fn:escapeXml(neighbourhood)}</h2> 
	<c:if test="${empty publisher}">
		<form action="/contact?propertyId=${fn:escapeXml(param.propertyId)}" method="post">
			<label for="name">Name: </label>
			<input type="text" name="name" value="${fn:escapeXml(param.name)}" /><br />
			<c:if test="${nameEmpty }">
				<p class="error">The field 'name' cannot be empty.</p>
			</c:if>
			<c:if test="${nameBadLength }">
				<p class="error">The field 'name' has to be shorter than 50 characters.</p>
			</c:if>
			<br />
			
			<label for="email">Email: </label>
			<input type="text" name="email" value="${fn:escapeXml(param.email) }"/><br />
			<c:if test="${emailEmpty }">
				<p class="error">The field 'email' cannot be empty.</p>
			</c:if>
			<c:if test="${emailBadLength }">
				<p class="error">The field 'email' has to be shorter than 50 characters.</p>
			</c:if>
			<c:if test="${emailInvalidFormat }">
				<p class="error">The field 'email' has to be a valid email address.</p>
			</c:if>
			<br />
						
			<label for="phone">Phone: </label>
			<input type="text" name="phone" value="${fn:escapeXml(param.phone) }"/><br />
			<c:if test="${phoneEmpty }">
				<p class="error">The field 'phone' cannot be empty.</p>
			</c:if>
			<c:if test="${phoneBadLength }">
				<p class="error">The field 'phone' has to be shorter than 20 characters.</p>
			</c:if>
			<c:if test="${phoneInvalidFormat }">
				<p class="error">The field 'phone' can only contain numbers.</p>
			</c:if>
			<br />
			
			<label for="comment">Comment (optional):</label>
			<br />
			<textarea rows="5" cols="50" name="comment">${fn:escapeXml(param.comment)}</textarea>
			<c:if test="${CommentBadLength }">
				<p class="error">The field 'comment' has to be shorter than 1000 characters.</p>
			</c:if>
			<br />
			<input type="submit" value="Submit">
		</form>
	</c:if>
	<c:if test="${not empty publisher}">
		<h2>Contact information</h2>
		<p>
		Phone: ${fn:escapeXml(publisher.phone)}
		<br />
		Email: ${fn:escapeXml(publisher.email)}
		</p>
	</c:if>	
</div>
