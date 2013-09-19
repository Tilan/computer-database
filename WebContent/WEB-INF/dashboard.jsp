<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tilan.domain.*"%>

<jsp:include page="../include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">${requestScope.numberOfComputers}  Computer(s) found</h1>
	<div id="actions">
		<form action="searchComputer" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer.aspx">Add Computer</a>
<!-- 		passer par une servlet -->
	</div>

		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.computers}" var="computer" >
					
					<tr>
						<td><a href="<c:url value="editComputer?idComputerSelected=${computer.id}"/>" onclick="">${computer.name}</a></td>
						<td>${computer.introducedFormatted}</td>
						<td>${computer.discontinuedFormatted}</td>
						<td>${computer.company.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul>
		<c:if test="${numPage > 1}">
		<a href="listAllComputers.aspx?page=${numPage - 1}"><button style="background:none;" class="btn primary" type="button" value="next">Précédent</button></a>
		</c:if>
		<c:if test="${numPage < (requestScope.numberOfComputers/15)}">
		<a href="listAllComputers.aspx?page=${numPage + 1}"><button style="margin-left:20px; background:none;" class="btn primary" type="button" value="next">Suivant</button></a>
		</c:if>
		</ul>
</section>

<jsp:include page="../include/footer.jsp" />
