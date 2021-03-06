<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tilan.domain.*"%>

<jsp:include page="../include/header.jsp" />
<section id="main">

	<h1>Add Computer</h1>
	
	<form action="addComputer" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" />
					<span class="erreur">${errors['name']}</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introduced" pattern="YYYY-MM-dd"/>
					<span class="help-inline">YYYY-MM-DD</span>
					<span class="erreur">${errors['introduced']}</span>
					
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinued" pattern="YYYY-MM-dd"/>
					<span class="help-inline">YYYY-MM-DD</span>
					<span class="erreur">${errors['discontinued']}</span>
					
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value = 0>-- </option>
							<c:forEach items="${requestScope.companies}" var="company">
								 <option value = "${company.id}">${company.name}</option>
							</c:forEach>
					</select>
				</div>		
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary">
			or <a href="listAllComputers.aspx" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="../include/footer.jsp" />