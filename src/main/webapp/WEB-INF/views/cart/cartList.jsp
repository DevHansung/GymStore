<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document).ready(function() {
		// 리스트 페이지로 이동
		$("#btnList").click(function() {
			location.href = "${path}/product";
		});
	});
</script>

<div class=" intro bg-image bg-img1">
	<div class="overlay-itro">
		<div class="container container-wrapper">
			<div class="jumbotron">
				<div class="container">
					<h2>Cart</h2>

					<p class="text-dark">All the selected products in your shopping
						cart</p>
				</div>
			</div>

			<section class="container">
				<c:choose>
					<c:when test="${count == 0}">
            장바구니가 비어있습니다.
        </c:when>
					<c:otherwise>

						<form name="form1" method="post">


							<table class="table table-hover">
								<tr>
									<th>상품명</th>
									<th>단가</th>
									<th>수량</th>
									<th>금액</th>
									<th>취소</th>
								</tr>

								<c:forEach var="cart" items="${cart}" varStatus="i">
									<tr>
										<td>${cart.productname}</td>
										<td>${cart.productprice}</td>
										<td><input type="number" style="width: 40px"
											name="amount" value="${cart.amount}" min="1"> <input
											type="hidden" name="productid" value="${cart.productid}"></td>
										<td>${cart.totalprice}</td>
										<td><a
											href="${pageContext.request.contextPath}/cartList/deleteCart/${cart.id}?${_csrf.parameterName}=${_csrf.token}">삭제</a>
										</td>
									</tr>
								</c:forEach>

								<tr>
									<td colspan="5" align="right">※30000원 이상시 배송료 무료<br>
									<br> 장바구니 금액 합계 : ${sumMoney}won <br> 배송료 : ${fee}won<br>
										전체 주문금액 :${allSum}won
									</td>
								</tr>
							</table>
							<input type="hidden" name="count" value="${count}">
							<button class="btn btn-outline-primary" type="submit"
								id="btnUpdate"
								formaction="${pageContext.request.contextPath}/cartList/updateCart?${_csrf.parameterName}=${_csrf.token}">수정</button>
							<button class="btn btn-outline-primary" type="submit"
								id="btnUpdate"
								formaction="${pageContext.request.contextPath}/order/orderForm?${_csrf.parameterName}=${_csrf.token}">주문</button>
							<a href="<c:url value="/products?page=1"/>"
								class="btn btn-outline-primary">쇼핑 계속하기</a>
						</form>
					</c:otherwise>
				</c:choose>

			</section>
		</div>
	</div>
</div>

