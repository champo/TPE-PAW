<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>${documenTitle }</title>

<link rel="stylesheet" type="text/css" href="${basePath }/css/main.css" />
</head>
<body>
	<h1>Arqvengers Property
		Administration System</h1>
	<c:if test="${not empty user}">
		<span style="float: right;"><c:out value="${user.username}" />
			<a href="${basePath }/user/logout">[logout]</a></span>
	</c:if>
	<c:if test="${empty user }">
		<span style="float: right;"><a href="${basePath }/user/login">[login]</a></span>
	</c:if>
	
	<ul style="list-style:none;">
		<li><a href="${basePath }/query">Search properties</a></li>
		<c:if test="${not empty user }">
			<li><a href="${basePath }/property/list">My properties</a></li>
		</c:if>
	</ul>
	<c:import url="/WEB-INF/jsp/${documentBodyFile }" />
</body>
</html>