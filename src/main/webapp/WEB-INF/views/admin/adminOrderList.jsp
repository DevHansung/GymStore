<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="  bg-image bg-img1">

	<div class="container">
		<br> <br> <br> <br>
		<c:forEach items="${adminOrderList}" var="adminOrderList">
			<div class="row">
				<div class="col-sm-12">
					<div class="box-shadow-full">
						<div class="row">
							<div class="col-md-12">
								<ul class="OrderList">
									<li>
										<div class="text-dark">
											<p>
												<span>주문번호: </span><a class="text-dark"
													href="<c:url value="/admin/adminOrderListDetail/${adminOrderList.orderid}?${_csrf.parameterName}=${_csrf.token}"/>">${adminOrderList.orderid}</a>
											</p>

											<p>
												<span>주문자: </span>${adminOrderList.username}</p>
											<p>
												<span>수령인: </span>${adminOrderList.recipient}</p>
											<p>
												<span>주소: </span>${adminOrderList.address}</p>
											<p>
												<span>가격: </span>${adminOrderList.orderprice} 원
											</p>
											<p>
												<span>배송 상태: </span>${adminOrderList.delivery}</p>

											<div class="deliveryChange">
												<form role="form" method="post"
													action="<c:url value="/admin/adminOrderUpdateDelivery?page=${page}&${_csrf.parameterName}=${_csrf.token}"/>"
													class="deliveryForm">

													<input type="hidden" name="orderid"
														value="${adminOrderList.orderid}" /> <input type="hidden"
														name="delivery" value="배송중" />

													<button type="submit" id="btnUpdate"
														class="btn btn-outline-primary">배송중</button>

												</form>

												<form role="form" method="post"
													action="<c:url value="/admin/adminOrderUpdateDelivery?page=${page}&${_csrf.parameterName}=${_csrf.token}"/>"
													class="deliveryForm">

													<input type="hidden" name="orderid"
														value="${adminOrderList.orderid}" /> <input type="hidden"
														name="delivery" value="배송지연" />

													<button type="submit" id="btnUpdate"
														class="btn btn-outline-primary">배송 지연</button>

												</form>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

		<div class="text-light">
			<c:if test="${prev}">
				<span>[ <a class="text-light"
					href="<c:url value="/admin/adminOrderList?page=${startPageNum - 1}"/>">이전</a>
					]
				</span>
			</c:if>

			<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
				<span> <a class="text-light"
					href="<c:url value="/admin/adminOrderList?page=${page}"/>">${page}</a>
				</span>
			</c:forEach>

			<c:if test="${next}">
				<span>[ <a class="text-light"
					href="<c:url value="/admin/adminOrderList?page=${endPageNum + 1}"/>">다음</a>
					]
				</span>
			</c:if>
		</div>
	</div>
</div>
