<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper"> 

	<div class="container">
		<h2>Administrator Page</h2>
		<p class="lead">관리자 페이지입니다.</p>
	</div>

	<div class="container">
		<a href="<c:url value="/admin/adminProductInventory?page=1"/>">
			<h2>Admin Product</h2>

		</a> <a href="<c:url value="/admin/adminViewBoard?page=1"/>">
			<h2>Admin View board</h2>

		</a> <a href="<c:url value="/admin/adminUserList"/>">
			<h2>Admin User management</h2>
			
		</a> </a> <a href="<c:url value="/admin/adminOrderList?page=1"/>">
			<h2>Admin Order management</h2>
		</a>

	</div>

</div>