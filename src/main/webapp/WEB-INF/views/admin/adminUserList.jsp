<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>All Products</h2>
		<p>착한 가격으로 상품을 살펴보세요 ! ! !</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>이름</th>
					<th>이메일</th>
					<th>상태</th>
					<th>권한</th>
					<td>수정</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="user" items="${user}">
					<tr>
						<td>${user.username}</td>
						<td>${user.email}</td> 
						<td>${user.enabled}</td>
						<td>${user.authority}</td> 
						<td><a
							href="<c:url value="adminUserList/adminUserManagement/${user.userId}" />"> <i
								class="fa fa-edit"></i>
						</a> <a href="<c:url value="adminUserList/deleteUser/${user.userId}"/>"><i
								class="fa fa-times"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



