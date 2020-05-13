<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="  bg-image bg-img1">
	<div class="container ">
		<br /> <br /> <br /> <br />
		<div class="jumbotron">
			<div class="container">
				<h2 class="text-primary ">Board</h2>
			</div>
		</div>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead class="text-light">
					<tr class=" col-xs-12">
						<th>번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>조회수</th>
						<th>상세정보</th>
					</tr>
				</thead>

				<tbody class="text-light">
					<c:forEach var="boardList" items="${boardList}">
						<tr class=" col-xs-12">
							<td>${boardList.boardNum}</td>
							<td>${boardList.title}</td>
							<td>${boardList.text}</td>
							<td>${boardList.writer}</td>
							<td>${boardList.date}</td>
							<td>${boardList.count}</td>
							<td><a
								href="<c:url value="/detailBoard/${page}/${boardList.boardNum}" />">
									<i class="fa fa-info-circle"></i>
							</a> <a
								href="<c:url value="/admin/adminViewBoard/adminDeleteBoard/${page}/${boardList.boardNum}"/>"><i
									class="fa fa-times"></i></a> <a
								href="<c:url value="viewBoard/updateBoard/${page}/${boardList.boardNum}"/>"><i
									class="fa fa-edit"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="text-light">
			<c:if test="${prev}">
				<span>[ <a class="text-light"
					href="<c:url value="/admin/adminViewBoard?page=${startPageNum - 1}"/>">이전</a>
					]
				</span>
			</c:if>
			<c:forEach begin="${startPageNum}" end="${endPageNum}" var="pageNum">
				<span> <c:if test="${page != pageNum}">
						<a class="text-light"
							href="<c:url value="/admin/adminViewBoard?page=${pageNum}"/>">${pageNum}</a>
					</c:if> <c:if test="${page == pageNum}">
						<b>${pageNum}</b>
					</c:if>
				</span>
			</c:forEach>
			<c:if test="${next}">
				<span>[ <a class="text-light"
					href="<c:url value="/admin/adminViewBoard?page=${endPageNum + 1}"/>">다음</a>
					]
				</span>
			</c:if>
		</div>
		
		<div>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<a
					href="<c:url value="/admin/adminViewBoard/adminWriteBoard/${page}"/>"
					class="btn btn-outline-primary">writeBoard</a>
			</c:if>
		</div>
		<br /> <br />
	</div>
</div>



