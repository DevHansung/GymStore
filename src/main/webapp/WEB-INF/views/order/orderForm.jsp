<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document).ready(function() {
		$("#btnList").click(function() {
			location.href = "${path}/product";
		});
	});
</script>
</head>
<body>
	<div class="bg-image bg-img1">
		<div class="container ">
			<br> <br> <br> <br>
			<div class="jumbotron">
				<div class="container">
					<h2>장바구니 확인</h2>

					<table border="1">
						<tr>
							<th>상품명</th>
							<th>단가</th>
							<th>수량</th>
							<th>금액</th>
						</tr>
						<c:forEach var="cart" items="${cart}">
							<tr>
								<td>${cart.productname}</td>
								<td>${cart.productprice}</td>
								<td>${cart.amount}</td>
								<td>${cart.totalprice}
								<input type="hidden" name="productid" value="${cart.productid}"></td>

							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" align="right">장바구니 금액 합계 : ${sumMoney}won<br>
								배송료 : ${fee}won<br> 전체 주문금액 :${allSum}won <input
								type="hidden" name="orderprice" id="orderprice"
								value="${allSum}" />
							</td>
						</tr>
					</table>
				</div>
			</div>

			<form class="text-white" name="form1" method="post"
				action="${pageContext.request.contextPath}/order/orderSuccess?${_csrf.parameterName}=${_csrf.token}">

				<input type="hidden" name="orderprice" id="orderprice"
					value="${allSum}" />
				<div class="form-group">
					<label for="payment">결제정보: </label> <input type="radio"
						name="payment" value="신용카드" /> 신용카드 <input type="radio"
						name="payment" value="계좌이체" /> 계좌이체 <input type="radio"
						name="payment" value="무통장입금" /> 무통장입금
				</div>

				<div class="form-group">
					<label for="username">주문자</label> <input type="text"
						name="username" id="username" class="form-control" />
				</div>

				<div class="form-group">
					<label for="recipient">수취인</label> <input type="text"
						name="recipient" id="recipient" class="form-control" />
				</div>

				<h3 class="text-white">배송 정보</h3>
				<div class="form-group">
					<label for="postcode">우편 번호</label> <input type="text"
						name="address" id="postcode" class="form-control" readonly="true" />
					<input class="btn btn-outline-primary" type="button"
						onclick="sample2_execDaumPostcode()" value="우편번호 찾기">
				</div>

				<div class="form-group">
					<label for="address">주소</label> <input type="text" name="address"
						id="address" id="address" class="form-control" readonly="true" />
				</div>

				<div class="form-group">
					<label for="detailAddress">상세 주소</label> <input type="text"
						name="address" id="detailAddress" class="form-control" />
				</div>

				<div class="form-group">
					<label for="description">휴대폰번호</label> <input type="text"
						name="phonenumber" id="" class="form-control" />
				</div>

				<button type="submit" class="btn btn-primary">주문완료</button>
			</form>
			<br> <a href="<c:url value="/products"/>" class="btn btn-danger">Back</a>
		</div>
	</div>
</body>

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

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
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

