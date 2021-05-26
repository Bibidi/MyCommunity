<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp"%>

<body>

	<div id="wrapper">

		<!-- navigation -->
		<%@ include file="../includes/navigation.jsp"%>

		<!-- 일단 클래스로 레이아웃을 나누듯이 이름 지음. 나중에 고쳐야 됨.  -->
		<div class="contents-wrapper" id="page-wrapper">

			<div class="contents-view col-lg-9">
				<div class="forum-info" style="margin-bottom: 10px">
					<h1>
						<c:out value="${forum.name }" />
					</h1>
					<span><c:out value="${forum.description }" /></span>
				</div>
				
				<!-- 나중에 새로 만들 때 패널 자체를 날려버려야 됨. 따로 이름 짓지 않은 건 나중에 삭제돼야 하는 것들-->
				<div class="post-view panel panel-default">
					<div class="panel-body">
						<div class="post-title" style="width:100%; padding:5px; border-bottom:1px solid black"><h3><c:out value="${selectedPost.title}" /></h3></div>
						<div class="post-details" style="width:100%; padding:5px; border-bottom:1px solid black">
							<div class="user-info" style="display:inline">
								<span><c:out value="${selectedPost.writer }" /></span>
							</div>
							<div class="post-info pull-right" style="display:inline">
								<span><c:out value="조회수 ${selectedPost.views} | "/></span>
								<span>작성일 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${selectedPost.datePosted}"/></span>
							</div>
						</div>
						<div class="post-content" style="width:100%; padding:5px; margin-bottom:40px">
							<p><c:out value="${selectedPost.content}"/></p>
						</div>
						<div class="post-likes" style="margin:5px; text-align:center; margin-bottom:20px">
							<button class="btn-like btn btn-default">좋아요 0</button>
							<button class="btn-dislike btn btn-default">싫어요 0</button>
						</div>
						
						<div class="comments-view" style="padding:5px">
							<div class="comments-header" style="padding:5px">
								<i class="fa fa-comments fa-fw"></i> Comments
							</div>
							
							<ul class="comments">
								<li data-cno='39'>
									<div class="header">
										<div class="header--left" style="display:inline; padding-left:18px">
											<strong class="primary-font">user00</strong>
										</div>
										<div class="header--right pull-right" style="display:inline">
											<time>2018-01-01 13:13 </time>
											<a>삭제 </a>
											<a href='#'>수정 </a>
											<a href='#'>답글</a>
										</div>
									</div>
									<div class="body">
										<p><i class="fa fa-angle-right fa-fw"></i>Good</p>
									</div>
								</li>
							</ul>
						</div>
						
						<sec:authorize access="isAuthenticated()">
							<div class="comments-register" style="margin-bottom:5px; padding:5px">
								<textarea id="comment-register-content" rows="5" style="width:100%"></textarea>
								<div style="text-align:right">
									<button id="comment-register-btn" class="btn btn-primary" style="display:inline">등록</button>
								</div>
							</div>
						</sec:authorize>
						
						<sec:authorize access="isAnonymous()">
							<div style="height:40px; text-align:center">
								로그인 하시면 댓글을 작성할 수 있어요.
							</div>
						</sec:authorize>
						
						<div class="btns-post" style="padding:5px;">
							<div class="btns-post-left" style="display:inline">
								<a class="btn btn-default" href="/posts/${forum.slug}">전체글</a> 
								<a class="btn btn-default" href="#">개념글</a>
							</div>
							
							<sec:authorize access="isAuthenticated()">
								<div class="btns-post--right pull-right" style="display:inline">
									<c:if test="${principal.user.nickname == post.writer }">
										<a class="btn btn-default" href="/posts/${forum.slug}/${selectedPost.number}/modification">수정</a>
										<a class="btn btn-default">삭제</a>
									</c:if>
									<a class="btn btn-default" href="/posts/${forum.slug}/registration">글쓰기</a>
								</div>
							</sec:authorize>
						</div>
					</div>
				</div>
				<!-- /.panel -->

				
				<div class="panel panel-default">
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
										<td><c:out value="${post.number}" /><c:if test="${selectedPost.number == post.number }"> 현재</c:if></td>
										<td><a href="/posts/${forum.slug}/${post.number}?pageNumber=${pageMaker.searchCriteria.pageNumber}"><c:out value="${post.title}" /></a></td>
										<td><c:out value="${post.writer}" /></td>
										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${post.datePosted}" /></td>
										<td><c:out value="${post.views}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- /.table-responsive -->
						
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

			<aside class="right-sidebar col-lg-3">
				<div class="panel panel-default" style="margin-top: 60px">
					<div class="panel-body" style="height: 320px; text-align: center">
						<p>광고란</p>
					</div>
				</div>
			</aside>

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	
	<script type="text/javascript" src="/resources/js/comment.js"></script>
	
	<script>
		$(document).ready(function() {
			
			const postNumber = '<c:out value="${selectedPost.number}"/>';
			const metaNickname = document.querySelector("meta[name='userNickname']");
			const userNickname = !metaNickname ? "" : metaNickname.getAttribute("content");
		
			listComments(1);
			registerBtnEvent();
			
			function registerBtnEvent() {
				const isLogin = userNickname == '' ? false : true;
				console.log(isLogin);
				
				if (isLogin) {
					const commentRegisterBtn = document.querySelector("#comment-register-btn");
					
					if (commentRegisterBtn != null) {
						commentRegisterBtn.addEventListener('click', function() {
							const textArea = document.querySelector('#comment-register-content');
							const content = textArea.value;
							
							if (content == '') {
								alert("내용을 채워주세요.");
								return;
							}
							
							comment = {
								postNumber : postNumber,
								content : content,
								writer : userNickname
							};
							
							console.log(comment);
							
							commentService.addComment(
								comment,
								function (msg) {
									textArea.value = '';
									listComments(1);
								}
							);
						});
					}
				}
			}
			
			function listComments(pageNumber) {
				
				const param = {
					postNumber : postNumber,
					pageNumber : pageNumber
				};
				const commentUl = document.querySelector(".comments");
				
				commentService.getComments(
					param,
					function(list) {
						
						if (!list || list.length == 0) {
							commentUl.innerHTML = "";
							return;
						}
						
						let str = "";
						list.forEach(item => {
							if (item.number === item.parentNumber) {
								str += "<li data-cno='" + item.number + "'>"
									+ "<div class='comment-header'>"
									+ "<div class='header--left' style='display:inline'>"
									+ "<strong class='primary-font'>" + item.writer + "</strong>"
									+ "</div>"
									+ "<div class='header--right pull-right' style='display:inline'>"
									+ "<time>" + displayTime(item.dateCommented) + " </time>";
								
								if (item.writer === userNickname) {
									str += "<a href='#'> 삭제 </a>"
										+ "<a href='#'> 수정 </a>";
									
								}
								
								if (userNickname != '') {
									str += "<a href='#'> 답글 </a>";
								}
								
								str += "</div></div><div class='comment-body'>"
									+ "<p>" + item.content + "</p>"
									+ "</div></li>";
							}
							else {
								str += "<li class='reply' data-cno='" + item.number + "'>"
									+ "<div class='comment-header'>"
									+ "<div class='header--left' style='display:inline; padding-left:18px'>"
									+ "<strong class='primary-font'>" + item.writer + "</strong>"
									+ "</div>"
									+ "<div class='header--right pull-right' style='display:inline'>"
									+ "<time>" + displayTime(item.dateCommented) + " </time>";
								
								if (item.writer === userNickname) {
									str += "<a href='#'> 삭제 </a>"
										+ "<a href='#'> 수정 </a>";
									}
								
								str	+= "</div></div><div class='comment-body'>"
									+ "<p><i class='fa fa-angle-right fa-fw'></i>" + item.content + "</p>"
									+ "</div></li>";
							}
						});
						
						commentUl.innerHTML = str;
					}
				);
			}
			
			function displayTime(timeValue) {
				
				const date = new Date(timeValue);
				
				const YYYY = date.getFullYear();
				const MM = date.getMonth() + 1;
				const DD = date.getDate();
				
				const HH = date.getHours();
				const mm = date.getMinutes();
				const ss = date.getSeconds();
				
				return YYYY + '-' + MM + '-' + DD + ' ' + HH + ':' + mm + ':' + ss;
			}
		});
	</script>

	<%@ include file="../includes/footer.jsp"%>