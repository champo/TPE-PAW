<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Register</h1>

<form action="/register" method="post">
	<fieldset>
		<label for="name">Name: </label>
		<input type="text" name="name" value="${fn:escapeXml(param.name)}" /><br />
		
		<label for="surname">Surname: </label>
		<input type="text" name="surname" value="${fn:escapeXml(param.surname) }" /><br />
		
		<label for="email">Email: </label>
		<input type="text" name="email" value="${fn:escapeXml(param.email) }"/><br />

		<label for="phone">Phone: </label>
		<input type="text" name="phone" value="${fn:escapeXml(param.phone) }"/><br />

		<label for="username">Username: </label>
		<input type="text" name="username" value="${fn:escapeXml(param.username) }"/><br />

		<label for="password">Password: </label>
		<input type="text" name="password" value="${fn:escapeXml(param.password) }"/><br />

		<label for="passwordConfirmation">Confirm password: </label>
		<input type="text" name="passwordConfirmation" value="${fn:escapeXml(param.passwordConfirmation) }"/><br />
		
		<input type="submit" value="Register" />
	</fieldset>
</form>
