<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section id="content">
	<div class="  bg-image bg-img1">
		<div class="container">
			<br /> <br /> <br /> <br />

			<div class="orderInfo text-light">
				<c:forEach items="${adminOrderListDetail}"
					var="adminOrderListDetail" varStatus="status">

					<c:if test="${status.first}">
						<p>
							<span>수령인: </span>${adminOrderListDetail.recipient}</p>
						<p>
							<span>주소: </span>${adminOrderListDetail.address}</p>
						<p>
							<span>가격: </span>${adminOrderListDetail.orderprice} 원
						</p>
					</c:if>

				</c:forEach>
			</div> 

			<div class="container">
				<c:forEach items="${adminOrderListDetail}"
					var="adminOrderListDetail">
					<div class="row">
						<div class="col-sm-12">
							<div class="box-shadow-full">
								<div class="row">

									<div class="col-md-4">
										<img
											src="<c:url value="/resources/images/${adminOrderListDetail.imageFilename}"/>"
											alt="image" style="width: 80%" />
									</div>
									<div class="pt-4 pt-md-0">
										<p class="lead">
										<ul class="orderView">
											<li>
												<div class="col-md-8">
													<p>
														<span>품명: </span>${adminOrderListDetail.name}<br /> <span>단가:
														</span>${adminOrderListDetail.price} 원<br /> <span>수량: </span>${adminOrderListDetail.amount}
														개<br /> <span>금액: </span>${adminOrderListDetail.price * adminOrderListDetail.amount}
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


			<br /> <br />
		</div>
	</div>
</section>