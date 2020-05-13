<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="bg-image bg-img1">
	<div class="container">
		<br> <br> <br> <br>
		<c:forEach items="${orderList}" var="orderList">
			<div class="row">
				<div class="col-sm-12">
					<div class="box-shadow-full">
						<div class="row">
							<div class="col-md-12">
								<ul class="listOrder">
									<li>
										<div>
											<p>
												<span>주문번호</span><a
													href="${pageContext.request.contextPath}/order/orderListDetail/${orderList.orderid}?${_csrf.parameterName}=${_csrf.token}">${orderList.orderid}</a>
											</p>

											<p>
												<span>수령인: </span>${orderList.recipient}</p>
											<p>
												<span>주소: </span>${orderList.address}</p>
											<p>
												<span>가격: </span>${orderList.orderprice} 원
											</p>
											<p>
												<span>배송 상태: </span>${orderList.delivery}</p>
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
					href="<c:url value="/order/orderList?page=${startPageNum - 1}"/>">이전</a>
					]
				</span>
			</c:if>

			<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
				<span> <a class="text-light"
					href="<c:url value="/order/orderList?page=${page}"/>">${page}</a>
				</span>
			</c:forEach>

			<c:if test="${next}">
				<span>[ <a class="text-light"
					href="<c:url value="/order/orderList?page=${endPageNum + 1}"/>">다음</a> ]
				</span>
			</c:if>
		</div>
	</div>
	<br> <br>
</div>