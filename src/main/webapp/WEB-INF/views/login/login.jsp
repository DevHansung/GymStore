<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="  bg-image bg-img1">
	<div class="container">
		<br />
		<br />
		<br />
		<br />
		<h2 class="text-primary">Login</h2>

		<c:if test="${not empty errorMsg}">
			<div style="color: #ff0000;">
				<h3 class="text-danger">${errorMsg}</h3>
			</div>
		</c:if>

		<c:if test="${not empty logoutMsg}">
			<div style="color: #0000ff;">
				<h3 class="text-success">${logoutMsg}</h3>
			</div>
		</c:if>

		<form action="<c:url value="/login"/>" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" placeholder="Enter username"
					name="username" style="width: 50%">
			</div>

			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" placeholder="Enter password"
					name="password" style="width: 50%">
			</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button type="submit" class="btn btn-outline-primary">Submit</button>

		</form>
		<br />
		<br />
	</div>
</div>
