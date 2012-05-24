<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul>
	<c:forEach items="${brokers }" var="broker">
		<li>
			<p>${fn:escapeXml(broker.name) } -- <a href="${basePath }/query/user?id=${broker.id }">Propiedades (${fn:escapeXml(broker.properties) })</a></p>
			<c:if test="${not empty broker.logoExtension}">
				<img class="realEstateLogo" alt="Real estate logo" src="${basePath }/images/logo_${broker.id}${broker.logoExtension}" />
			</c:if>
		</li>
	</c:forEach>
</ul>