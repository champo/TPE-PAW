<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div>
	<c:if test="${empty validates}">
		<c:if test="${invalidId}">
			<p class="error">Invalid user.</p>
		</c:if>
		<c:if test="${invalidName}">
			<p class="error">Empty or invalid name.</p>
		</c:if>
		<c:if test="${invalidEmail}">
			<p class="error">Empty or invalid email.</p>
		</c:if>
		<c:if test="${invalidPhone}">
			<p class="error">Empty or invalid phone.</p>
		</c:if>
	
		<form action="/contact?id=${fn:escapeXml(id)}" method="post">
			<label for="name">Name: </label>
			<input type="text" name="name" value="${fn:escapeXml(nameValue)}"/>
			<br />
			<label for="email">Email: </label>
			<input type="text" name="email" value="${fn:escapeXml(emailValue)}"/>
			<br />
			<label for="phone">Phone: </label>
			<input type="text" name="phone" value="${fn:escapeXml(phoneValue)}"/>
			<br />
			<label for="comment">Comment (optional):</label>
			<br />
			<textarea rows="5" cols="50" name="comment">${fn:escapeXml(commentValue)}</textarea>
			<br />
			<input type="submit" value="Submit">
		</form>
	</c:if>
	<c:if test="${not empty validates}">
		<h2>Contact information</h2>
		<p>
		Phone: ${fn:escapeXml(user.phone)}
		<br />
		Email: ${fn:escapeXml(user.email)}
		</p>
	</c:if>	
</div>
