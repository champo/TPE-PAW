<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${user != null }">
<p>You can see the list of your published properties <a href="${pageContext.request.contextPath}/property/list">here.</a> </p>
</c:if>
<c:if test="${user == null }">
<p>You might want to <a href="${pageContext.request.contextPath}/user/login">login</a> or <a href="${pageContext.request.contextPath}/user/register">register</a> if you're new here.</p>
</c:if>
