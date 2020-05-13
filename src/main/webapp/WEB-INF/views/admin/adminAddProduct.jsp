<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container-wrapper">
	<div class="container">
		<h2>Add Page</h2>
		<p class="lead">Fill the below information to add a product</p>



		<sf:form
			action="${pageContext.request.contextPath}/admin/adminProductInventory/adminAddProduct?page=${page}&${_csrf.parameterName}=${_csrf.token}"
			method="post" modelAttribute="product" enctype="multipart/form-data">

			<div class="form-group">
				<label for="name">Name</label>
				<sf:input path="name" id="name" class="form-control" />
				<sf:errors path="name" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="category">Category: </label>
				<sf:radiobutton path="category" id="category" value="Outer" />
				Outer
				<sf:radiobutton path="category" id="category" value="Top" />
				Top
				<sf:radiobutton path="category" id="category" value="Pants" />
				Pants
				<sf:radiobutton path="category" id="category" value="Bag" />
				Bag
				<sf:radiobutton path="category" id="category" value="Shoes" />
				Shoes
				<sf:radiobutton path="category" id="category" value="Accessory" />
				Accessory
			</div>

			<div class="form-group">
				<label for="description">Description</label>
				<sf:textarea path="description" id="description"
					class="form-control" />
			</div>

			<div class="form-group">
				<label for="price">Price</label>
				<sf:input path="price" id="price" class="form-control" />
				<sf:errors path="price" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="unitInStock">Unit In Stock</label>
				<sf:input path="unitInStock" id="unitInStock" class="form-control" />
				<sf:errors path="unitInStock" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="manufacturer">Manufacturer</label>
				<sf:input path="manufacturer" id="manufacturer" class="form-control" />
				<sf:errors path="manufacturer" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="productImage">Upload Picture</label>
				<sf:input path="productImage" id="productImage" type="file"
					class="form-control" />
			</div>

			<input type="submit" value="submit" class="btn btn-default">
			<a href="<c:url value="/admin/adminProductInventory"/>"
				class="btn btn-default">Cancel</a>

		</sf:form>
		<br />
	</div>
</div>



