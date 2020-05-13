<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>GymStore</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <title><tiles:insertAttribute name="title" /></title>
  <script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
  
  <!-- Favicons -->
  <link href="<c:url value="/resources/img/logo3.png"/>" rel="icon">
  <link href="<c:url value="/resources/img/logo2.png"/>" rel="apple-touch-icon">

  <!-- Bootstrap CSS File -->
  <link href="<c:url value="/resources/lib/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="<c:url value="/resources/lib/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
  <link  href="<c:url value="/resources/lib/animate/animate.min.css"/>" rel="stylesheet">
  <link  href="<c:url value="/resources/lib/ionicons/css/ionicons.min.css"/>" rel="stylesheet">
  <link  href="<c:url value="/resources/lib/owlcarousel/assets/owl.carousel.min.css"/>" rel="stylesheet">
  <link  href="<c:url value="/resources/lib/lightbox/css/lightbox.min.css"/>" rel="stylesheet">
    <link  href="<c:url value="/resources/lib/lightbox/css/lightbox.min.css"/>" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">

</head>

<body id="page-top">

	<div>
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>


  <!--/ Section Contact-footer End /-->

  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
  <div id="preloader"></div>

  <!-- JavaScript Libraries -->
  <script src="<c:url value="/resources/lib/jquery/jquery.min.js"/>"></script>
  <script src="<c:url value="/resources/lib/jquery/jquery-migrate.min.js"/>"></script>
  <script src="<c:url value="/resources/lib/popper/popper.min.js"/>"></script>
  <script src="<c:url value="/resources/lib/bootstrap/js/bootstrap.min.js"/>"></script>
  <script src="<c:url value="/resources/lib/easing/easing.min.js"/>"></script>
  <script src="<c:url value="/resources/lib/counterup/jquery.waypoints.min.js"/>"></script>
  <script src="<c:url value="/resources/lib/counterup/jquery.counterup.js"/>"></script>
  <script src="<c:url value="/resources/lib/owlcarousel/owl.carousel.min.js"/>"></script>
  <script src="<c:url value="/resources/lib/lightbox/js/lightbox.min.js"/>"></script>
  <script src="<c:url value="/resources/lib/typed/typed.min.js"/>"></script>
  <!-- Contact Form JavaScript File -->
  <script src="<c:url value="/resources/contactform/contactform.js"/>"></script>

  <!-- Template Main Javascript File -->
  <script src="<c:url value="/resources/js/main.js"/>"></script>

</body>
</html>
