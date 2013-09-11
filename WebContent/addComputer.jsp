<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tilan.domain.*"%>

<jsp:include page="include/header.jsp" />
<section id="main">

	<h1>Add Computer</h1>
	
	<form action="addComputer.jsp" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" />
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introduced" pattern="YY-MM-dd"/>
					<span class="help-inline">YY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinued" pattern="YY-MM-dd"/>
					<span class="help-inline">YY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
					<c:set var="i" value="0"/>
						<c:forEach items="${requestScope.companies}" var="company">
							<c:set var="i" value="i++"/>
<%-- 							<c:out option value="${i++}">${company.name}</option> --%>
						</c:forEach>
					</select>
				</div>		
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary">
			or <a href="dashboard.jsp" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />