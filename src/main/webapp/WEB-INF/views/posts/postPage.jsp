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
						<div class="post-content" style="width:100%; height:60vw; padding:5px; margin-bottom:5px"><p><c:out value="${selectedPost.content}"/></p></div>
						<div class="post-likes" style="margin:5px; text-align:center">
							<button class="btn-like btn btn-default">좋아요 0</button>
							<button class="btn-dislike btn btn-default">싫어요 0</button>
						</div>
						<div class="btns-post" style="padding:5px">
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
										<td><a href="/posts/${forum.slug}/${post.number}"><c:out value="${post.title}" /></a></td>
										<td><c:out value="${post.writer}" /></td>
										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${post.datePosted}" /></td>
										<td><c:out value="${post.views}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- /.table-responsive -->
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

	<%@ include file="../includes/footer.jsp"%>