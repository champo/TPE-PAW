<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Query</h1>

<div>
	<c:if test="${invalidRange}">
		<p class="error">Invalid price range.</p>
	</c:if>
	
	<form action="/query" method="get">
	
		<label for="operation">Type of operation: </label>
		<input type="radio" name="operation" value="any" checked="checked">Any
		<input type="radio" name="operation" value="selling" />Selling
		<input type="radio" name="operation" value="leasing" />Leasing
		<br />
		
		<label for="property">Type of property: </label>
		<input type="radio" name="property" value="any" checked="checked">Any
		<input type="radio" name="property" value="flat" />Flat
		<input type="radio" name="property" value="house" />House
		<br />
		
		<label for="range">Price range:
		from <input type="text" name="rangeFrom"> to <input type="text" name="rangeTo">
		<br />
		
		<input type="submit" value="Query">
	
	</form>
</div>
<br />

<div>
	<c:forEach var="result" items="queryResults">
		<p>
			<ul>
				<li>${result.operationType}</li>
				<li>${result.propertyType}</li>
				<li>${result.address}</li>
				<li>${result.neighbourhood}</li>
				<li>${result.price}</li>				
			</ul>
		</p>
	</c:forEach>
</div>
