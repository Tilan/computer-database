<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tilan.domain.*"%>

<jsp:include page="../include/header.jsp" />
<section id="main">

	<h1>Edit Computer</h1>
	
	<form action="editComputer" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name *: </label>
				<div class="input">
					<input type="text" name="name" value="${computer.name}"/>
					<span class="erreur">${errors['name']}</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date *:</label>
				<div class="input">
					<input type="date" name="introduced" pattern="YYYY-MM-dd" value="${computer.introducedFormatted}"> 
					<span class="help-inline">YYYY-MM-DD</span>
					<span class="erreur">${errors['introduced']}</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date *: </label>
				<div class="input">
					<input type="date" name="discontinued" pattern="YYYY-MM-dd" value="${computer.discontinuedFormatted}"/>
					<span class="help-inline">YYYY-MM-DD</span>
					<span class="erreur">${errors['discontinued']}</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name :</label>
				<div class="input">
					<select name="company">
						<option value = 0> ${computer.company.name} </option>
							<c:forEach items="${requestScope.companies}" var="company">
								 <c:if test="${computer.company.id != company.id}">
								 	<option value ="${company.id}"> ${company.name}</option>
								 </c:if>
							</c:forEach>
					</select>
				</div>		
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Edit" class="btn primary" name="actionDone">
			<input type="submit" value="Delete" class="btn primary" name="actionDone">
			or <a href="listAllComputers.aspx" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="../include/footer.jsp" />