<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<c:if test="${not empty fatal}">
	<p class="alert alert-error">Unexpected error processing the file. Please contact an administrator.</p>
</c:if>

<c:if test="${not empty writeError}">
	<p class="alert alert-error">Error while writing the file. Please try again.</p>
</c:if>

<div class="page-header"><h1>Register</h1></div>

<form:form action="${basePath }/user/register" method="post" modelAttribute="registerForm" enctype="multipart/form-data">

	<fieldset>
	
		<div>
			<label for="name">Name: </label>
			<form:input path="name" />
			<form:errors path="name" element="p" cssClass="alert alert-error" />
		</div>
		
		<div>
			<label for="surname">Surname: </label>
			<form:input path="surname"/>
			<form:errors path="surname" element="p" cssClass="alert alert-error" />
		</div>
		
		<div>
			<label for="email">Email: </label>
			<form:input path="email" />
			<form:errors path="email" element="p" cssClass="alert alert-error" />
		</div>

		<div>
			<label for="phone">Phone: </label>
			<form:input path="phone" />
			<form:errors path="phone" element="p" cssClass="alert alert-error" />
		</div>

		<div>
			<label for="username">Username: </label>
			<form:input path="username" />
			<form:errors path="username" element="p" cssClass="alert alert-error" />
		</div>
		<c:if test="${usernameDuplicate }">
			<p class="alert alert-error">A user with this username already exists. Choose another one.</p>
		</c:if>

		<div>
			<label for="password">Password: </label>
			<form:password path="password" showPassword="true" />
			<form:errors path="password" element="p" cssClass="alert alert-error" />
		</div>
		
		<div>
			<label for="passwordConfirm">Confirm password: </label>
			<form:password path="passwordConfirm" showPassword="true" />
			<form:errors element="p" cssClass="alert alert-error" />
		</div>
		
		<script type="text/javascript">
			function showRealEstateOptions(show) {

				div = document.getElementsByTagName("div")["realEstateOptions"];
				
				if ( show ) {
					div.className = "show";
				} else {
					div.className = "hide";
				}
			}
		</script>
		
		<div>
			<label for="userType">User type: </label>
			<form:select path="userType" onchange="showRealEstateOptions(this.options[this.selectedIndex].value=='REAL_ESTATE');">
				<form:option value="REGULAR">Regular</form:option>
				<form:option value="REAL_ESTATE">Real estate</form:option>
			</form:select>
			<form:errors path="userType" element="p" cssClass="alert alert-error" />
		</div>

		<c:if test="${empty isRealEstate }">
			<div id="realEstateOptions" class="hide">
		</c:if>
		<c:if test="${not empty isRealEstate }">
			<div id="realEstateOptions" class="show">
		</c:if>
		
			<div>
				<label for="realEstateName">Real estate name: </label>
				<form:input path="realEstateName" type="text"/>
				<form:errors path="realEstateName" element="p" cssClass="alert alert-error" />
				<c:if test="${not empty missingRealEstateNameError}">
					<br />
					<p class="alert alert-error">The field real estate name should not be empty.</p>
					<br />
				</c:if>
			</div>
			
			<div>
				<label for="logo">Logo: </label>
				<input type="file" name="logo" accept="image/*" size="40" >
				<c:if test="${not empty missingLogo}">
					<br />
					<p class="alert alert-error">The field logo should not be empty.</p>
					<br />
				</c:if>
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

		</div>

		<input type="submit" value="Register" />
	</fieldset>
</form:form>
