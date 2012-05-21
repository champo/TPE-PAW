<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<h1>Register</h1>

<form:form action="${basePath }/user/register" method="post" modelAttribute="registerForm">

	<fieldset>
	
		<div>
			<label for="name">Name: </label>
			<form:input path="name" />
			<form:errors path="name" element="p" cssClass="error" />
		</div>
		
		<div>
			<label for="surname">Surname: </label>
			<form:input path="surname"/>
			<form:errors path="surname" element="p" cssClass="error" />
		</div>
		
		<div>
			<label for="email">Email: </label>
			<form:input path="email" />
			<form:errors path="email" element="p" cssClass="error" />
		</div>

		<div>
			<label for="phone">Phone: </label>
			<form:input path="phone" />
			<form:errors path="phone" element="p" cssClass="error" />
		</div>

		<div>
			<label for="username">Username: </label>
			<form:input path="username" />
			<form:errors path="username" element="p" cssClass="error" />
		</div>
		<c:if test="${usernameDuplicate }">
			<p class="error">A user with this username already exists. Choose another one.</p>
		</c:if>

		<div>
			<label for="password">Password: </label>
			<form:password path="password" showPassword="true" />
			<form:errors path="password" element="p" cssClass="error" />
		</div>
		
		<div>
			<label for="passwordConfirm">Confirm password: </label>
			<form:password path="passwordConfirm" showPassword="true" />
			<form:errors element="p" cssClass="error" />
		</div>
		
		<input type="submit" value="Register" />
	</fieldset>
</form:form>
