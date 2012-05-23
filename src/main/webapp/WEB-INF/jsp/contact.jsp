<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div>
	<div class="page-header"><h2>Contact form for ${fn:escapeXml(property.address)} - ${fn:escapeXml(property.neighbourhood)}</h2></div> 
	<c:if test="${empty publisher}">
		<form:form action="${basePath}/contact?propertyId=${fn:escapeXml(param.propertyId)}" method="post" commandName="contactForm">

			<div>
				<label for="name">Name: </label>
				<form:input type="text" path="name" />

				<form:errors path="name" element="p" cssClass="alert alert-error" />
			</div>
			<br />

			<div>
				<label for="email">Email: </label>
				<form:input type="text" path="email"/>

				<form:errors path="email" element="p" cssClass="alert alert-error" />
			</div>
			<br />

			<div>
				<label for="phone">Phone: </label>
				<form:input type="text" path="phone"/>

				<form:errors path="phone" element="p" cssClass="alert alert-error" />
			</div>
			<br />

			<div>
				<label for="comment">Comment (optional):</label>
				<br />
				<form:errors path="comment" element="p" cssClass="alert alert-error" />

				<form:textarea rows="5" cols="50" path="comment" />
			</div>
			<br />

			<input type="submit" value="Submit">
		</form:form>
	</c:if>
	<c:if test="${not empty publisher}">
		<div class="page-header"><h2>Contact information</h2></div>
		<p>
		Phone: ${fn:escapeXml(publisher.phone)}
		<br />
		Email: ${fn:escapeXml(publisher.email)}
		</p>
	</c:if>	
</div>
