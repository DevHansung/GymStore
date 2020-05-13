<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="bg-image bg-img1">
	<div class="container">
		<br> <br> <br> <br>
		<div class="jumbotron">
			<h1 class="text-primary">주문 상세</h1>
			<div class="orderInfo">
				<c:forEach items="${orderListDetail}" var="orderListDetail"
					varStatus="status">

					<c:if test="${status.first}">
						<p>
							<span>수령인: </span>${orderListDetail.recipient}</p>
						<p>
							<span>주소: </span>${orderListDetail.address}</p>
						<p>
							<span>주문금액: </span>${orderListDetail.orderprice} 원
						</p>
					</c:if>
				</c:forEach>
			</div>
		</div>

		<div class="container">
			<c:forEach items="${orderListDetail}" var="orderListDetail">
				<div class="row">
					<div class="col-sm-12">
						<div class="box-shadow-full">
							<div class="row">

								<div class="col-md-4">
									<img
										src="<c:url value="/resources/images/${orderListDetail.imageFilename}"/>"
										alt="image" style="width: 80%" />
								</div>
								<div class="pt-4 pt-md-0">
									<p class="lead">
									<ul class="orderView">
										<li>
											<div class="col-md-8">
												<p>
													<span>품명: </span>${orderListDetail.name}<br /> <span>단가:
													</span>${orderListDetail.price} 원<br /> <span>수량: </span>${orderListDetail.amount}
													개<br /> <span>금액: </span>${orderListDetail.price * orderListDetail.amount}
													원
												</p>
											</div>
										</li>
									</ul>
								</div>

							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
