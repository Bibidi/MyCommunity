<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../includes/header.jsp" %>

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
				<div class="post-Registration-view panel panel-default">
					<div class="panel-heading">글쓰기</div>
					<div class="panel-body">
						
						<form role="form" action="/posts/${forum.slug}/${post.number}" method="patch">
							<div class="form-group" >
								<label>제목</label>
								<input class="form-control" name="title" value="${post.title}">
							</div>
							
							<div class="form-group" >
								<textarea class="form-control" name="content" rows="10"><c:out value="${post.content}"/></textarea>
							</div>
							
							<input type="hidden" name="number" value="${post.number }" />
							<input type="hidden" name="writer" value="<sec:authentication property="principal.user.nickname"/>">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" />
							
							<button type="submit" class="btn btn-default">제출</button>
						</form>
						
					</div>
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