<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div class="container-wrapper">
	<div class="container">

		<div class="jumbotron">
			<h1 class="text-primary">productInventory</h1>
			<p>제품 재고 현황입니다.</p>
			<p>전체 상품 수: ${count}</p>
		</div>

		<div class="row">
			<div class="table-responsive">
				<table class="table table-hover table-striped bg-light text-body">
					<thead>
						<tr class="bg-secondary">
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
						<c:forEach var="ProductList" items="${ProductList}">
							<tr>
								<td><img
									src="<c:url value="/resources/images/${ProductList.imageFilename}"/>"
									alt="image" style="width: 100%" /></td>
								<td>${ProductList.name}</td>
								<td>${ProductList.category}</td>
								<td>${ProductList.price}</td>
								<td>${ProductList.manufacturer}</td>
								<td>${ProductList.unitInStock}</td>
								<td>${ProductList.description}</td>
								<td><a
									href="<c:url value="/admin/adminProductInventory/adminDeleteProduct/?page=${page}&productid=${ProductList.productid}&${_csrf.parameterName}=${_csrf.token}"/>"><i
										class="fa fa-times"></i></a> <a
									href="<c:url value="/admin/adminProductInventory/adminUpdateProduct/?page=${page}&productid=${ProductList.productid}&${_csrf.parameterName}=${_csrf.token}"/>"><i
										class="fa fa-edit"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="text-dark">
			<c:if test="${prev}">
				<span>[ <a class="text-dark"
					href="<c:url value="/admin/adminProductInventory?page=${startPageNum - 1}"/>">이전</a>
					]
				</span>
			</c:if>

			<c:forEach begin="${startPageNum}" end="${endPageNum}" var="page">
				<span> <a class="text-dark"
					href="<c:url value="/admin/adminProductInventory?page=${page}"/>">${page}</a>
				</span>
			</c:forEach>

			<c:if test="${next}">
				<span>[ <a class="text-dark"
					href="<c:url value="/admin/adminProductInventory?page=${endPageNum + 1}"/>">다음</a>
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
				class="btn btn-outline-primary">Accessory</a>
		</div>
		<a
			href="<c:url value="/admin/adminProductInventory/adminAddProduct?page=${page}"/>"
			class="btn btn-primary"> Add Product</a>
	</div>
</div>
