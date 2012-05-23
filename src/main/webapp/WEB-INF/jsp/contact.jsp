<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div>
	<c:if test="${empty publisher}">
		<div class="page-header"><h1>Contact form for property ${fn:escapeXml(property.address)} - ${fn:escapeXml(property.neighbourhood)}</h1></div>
 
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

			<div class="row">
				<div class="span7">
					<label for="comment">Comment (optional):</label>
					<br />
					<form:errors path="comment" element="p" cssClass="alert alert-error" />
	
					<form:textarea rows="5" path="comment" class="span7"/>
				</div>
			</div>
			<br />

			<input type="submit" value="Submit" class="btn btn-primary">
		</form:form>
	</c:if>

	<c:if test="${not empty publisher}">
		<div class="page-header"><h1>Contact information for property ${fn:escapeXml(property.address)} - ${fn:escapeXml(property.neighbourhood)}</h1></div>
 
		<p>
			<strong>Phone:</strong> ${fn:escapeXml(publisher.phone)}
		</p>

		<p>
			<strong>Email:</strong> ${fn:escapeXml(publisher.email)}
		</p>
	</c:if>	
</div>
