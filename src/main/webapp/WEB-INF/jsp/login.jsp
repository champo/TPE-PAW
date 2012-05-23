<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="page-header"><h1>Login</h1></div>

<c:if test="${invalidCredentials }">
	<p class="alert alert-error">No user with those credentials was found.</p>
</c:if>

<p>Dont have an account? <a href="${basePath }/user/register">Register!</a></p>
<br />

<form action="${basePath }/user/login?from=${fn:escapeXml(param.from) }" method="post">
	<fieldset>
		<p>
			<label for="username">Username: </label>
			<input type="text" name="username" value="${fn:escapeXml(username) }"/><br />
			
			<label for="rememberName" class="checkbox inline">
				<input type="checkbox" name="rememberName"/> Remember username
			</label>
		</p> 

		<p>
			<label for="password">Password: </label>
			<input type="password" name="password" value="${fn:escapeXml(param.password) }"/><br />
			
			<label for="rememberMe" class="checkbox inline">
				<input type="checkbox" name="rememberMe" /> Remember login
			</label>
		</p>
		
		<br />
	
		<input type="submit" value="Login" class="btn"/>
	</fieldset>
</form>