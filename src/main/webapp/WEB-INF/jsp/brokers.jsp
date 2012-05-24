<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul>
	<div class="row">
		<c:forEach items="${brokers }" var="broker">
			<div class="alert alert-info span3">		
				<p>
					<div class="row" style="height:100px; display: table-cell; vertical-align:middle;">
						<div class="span2">
							<c:if test="${not empty broker.logoExtension}">
								<img class="realEstateLogo" alt="Logo" src="${basePath }/images/logo_${broker.id}${broker.logoExtension}" />
							</c:if>
						</div>
						<div class="span1" style="margin: 0px;">
							<br />
							<strong>${fn:escapeXml(broker.name)}</strong>
						</div>
					</div>
				</p>
				<p style="text-align: center;">
					<strong><a href="${basePath }/query/user?id=${broker.id }">${fn:escapeXml(broker.properties)} properties</a></strong>
				</p>
			</div>
		</c:forEach>
	</div>
</ul>