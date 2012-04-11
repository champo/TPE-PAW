<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
		<title>${documenTitle }</title>
		
		<link rel="stylesheet" type="text/css" href="/css/main.css" />
	</head>
	<body>
	<div style="overflow: auto;">
			<h1 style="float: left; margin-top: 0;">Arqvengers Property Administration System</h1>
			<c:if test="${not empty user}">
				<span style="float: right;"><c:out value="${user.username}" /> [<a href="logout">logout]</a></span>
			</c:if>
		</div>
		<c:import url="/WEB-INF/${documentBodyFile }" />
	</body>
</html>