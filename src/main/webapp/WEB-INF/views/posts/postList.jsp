<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<body>

	<div id="wrapper">

		<!-- navigation -->
		<%@ include file="../includes/navigation.jsp"%>


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="board-title">
						<div></div>
					</div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-9">
					<div class="forum-title" style="margin-bottom: 10px">
						<h1>
							<c:out value="${forum.name }" />
						</h1>
						<span><c:out value="${forum.description }" /></span>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">패널 헤더</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table width="100%"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>글쓴이</th>
										<th>작성일</th>
										<th>조회</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${posts}" var="post">
										<tr>
											<td><c:out value="${post.number}" /></td>
											<td><a href="/posts/${forum.slug}/${post.number}"><c:out
														value="${post.title}" /></a></td>
											<td><c:out value="${post.writer}" /></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd"
													value="${post.datePosted}" /></td>
											<td><c:out value="${post.views}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<div class="btns-post">
								<a class="btn btn-default" href="/posts/${forum.slug}">전체글</a> <a
									class="btn btn-default" href="#">개념글</a>
								<sec:authorize access="isAuthenticated()">
									<a class="btn btn-default pull-right"
										href="/posts/${forum.slug}/registration">글쓰기</a>
								</sec:authorize>
							</div>
							
							<div class="pagination-wrapper" style="text-align:center">
								<ul class="pagination">
									<c:if test="${pageMaker.hasPrevPage}">
										<li class="paginate_button previous"><a href="/posts/${forum.slug}?pageNumber=${pageMaker.searchCriteria.pageNumber - 1}">이전</a></li>
									</c:if>
									<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
										<li class="paginate_button ${pageMaker.searchCriteria.pageNumber == num ? "active" : "" }"><a href="/posts/${forum.slug}?pageNumber=${num}">${num}</a></li>
									</c:forEach>
									
									<c:if test="${pageMaker.hasNextPage }">
										<li class="paginate_button next"><a href="/posts/${forum.slug}?pageNumber=${pageMaker.searchCriteria.pageNumber + 1}">다음</a></li>
									</c:if>
								</ul>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-9 -->

				<div class="col-lg-3">
					<div class="panel panel-default" style="margin-top: 60px">
						<div class="panel-body" style="height: 320px; text-align: center">
							<p>광고란</p>
							<p><c:out value="${result}"/></p>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->


	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">처리 결과</h4>
				</div>
				<div class="modal-body">처리되었습니다.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
	<script>
		$(document).ready(function() {
			
			notifyResult();
			
			function notifyResult() {
				
				const resultMessage = '<c:out value="${resultMessage}"/>';
				
				if (!resultMessage) return;
				
				$(".modal-body").html(resultMessage);
				$("#myModal").modal("show");
			}
			
			// 나중에 json으로 데이터 받는 부분 구현하고나서 구현할 것
			function addPagenation() {
				
				const pageMaker = '<c:out value="${pageMaker}"/>';
				const forum = '<c:out value="${forum}"/>';
				const pageUl = document.getElementsByClassName("pagination")[0];
				
				console.log(pageMaker);
				console.log(forum);
				console.log(pageUl);
				
				console.log(pageMaker.startPage);
				console.log(forum[0]);
				
				let innerStr = "";
				
				if (pageMaker.hasPrevPage) {
					innerStr += '<li class="paginate_button previous"><a href="' 
									+ (pagemaker.startPage - 1)
									+ '">이전</a></li>';
					console.log("hasPrevePage");
				}
				
				for (let num = pageMaker.startPage; num <= pageMaker.endPage; i++) {
					innerStr += '<li class="paginate_button ';
					if (pageMaker.searchCriteria.pageNumber === num) {
						innserStr += 'active';
					}
					innerStr += '}"><a href="'
								+ '/posts/' + forum.slug + '?pageNumber=' + pageMaker.pageNumber
								+ '">'
								+ num
								+ '</a></li>';
					console.log("number : " + num);
				}
				
				if (pageMaker.hasNextPage) {
					innerStr += '<li class="paginate_button next"><a href="'
								+ (pageMaker.endPage + 1)
								+ '">다음</a></li>';
					console.log("hasNextPage");
				}
				innerStr += "<li>haha</li>"
				pageUl.innerHTML = innerStr;
			}
		});
	</script>

	<%@ include file="../includes/footer.jsp"%>