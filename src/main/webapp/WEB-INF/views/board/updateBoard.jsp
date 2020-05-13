<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container-wrapper">
	<div class="container">
		<h2>Update Page</h2>
		<p class="lead">Fill the below information to add a product</p>

		<sf:form
			action="${pageContext.request.contextPath}/viewBoard/updateBoard/${page}?${_csrf.parameterName}=${_csrf.token}"
			method="post" modelAttribute="board">
			<sf:hidden path="boardNum" />
			<div class="form-group">
				<label for="title">title</label>
				<sf:input path="title" id="title" class="form-control" />
				<sf:errors path="title" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="text">text</label>
				<sf:textarea path="text" id="text" class="form-control" />
				<sf:errors path="text" cssStyle="color:#ff0000;" />

			</div>
			<sf:hidden path="writer" />


			<input type="submit" value="submit" class="btn btn-default">
			<a href="<c:url value="/viewBoard/${page}"/>" class="btn btn-default">Cancel</a>

		</sf:form>
		<br />
	</div>
</div>



