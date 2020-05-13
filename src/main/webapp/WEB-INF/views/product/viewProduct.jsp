<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<div class="container-wrapper">
	<div class="container" ng-app="cartApp">
		<h2>Products Detail</h2>
		<p class="lead">Here is the detail information of the product</p>

		<div class="row" ng-controller="cartCtrl">
			<div class="col-md-6">
				<img
					src="<c:url value="/resources/images/${product.imageFilename}"/>"
					alt="image" style="width: 80%" />
			</div>
			<div class="col-md-6">
				<h3>품명: ${product.name}</h3><br>

				<p>
					<strong> 브랜드: </strong> ${product.manufacturer}
				</p>
				<p>
					<strong>설명: </strong> ${product.description}
				</p>
				<p>
					<strong> 가격: </strong> ${product.price} 원
				</p>

				<c:if test="${pageContext.request.userPrincipal.name!=null}">
					<form name="form1" method="post"
						action="${pageContext.request.contextPath}/insertCart?${_csrf.parameterName}=${_csrf.token}">
						<input type="hidden" name="productid" value="${product.productid}">
						<input type="hidden" name="productname" value="${product.name}">
						<input type="hidden" name="productprice" value="${product.price}">
						<select name="amount">
							<c:forEach begin="1" end="10" var="i">
								<option value="${i}">${i}</option>
							</c:forEach>
						</select>개
						<p>
							<button class="btn btn-warning btn-large">
								<i class="fa fa-shopping-cart"></i> Order Now
							</button>
							<a href="<c:url value="/cartList" />" class="btn btn-info"> <i
								class="fa fa-eye"></i> View Cart
							</a>
						</p>
						<br />
					</form>
				</c:if>

				<c:if test="${pageContext.request.userPrincipal.name==null}">
					<p>주문하려면 로그인 하세요.</p>
					<a href="<c:url value="/login"/>" class="btn btn-info"> Login </a>
				</c:if>

				<c:if test="${categoty == null}">
					<a href="<c:url value="/products?page=${page}"/>"
						class="btn btn-danger">Back</a>
				</c:if>
				<c:if test="${categoty != null}">
					<a
						href="<c:url value="/productCategory?page=${page}&category=${category}"/>"
						class="btn btn-danger">Back</a>
				</c:if>
				<c:if
					test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities =='[ROLE_ADMIN]'}">
					<a
						href="<c:url value="/admin/adminProductCategory?page=${page}&category=${category}"/>"
						class="btn btn-danger">Backk</a>
				</c:if>
			</div>
		</div>
	</div>
</div>