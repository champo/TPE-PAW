<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Register</h1>

<form action="${basePath }/register" method="post">
	<fieldset>
		<label for="name">Name: </label>
		<input type="text" name="name" value="${fn:escapeXml(param.name)}" /><br />
		<c:if test="${nameEmpty }">
			<p class="error">The field 'name' cannot be empty.</p>
		</c:if>
		<c:if test="${nameBadLength }">
			<p class="error">The field 'name' has to be shorter than 50 characters.</p>
		</c:if>
		
		<label for="surname">Surname: </label>
		<input type="text" name="surname" value="${fn:escapeXml(param.surname) }" /><br />
		<c:if test="${surnameEmpty }">
			<p class="error">The field 'surname' cannot be empty.</p>
		</c:if>
		<c:if test="${surnameBadLength }">
			<p class="error">The field 'surname' has to be shorter than 50 characters.</p>
		</c:if>
		
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

		<label for="username">Username: </label>
		<input type="text" name="username" value="${fn:escapeXml(param.username) }"/><br />
		<c:if test="${usernameEmpty }">
			<p class="error">The field 'username' cannot be empty.</p>
		</c:if>
		<c:if test="${usernameBadLength }">
			<p class="error">The field 'username' has to be shorter than 50 characters.</p>
		</c:if>
		<c:if test="${usernameDuplicate }">
			<p class="error">A user with this username already exists. Choose another one.</p>
		</c:if>

		<label for="password">Password: </label>
		<input type="password" name="password" value="${fn:escapeXml(param.password) }"/><br />
		<c:if test="${passwordEmpty }">
			<p class="error">The field 'password' cannot be empty.</p>
		</c:if>

		<label for="passwordConfirmation">Confirm password: </label>
		<input type="password" name="passwordConfirmation" value="${fn:escapeXml(param.passwordConfirmation) }"/><br />
		<c:if test="${passwordConfirmationDoesntMatch }">
			<p class="error">The passwords don't match</p>
		</c:if>
		
		<input type="submit" value="Register" />
	</fieldset>
</form>
