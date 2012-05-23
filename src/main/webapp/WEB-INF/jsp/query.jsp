<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="page-header"><h1>Query</h1></div>

<div>
	<c:if test="${param.unpublished}">
		<p class="alert alert-error">The property you were trying to access is unpublished. You no longer have to access to it.</p>
	</c:if>
	
	<form:errors path="pageNumber" element="p" cssClass="alert alert-error" />
</div>

<div class="well">
	<form:form action="${basePath }/query" method="get" commandName="propertyQuery">

		<p>
			<label for="operation">Type of operation: </label>

			<label class="radio inline">
				<form:radiobutton path="operation" value="ANY"/> Any
			</label>
			<label class="radio inline">
				<form:radiobutton path="operation" value="SELLING"/> Selling
			</label>
			<label class="radio inline">
				<form:radiobutton path="operation" value="LEASING"/> Leasing
			</label>

			<form:errors path="operation" element="p" cssClass="alert alert-error" />
		</p>
		

		<p>
			<label for="property">Type of property: </label>
	
			<label class="radio inline">
				<form:radiobutton path="property" value="ANY"/> Any
			</label>
			<label class="radio inline">
				<form:radiobutton path="property" value="FLAT"/> Flat
			</label>
			<label class="radio inline">
				<form:radiobutton path="property" value="HOUSE"/> House
			</label>

			<form:errors path="property" element="p" cssClass="alert alert-error" />
		</p>

		<p>
			<label for="range">Price</label>
	
			from
			<form:input path="rangeFrom" type="text" class="input-small"/>
			to
			<form:input path="rangeTo" type="text" class="input-small"/>
			with
			<form:select path="order" class="span2">
				<form:option value="ASCENDING">Ascending</form:option>
				<form:option value="DESCENDING">Descending</form:option>
			</form:select>
			order
			
			<form:errors path="rangeFrom" element="p" cssClass="alert alert-error" />
			<form:errors path="rangeTo" element="p" cssClass="alert alert-error" />
			<form:errors path="order" element="p" cssClass="alert alert-error" />
		</p>

		<input type="submit" value="Query" class="btn btn-primary"/>

	</form:form>
</div>
<br />

<div>
	<c:if test="${not empty queryResults}">
        <c:import url="/WEB-INF/jsp/queryList.jsp" />
		<p>
			<div class="pagination pagination-centered">
				<ul>
					<c:if test="${pageNumber != 1}">
						<li><a href="${pageURL}1">First</a></li>
						<li><a href="${pageURL}${pageNumber - 1}">Previous</a></li>
					</c:if>
					<c:if test="${pageNumber == 1}">
						<li class="disabled"><a href="${pageURL}1">First</a></li>
						<li class="disabled"><a href="${pageURL}${pageNumber - 1}">Previous</a></li>
					</c:if>

					<c:forEach var="page" begin="${paginationFrom}" end="${pageNumber - 1}" step="1">
						<li><a href="${pageURL}${page}">${page}</a></li>
					</c:forEach>
		
					<li class="active"><a href="#">${pageNumber}</a></li>
					
					<c:forEach var="page" begin="${pageNumber + 1}" end="${paginationTo}" step="1">
						<li><a href="${pageURL}${page}">${page}</a></li>
					</c:forEach>
		
					<c:if test="${pageNumber != lastPage}">
						<li><a href="${pageURL}${pageNumber + 1}">Next</a></li>
						<li><a href="${pageURL}${lastPage}">Last</a></li>
					</c:if>
					<c:if test="${pageNumber == lastPage}">
						<li class="disabled"><a href="${pageURL}${pageNumber + 1}">Next</a></li>
						<li class="disabled"><a href="${pageURL}${lastPage}">Last</a></li>
					</c:if>
				</ul>
			</div>
		</p>
	</c:if>
</div>
