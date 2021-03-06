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
		<a class="btn success" id="add" href="addComputer">Add Computer</a>

	</div>

		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th><a href="<c:url value="listAllComputers.aspx?attribute=name"/>">Computer Name</a></th>
					<!-- Table header for Introduced Date -->
					<th><a href="<c:url value="listAllComputers.aspx?attribute=introduced"/>">Introduced Date</a></th>
					<!-- Table header for Discontinued Date -->
					<th><a href="<c:url value="listAllComputers.aspx?attribute=discontinued"/>">Discontinued Date</a></th>
					<!-- Table header for Company -->
					<th><a href="<c:url value="listAllComputers.aspx?attribute=company"/>">Company</a></th>
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
		<c:if test="${numPage > 1 && isSearch == false}">
		<a href="listAllComputers.aspx?page=${numPage - 1}&attribute=${tri}"><button style="background:none;" class="btn primary" type="button" value="next">Précédent</button></a>
		</c:if>
		<c:if test="${numPage < (requestScope.numberOfComputers/15) && isSearch == false}">
		<a href="listAllComputers.aspx?page=${numPage + 1}&attribute=${tri}"><button style="margin-left:20px; background:none;" class="btn primary" type="button" value="next">Suivant</button></a>
		</c:if>
		<c:if test="${numPage > 1 && isSearch == true}">
		<a href="searchComputer?page=${numPage - 1}&search=${search}"><button style="background:none;" class="btn primary" type="button" value="next">Précédent</button></a>
		</c:if>
		<c:if test="${numPage < (requestScope.numberOfComputers/15) && isSearch == true}">
		<a href="searchComputer?page=${numPage + 1}&search=${search}"><button style="margin-left:20px; background:none;" class="btn primary" type="button" value="next">Suivant</button></a>
		</c:if>
		</ul>
</section>

<jsp:include page="../include/footer.jsp" />
