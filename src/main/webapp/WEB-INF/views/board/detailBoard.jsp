<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="bg-image bg-img1">
	<div>
		<form id="detailBoard" name="detailBoard" method="post">
			<div>
				<br> <br> <br> <br>
				<div class="row">
					<div class="col-sm-12">
						<div class="box-shadow-full">
							<div class="row">
								<div class="col-md-12">

									<div>
										<table>
											<tr>
												<th><strong> 제목:</th>
												<td>${board.title}</td>
											</tr>
											<tr>
												<th><strong> 작성자:</th>
												<td>${board.writer}</td>
											</tr>
											<tr>
												<th><strong> 작성일:</th>
												<td>${board.date}</td>
											</tr>
											<tr>
												<th><strong>내용:</th>
												<td>${board.text}</td>
											</tr>

										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div>
					<a href="<c:url value="/viewBoard?page=${page}"/>"
						class="btn btn-primary">목록</a>
				</div>
				<br>
			</div>
			<input type='hidden' id='code' name='code' value='${board.boardNum }' />
		</form>
	</div>

	<div class="bg-light text-dark">
		<div class="container">
			<form id="replyForm" name="replyForm" method="post">
				<br> <br>
				<div>
					<div>
						<span><strong>Reply</strong></span> <span id="ReplyCount"></span>
					</div>
					<div>
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<table class="table col-md-12">
								<tr>

									<td><textarea class="table col-sm-12" rows="3" cols="30"
											id="replyText" name="replyText" placeholder="댓글을 입력하세요"></textarea>
										<br>
										<div>
											<a href='#' onClick="fn_reply('${board.boardNum}')"
												class="btn pull-right btn-success">등록</a>
										</div></td>
								</tr>
							</table>
						</c:if>
					</div>
				</div>
				<input type="hidden" id="boardNum" name="boardNum"
					value="${board.boardNum}" />
			</form>
		</div>

		<div id="replyPage" name="replyPage" class="container">
			<form id="replyListForm" name="replyListForm" method="post">
				<div id="replyList"></div>
			</form>
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(function() {
		getReplyList();
	});

	function getReplyList() {
		$
				.ajax({
					type : 'GET',
					url : "<c:url value='/board/replyList'/>",
					dataType : "json",
					data : $("#replyForm").serialize(),
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					success : function(data) {
						var html = "";
						var ReplyCount = data.length;

						if (data.length > 0) {
							for (i = 0; i < data.length; i++) {
								html += "<div>";
								html += "<div><table class='table'><h6><strong>작성자: "
										+ data[i].replyWriter
										+ "</strong></h6>";
								html += "내용: " + data[i].replyText + "<br>";
								html += "작성일: " + data[i].replyDate
										+ "<br><tr><td></td></tr>";
								html += '<c:if test="${pageContext.request.userPrincipal.name != null}">';
								html += '<a href="javascript:void(0)" onclick="fn_deleteReply('
										+ data[i].replyNum + ')" >삭제</a>';
								html += '</c:if>';
								html += "</table></div>";
								html += "</div>";
							}
						} else {
							html += "<div>";
							html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
							html += "</table></div>";
							html += "</div>";
						}
						$("#ReplyCount").html(ReplyCount);
						$("#replyList").html(html);
					},
					error : function(request, status, error) {
					}
				});
	}

	function fn_reply(code) {
		$.ajax({
			type : 'POST',
			url : "<c:url value='/board/addReply'/>",
			data : $("#replyForm").serialize(),
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			},
			success : function(data) {
				alert("댓글 작성을 완료했습니다.");

				if (data == "success") {
					getReplyList();
					$("#reply").val("");
				}
			},
			error : function(request, status, error) {
			}
		});
	}

	function fn_deleteReply(replyNum) {
		var form = {
			"replyNum" : replyNum,

		};
		$.ajax({
			url : "<c:url value='/board/deleteReply'/>",
			type : "POST",
			contentType : "application/json",

			data : JSON.stringify(form),
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			},
			success : function(result) {
				console.log(result);
				if (result == "success") {
					alert("댓글 삭제를 완료했습니다.");
					getReplyList();
				} else if (result == "error") { 
					alert("작성자 본인만 삭제할 수 있습니다."); 
				}
			},
			error : function(error) { 
				console.log("에러 : " + error);
			}
		});
	}
</script>