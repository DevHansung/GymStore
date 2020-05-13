<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="bg-image bg-img1">
		<div class="container ">
			<br/>
			<br/>
			<br/>
			<br/>
			<h1 class="text-primary">Register User</h1>
			<p class="lead">회원 가입을 위한 정보를 입력하세요.</p>

			<sf:form action="${pageContext.request.contextPath}/register"
				method="post" modelAttribute="user">

				<h3 class="text-white">기본 정보</h3>

				<div class="form-group">
					<label for="username">아이디</label> <span style="color: #ff0000">${usernameMsg}</span>
					<sf:input path="username" id="username" class="form-control" />
					<sf:errors path="username" cssStype="color: #ff0000" />
				</div>

				<div class="form-group">
					<label for="password">패스워드</label>
					<sf:password path="password" id="password" class="form-control" />
					<sf:errors path="password" cssStype="color: #ff0000" />
				</div>

				<div class="form-group">
					<label for="email">이메일 주소</label><span style="color: #ff0000">${emailMsg}</span>
					<sf:input path="email" id="email" class="form-control" />
					<sf:errors path="email" cssStype="color: #ff0000" />
				</div>
				<br />

				<h3 class="text-white">주소 정보</h3>
				<div class="form-group">
					<label for="postcode">우편번호</label><span style="color: #ff0000"></span>
					<sf:input type="text" id="postcode" placeholder="우편번호"
						path="address" class="form-control" readonly="true" />
					<input class="btn btn-outline-primary" type="button"
						onclick="sample2_execDaumPostcode()" value="우편번호 찾기">
				</div>

				<div class="form-group">
					<label for="address">주소</label><span style="color: #ff0000"></span>
					<sf:input type="text" id="address" placeholder="주소" path="address"
						class="form-control" readonly="true" />
				</div>

				<div class="form-group">
					<label for="detailAddress">상세주소</label><span style="color: #ff0000"></span>
					<sf:input type="text" id="detailAddress" placeholder="우편번호"
						path="address" class="form-control" />
				</div>

				<input class="btn btn-outline-primary" type="submit" value="submit"
					class="btn btn-default">
				<a href="<c:url value="/" />" class="btn btn-outline-primary">Cancel</a>
				<br />
			</sf:form>
			<br/><br/>
		</div>

</div>


<!-- 카카오 주소 api -->
<div id="layer"
	style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
	<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
		id="btnCloseLayer"
		style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
		onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<!-- 카카오 주소 api -->
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	var element_layer = document.getElementById('layer');

	function closeDaumPostcode() {
		element_layer.style.display = 'none';
	}

	function sample2_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {

				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('postcode').value = data.zonecode;
				document.getElementById("address").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("detailAddress").focus();

				// iframe을 넣은 element를 안보이게 한다.
				// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
				element_layer.style.display = 'none';
			},
			width : '100%',
			height : '100%',
			maxSuggestItems : 5
		}).embed(element_layer);

		// iframe을 넣은 element를 보이게 한다.
		element_layer.style.display = 'block';

		// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
		initLayerPosition();
	}

	function initLayerPosition() {
		var width = 300; //우편번호서비스가 들어갈 element의 width
		var height = 400; //우편번호서비스가 들어갈 element의 height
		var borderWidth = 5; //샘플에서 사용하는 border의 두께

		// 위에서 선언한 값들을 실제 element에 넣는다.
		element_layer.style.width = width + 'px';
		element_layer.style.height = height + 'px';
		element_layer.style.border = borderWidth + 'px solid';
		// 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
		element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth)
				+ 'px';
		element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth)
				+ 'px';
	}
</script>


