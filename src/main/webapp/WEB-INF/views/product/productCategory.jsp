<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div class="bg-image bg-img1">
	<div class="container">
		<br> <br> <br> <br>
		<div class="jumbotron">
			<h1 class="text-primary">productCategory</h1>
		</div>
		<div>
			<a href="<c:url value="/products?page=1"/>"
				class="btn btn-outline-primary">All</a> <a
				href="<c:url value="/productCategory?page=1&category=Outer"/>"
				class="btn btn-outline-primary">Outer</a> <a
				href="<c:url value="/productCategory?page=1&category=Top"/>"
				class="btn btn-outline-primary">Top</a> <a
				href="<c:url value="/productCategory?page=1&category=Pants"/>"
				class="btn btn-outline-primary">Pants</a> <a
				href="<c:url value="/productCategory?page=1&category=Bag"/>"
				class="btn btn-outline-primary">Bag</a> <a
				href="<c:url value="/productCategory?page=1&category=Shoes"/>"
				class="btn btn-outline-primary">Shoes</a> <a
				href="<c:url value="/productCategory?page=1&category=Accessory"/>"
				class="btn btn-outline-primary">Accessory</a>
		</div>
		<br>

		<div class="container">
			<div class="row">
				<c:forEach var="productCategory" items="${productCategory}">
					<div class="col-md-3">

						<div class="card ">
							<img
								src="<c:url value="/resources/images/${productCategory.imageFilename}"/>"
								alt="image" style="width: 50%" />
							<div class="card-body">
								<h4 class="card-title">상품명: ${productCategory.name}</h4>
								<p class="card-text">제조사: ${productCategory.manufacturer}</p>
								<p class="card-text">가격: ${productCategory.price}원</p>

								<a
									href="<c:url value="/viewProduct?page=${page}&productid=${productCategory.productid}&category=null" />"
									class="btn btn-primary">상품 보기</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<br>
		<div class="text-light">
			<c:if test="${prev}">
				<span>[ <a class="text-light"
					href="<c:url value="/productCategory?page=${startPageNum - 1}&category=${category}"/>">이전</a>
					]
				</span>
			</c:if>

			<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
				<span> <a class="text-light"
					href="<c:url value="/productCategory?page=${page}&category=${category}"/>">${page}</a>
				</span>
			</c:forEach>

			<c:if test="${next}">
				<span>[ <a class="text-light"
					href="<c:url value="/productCategory?page=${endPageNum + 1}&category=${category}"/>">다음</a>
					]
				</span>
			</c:if>
		</div>
	</div>
</div>