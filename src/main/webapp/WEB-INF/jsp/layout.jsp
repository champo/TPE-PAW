<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
	<title>${documentTitle }</title>
	
	<link rel="stylesheet" type="text/css" href="${basePath }/css/main.css" />
	<link rel="stylesheet" type="text/css" href="${basePath }/css/bootstrap.min.css" />
</head>

<body style="padding-top:70px">

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="${basePath}/index">
					<img style="height:22px;border:none" alt="A" src="${basePath}/images/arqvengers.png" />
					rqvengers PropAdS
				</a>
				<ul class="nav">

					<c:if test="${documentBodyFile == 'index.jsp'}">
						<li class="active"><a href="${basePath}/index">Home</a></li>
					</c:if>
					<c:if test="${documentBodyFile != 'index.jsp'}">
						<li><a href="${basePath}/index">Home</a></li>
					</c:if>

					<c:if test="${documentBodyFile == 'query.jsp'}">
						<li class="active"><a href="${basePath }/query">Search properties</a></li>
					</c:if>
					<c:if test="${documentBodyFile != 'query.jsp'}">
						<li><a href="${basePath }/query">Search properties</a></li>
					</c:if>
					<li class="divider-vertical"></li>
					<c:if test="${documentBodyFile == 'query.jsp'}">
                        <li class="active"><a href="${basePath }/brokers">Brokers</a></li>
					</c:if>
					<c:if test="${documentBodyFile != 'query.jsp'}">
                        <li><a href="${basePath }/brokers">Brokers</a></li>
					</c:if>

					<c:if test="${not empty user }">
						<li class="divider-vertical"></li>
						
						<c:if test="${documentBodyFile == 'listProperties.jsp'}">
							<li class="active"><a href="${basePath }/property/list">My properties</a></li>
						</c:if>
						<c:if test="${documentBodyFile != 'listProperties.jsp'}">
							<li><a href="${basePath }/property/list">My properties</a></li>
						</c:if>

						<li class="divider-vertical"></li>
					</c:if>
				</ul>
				<ul class="nav pull-right">
					<c:if test="${not empty user}">
						<li><p class="navbar-text" style="color:white"><c:out value="${user.username}" /></p></li>
						<li><a href="${basePath }/user/logout">logout</a></li>
					</c:if>
					<c:if test="${empty user }">
						<li><a href="${basePath }/user/login">login</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<c:import url="/WEB-INF/jsp/${documentBodyFile }" />
	</div>

</body>
</html>
