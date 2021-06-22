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
						
						<div class="comments">
							<div class="comments__header">
								<i class="fa fa-comments fa-fw"></i> Comments
							</div>
							
							<ul class="comments__list">
							</ul>
							
							<div class='comments__pagination'>
								<ul class='pagination'>
								</ul>
							</div>
						</div>
						
						<sec:authorize access="isAuthenticated()">
							<div class="comments-register" style="margin-bottom:5px; padding:5px;">
								<textarea id="comment-register-content" rows="5" style="width:100%"></textarea>
								<div style='display:inline'>
									<button class='emoticon-register-btn btn btn-default'>이모티콘</button>
								</div>
								<div class='pull-right' style="display:inline">
									<button id="comment-register-btn" class="btn btn-primary">등록</button>
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
										<a class="post-edit-link btn btn-default" href="/posts/${forum.slug}/${selectedPost.number}/modification">수정</a>
										<a class="post-delete-link btn btn-default">삭제</a>
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
										<li class="paginate_button ${pageMaker.searchCriteria.pageNumber == num ? 'active' : '' }"><a href="/posts/${forum.slug}?pageNumber=${num}">${num}</a></li>
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
	
	<script type="text/javascript" src="/resources/js/post.js"></script>
	<script type="text/javascript" src="/resources/js/comment.js"></script>
	
	<script>
		$(document).ready(function() {
			
			const urlTokens = location.pathname.split('/');
			const forumSlug = urlTokens[2];
			const postNumber = urlTokens[3];
			const metaNickname = document.querySelector("meta[name='userNickname']");
			const userNickname = !metaNickname ? "" : metaNickname.getAttribute("content");
			
			let commentCurrentPage = 1;
		
			registerBtnEvent();
			listComments(commentCurrentPage);
			
			function registerBtnEvent() {
				const isLogin = userNickname == '' ? false : true;
				
				if (isLogin) {
					
					// 댓글 등록 버튼 이벤트 등록
					const commentRegisterBtn = document.querySelector("#comment-register-btn");
					
					if (commentRegisterBtn !== null) {
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
							
							commentService.addComment(
								comment,
								function (msg) {
									textArea.value = '';
									listComments(1);
								}
							);
						});
					}
					
					
					// 게시물 삭제 이벤트 등록
					const postDeleteBtn = document.querySelector('.post-delete-link');
					
					if (postDeleteBtn !== null) {
						const result = location.pathname.split('/');
						
						postDeleteBtn.addEventListener('click', function(event) {
							event.preventDefault();
							
							postService.deletePost(
								postNumber,
								function(msg) {
									alert("msg");
									location.href = '/posts/' + forumSlug;
								}
							);
						});
					}
					
					// 이모티콘 창 띄우는 이벤트 등록
					const emoticonRegisterBtn = document.querySelector('.emoticon-register-btn');
					
					if (emoticonRegisterBtn !== null) {
						const commentRegisterDiv = emoticonRegisterBtn.parentNode.parentNode;
						let emoticonDiv = null;
						
						emoticonRegisterBtn.addEventListener('click', function(event) {
							
							if (emoticonDiv == null) {
								emoticonDiv = document.createElement('div');
								emoticonDiv.setAttribute('class', 'emoticons');
								
								let str = "<div class='emoticons__tab'>tabs</div>"
										+ "<div class='emoticons__imgs'>imgs</div>";
								
								emoticonDiv.innerHTML = str;
								commentRegisterDiv.appendChild(emoticonDiv);
							}
							else {
								emoticonDiv.remove();
								emoticonDiv = null;
							}
						});
					}
				}
			}
			
			function listComments(pageNumber) {
				
				const param = {
					postNumber : postNumber,
					pageNumber : pageNumber
				};
				const commentUl = document.querySelector(".comments__list");
				
				commentService.getComments(
					param,
					function(page) {
						const totalQuantity = page.commentsCount;
						const list = page.comments;
						
						if (!list || list.length == 0) {
							commentUl.innerHTML = "";
							registerCommentBtnEvent();
							
							return;
						}
						
						let str = "";
						list.forEach(item => {
							if (item.number === item.parentNumber) {
								
								if (item.isDeleted) {
									const msgDeletedItem = "해당 댓글은 삭제되었습니다.";
									
									str += "<li>"
										+ "<div class='comment-wrapper'>"
										
									if (userNickname != '') {
										str += "<a class='comment-reply-link pull-right' href='#' data-target='" + item.number + "'> 답글 </a>";
									}
									str += "</div></div><div class='comment-body'>";
									str += "<p>" + msgDeletedItem + "</p>";
									str	+= "</div></div></li>";
									
									return;
								}
								
								str += "<li>"
									+ "<div class='comment-wrapper'>"
									+ "<div class='comment-header'>"
									+ "<div class='header--left' style='display:inline'>"
									+ "<strong class='primary-font'>" + item.writer + "</strong>"
									+ "</div>"
									+ "<div class='header--right pull-right' style='display:inline'>"
									+ "<time>" + displayTime(item.dateCommented) + " </time>";
								
								if (item.writer === userNickname) {
									str += "<a class='comment-delete-link' href='#' data-target='" + item.number + "'> 삭제 </a>"
										+ "<a class='comment-edit-link' href='#' data-target='" + item.number + "'> 수정 </a>";
									
								}
								
								if (userNickname != '') {
									str += "<a class='comment-reply-link' href='#' data-target='" + item.number + "'> 답글 </a>";
								}
								
								str += "</div></div><div class='comment-body'>";
								str += "<p>" + item.content + "</p>";
								str	+= "</div></div></li>";
							}
							else {
								str += "<li>"
									+ "<div class='comment-header'>"
									+ "<div class='header--left' style='display:inline; padding-left:18px'>"
									+ "<strong class='primary-font'>" + item.writer + "</strong>"
									+ "</div>"
									+ "<div class='header--right pull-right' style='display:inline'>"
									+ "<time>" + displayTime(item.dateCommented) + " </time>";
								
								if (item.writer === userNickname) {
									str += "<a class='comment-delete-link' href='#' data-target='" + item.number + "'> 삭제 </a>"
										+ "<a class='comment-edit-link' href='#' data-target='" + item.number + "'> 수정 </a>";
									}
								
								str	+= "</div></div><div class='comment-body'>"
									+ "<p><i class='fa fa-angle-right fa-fw'></i>" + item.content + "</p>"
									+ "</div></li>";
							}
						});
						
						commentUl.innerHTML = str;
						registerCommentBtnEvent();
						commentCurrentPage = pageNumber;
						addCommentPagination(totalQuantity);
					}
				);
			}
			
			function addCommentPagination(totalQuantity) {
				
				const contentQuantity = 20;
				const pageSize = 5;
				let endPage = Math.ceil(commentCurrentPage / pageSize) * pageSize;
				const startPage = endPage - pageSize + 1;
				let hasNextPage = true;
				const hasPreviousPage = (startPage != 1);
				
				if (endPage * contentQuantity >= totalQuantity) {
					hasNextPage = false;
					endPage = Math.ceil(totalQuantity / contentQuantity);
				}
				
				
				let str = '';
				if (hasPreviousPage) {
					str += '<li class="paginate_button previous"><a href="' + (startPage - 1) + '">이전</a></li>';
				}
				
				for (let i = startPage; i <= endPage; i++) {
					str += '<li class="paginate_button"><a href="#">' + i + '</a></li>';
				}
				
				if (hasNextPage) {
					str += '<li class="paginate_button next"><a href="' + (endPage + 1) + '">다음</a></li>';
				}
				
				const commentPaginationUl = document.querySelector('.comments__pagination').querySelector('.pagination');
				commentPaginationUl.innerHTML = str;
				
				const commentItems = commentPaginationUl.querySelectorAll('.paginate_button');
				commentItems.forEach(item => {
					item.addEventListener('click', function(event) {
						event.preventDefault();
						
						const targetNumber = this.querySelector('a').innerText;
						listComments(targetNumber);
					});
				});
			}
			
			function registerCommentBtnEvent() {
				const isLogin = userNickname == '' ? false : true;
				
				if (isLogin) {
					
					// 댓글 삭제 이벤트 등록
					const commentDeleteBtns = document.querySelectorAll('.comment-delete-link');
					
					commentDeleteBtns.forEach(btn => {
						const targetNumber = btn.dataset.target;
						
						btn.addEventListener("click", function(event) {
							event.preventDefault();
							
							commentService.removeComment(
								targetNumber,
								function (msg) {
									alert(msg);
									listComments(commentCurrentPage);
								}
							);
						});
					});
					
					// 댓글 수정 이벤트 등록
					const commentEditLinks = document.querySelectorAll('.comment-edit-link');
					
					commentEditLinks.forEach(link => {
						let commentEditDiv = null;
						
						link.addEventListener("click", function(event) {
							event.preventDefault();
							const commentWrapper = this.parentNode.parentNode.parentNode;
							const commentContent = commentWrapper.querySelector('.comment-body').innerText;
							
							if (commentEditDiv === null) {
								const editDiv = document.createElement('div');
								editDiv.setAttribute('class', 'comment-edit');
								
								let str = "<textarea class='comment-edit__content' rows='5'>" + commentContent + "</textarea>"
										+ "<div class='btns-wrapper'>"
										+ "<button class='comment-edit__submit-btn btn btn-primary'>수정</button>"
										+ "</div>";
										
								editDiv.innerHTML = str;
								commentWrapper.appendChild(editDiv);
								commentEditDiv = editDiv;
								
								// 제출 버튼 이벤트 등록
								const commentEditBtn = commentEditDiv.querySelector(".comment-edit__submit-btn");
								const targetNumber = this.dataset.target;
								
								commentEditBtn.addEventListener('click', function() {
									
									comment = {
										number : targetNumber,
										content : commentEditDiv.querySelector(".comment-edit__content").value
									};
									
									commentService.updateComment(
										comment,
										function(msg) {
											alert(msg);
											listComments(commentCurrentPage);
										}	
									);
								});
							}
							else {
								commentEditDiv.remove();
								commentEditDiv = null;
							}
						});
					});
					
					// 대댓글 등록 이벤트
					const commentReplyLinks = document.querySelectorAll('.comment-reply-link');
					
					commentReplyLinks.forEach(link => {
						let commentReplyDiv = null;
						
						link.addEventListener("click", function(event) {
							event.preventDefault();
							const commentWrapper = this.parentNode.parentNode.parentNode;
							const commentContent = commentWrapper.querySelector('.comment-body').innerText;
							
							if (commentReplyDiv === null) {
								const replyDiv = document.createElement('div');
								replyDiv.setAttribute('class', 'comment-reply');
								
								let str = "<textarea class='comment-reply__content' rows='5'></textarea>"
										+ "<div class='btns-wrapper'>"
										+ "<button class='emoticon-register-btn btn btn-default'>이모티콘</button>"
										+ "<button class='comment-reply__submit-btn btn btn-primary'>등록</button>"
										+ "</div>";
										
										
								replyDiv.innerHTML = str;
								commentWrapper.appendChild(replyDiv);
								commentReplyDiv = replyDiv;
								
								// 제출 버튼 이벤트 등록
								const commentReplyBtn = commentReplyDiv.querySelector(".comment-reply__submit-btn");
								const targetNumber = this.dataset.target;
								
								commentReplyBtn.addEventListener('click', function() {
									
									comment = {
										postNumber : postNumber,
										parentNumber : targetNumber,
										content : commentReplyDiv.querySelector(".comment-reply__content").value,
										writer : userNickname
									};
									
									commentService.addComment(
										comment,
										function(msg) {
											alert(msg);
											listComments(commentCurrentPage);
										}	
									);
								});
							}
							else {
								commentReplyDiv.remove();
								commentReplyDiv = null;
							}
						});
					});
				}
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