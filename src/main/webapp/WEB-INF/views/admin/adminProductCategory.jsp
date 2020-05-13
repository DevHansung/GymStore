<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div class="container container-wrapper">
	<div class="jumbotron">
		<h1 class="text-primary">productCategory</h1>
		<p>카테고리별 제품 재고 현황입니다</p>
		<p>상품 수: ${count}</p>
	</div>

	<div class="row">
		<div class="table-responsive">
			<table class="table table-hover table-striped bg-light text-body">
				<thead>
					<tr class="bg-secondary ">
						<th>Photo Thumb</th>
						<th>Name</th>
						<th>Category</th>
						<th>Price</th>
						<th>Manufacturer</th>
						<th>UnitInStock</th>
						<th>Description</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="productCategory" items="${productCategory}">
						<tr class=" ">
							<td><img
								src="<c:url value="/resources/images/${productCategory.imageFilename}"/>"
								alt="image" style="width: 50%" /></td>
							<td>${productCategory.name}</td>
							<td>${productCategory.category}</td>
							<td>${productCategory.price}</td>
							<td>${productCategory.manufacturer}</td>
							<td>${productCategory.unitInStock}</td>
							<td>${productCategory.description}</td>
							<td><a
								href="<c:url value="/viewProduct?page=${page}&productid=${productCategory.productid}&category=${category}" />">
									<i class="fa fa-info-circle"></i>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

	<div class="text-light">
		<c:if test="${prev}">
			<span>[ <a class="text-dark"
				href="<c:url value="/adminadmin/adminProductCategory?page=${startPageNum - 1}&category=${category}"/>">이전</a>
				]
			</span>
		</c:if>

		<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
			<span> <a class="text-dark"
				href="<c:url value="/admin/adminProductCategory?page=${page}&category=${category}"/>">${page}</a>
			</span>
		</c:forEach>

		<c:if test="${next}">
			<span>[ <a class="text-dark"
				href="<c:url value="/admin/adminProductCategory?page=${endPageNum + 1}&category=${category}"/>">다음</a>
				]
			</span>
		</c:if>
	</div>

	<div>
		<a
			href="<c:url value="/admin/adminProductCategory?page=1&category=Outer"/>"
			class="btn btn-outline-primary">Outer</a> <a
			href="<c:url value="/admin/adminProductCategory?page=1&category=Top"/>"
			class="btn btn-outline-primary">Top</a> <a
			href="<c:url value="/admin/adminProductCategory?page=1&category=Pants"/>"
			class="btn btn-outline-primary">Pants</a> <a
			href="<c:url value="/admin/adminProductCategory?page=1&category=Bag"/>"
			class="btn btn-outline-primary">Bag</a> <a
			href="<c:url value="/admin/adminProductCategory?page=1&category=Shoes"/>"
			class="btn btn-outline-primary">Shoes</a> <a
			href="<c:url value="/admin/adminProductCategory?page=1&category=Accessory"/>"
			class="btn btn-outline-primary">Accessory</a> <a
			href="<c:url value="/admin/adminProductInventory?page=1"/>"
			class="btn btn-outline-primary">All View</a>
	</div>
</div>
