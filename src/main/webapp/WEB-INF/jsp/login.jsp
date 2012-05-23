<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="page-header"><h1>Login</h1></div>

<c:if test="${invalidCredentials }">
	<p class="alert alert-error">No user with those credentials was found.</p>
</c:if>

<p>Dont have an account? <a href="${basePath }/user/register">Register!</a></p>

<form action="${basePath }/user/login?from=${fn:escapeXml(param.from) }" method="post">
	<fieldset>
		<label for="username">Username: </label>
		<input type="text" name="username" value="${fn:escapeXml(username) }"/><br />
		
		<label for="rememberName">Remember username</label>
		<input type="checkbox" name="rememberName" /><br /> 

		<label for="password">Password: </label>
		<input type="password" name="password" value="${fn:escapeXml(param.password) }"/><br />
		
		<label for="rememberMe">Remember login </label>
		<input type="checkbox" name="rememberMe" /><br/>
		
		<input type="submit" value="Login" />
	</fieldset>
</form>