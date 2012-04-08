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
		<c:import url="/WEB-INF/${documentBodyFile }" />
	</body>
</html>