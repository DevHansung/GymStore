<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<nav class="navbar navbar-b navbar-trans navbar-expand-md fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll" href="<c:url value="/"/>">GymStore</a>
			<button class="navbar-toggler collapsed" type="button"
				data-toggle="collapse" data-target="#navbarDefault"
				aria-controls="navbarDefault" aria-expanded="false"
				aria-label="Toggle navigation">
				<span></span> <span></span> <span></span>
			</button>
			<div class="navbar-collapse collapse justify-content-end"
				id="navbarDefault">
				<ul class="navbar-nav ">

					<li class="dropdown"><a href="#" class="nav-link  active dropdown-toggle"
						data-toggle="dropdown">Information <b class="caret"></b></a>
						<ul class="dropdown-menu" style="background-color: rgba(255,255,255,0.5)">
							<li class="nav-item"><a class="nav-link js-scroll active"
								href="#page-top">Main</a></li>
							<li class="nav-item"><a class="nav-link js-scroll"
								href="#company">Company</a></li>
							<li class="nav-item"><a class="nav-link js-scroll"
								href="#service">Services</a></li>
							<li class="nav-item"><a class="nav-link js-scroll"
								href="#photo">Photo</a></li>
							<li class="nav-item"><a class="nav-link js-scroll"
								href="#blog">Blog</a></li>
							<li class="nav-item"><a class="nav-link js-scroll"
								href="#contact">Contact</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/products?page=1"/>">Products</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/viewBoard?page=1"/>">viewBoard</a></li>

					<!-- 권한에 따라 동적으로 메뉴바 활성화 -->
					<c:if test="${pageContext.request.userPrincipal.name !=null }">
						<c:if
							test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities =='[ROLE_ADMIN]'}">
							<li class="nav-item"><a class="nav-link"
								href="<c:url value="/admin"/>">AdminPage</a></li>
						</c:if>
						<c:if
							test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities =='[ROLE_USER]'}">
							<li class="nav-item"><a class="nav-link"
								href="<c:url value="/cartList"/>">Cart</a></li>

							<li class="nav-item"><a class="nav-link"
								href="<c:url value="/order/orderList?page=1"/>">OrderView</a></li>
						</c:if>

						<li class="nav-item"><a class="nav-link"
							href="javascript:document.getElementById('logout').submit()">Logout</a></li>
						<!-- 위의 로그아웃을 선택하면 logout이란 id를 가진  logout url+ post로 서버로 보냄. -->
						<form id="logout" action="<c:url value="/logout" />" method="post">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>

					</c:if>

					<c:if test="${pageContext.request.userPrincipal.name == null}">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value="/login"/>">Login</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value="/register"/>">Register</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>

</header>