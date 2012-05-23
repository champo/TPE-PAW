<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="page-header"><h1>Property list for ${fn:escapeXml(searchUser.username) }</h1></div>

<c:import url="/WEB-INF/jsp/queryList.jsp" />