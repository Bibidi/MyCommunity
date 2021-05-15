<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>

<body>

    <div id="wrapper">

		<!-- navigation -->
        <%@ include file="../includes/navigation.jsp" %>


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
                	<div class="forum-title" style="margin-bottom:10px">
                		<h1><c:out value="${forum.name }" /></h1>
                		<span><c:out value="${forum.description }" /></span>
                	</div>
                	
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            패널 헤더
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
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
                                			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${post.datePosted}"/></td>
                                			<td><c:out value="${post.views}" /></td>
                                		</tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            <div class="btns-post">
                                <a class="btn btn-default" href="/posts/${forum.slug}">전체글</a>
                                <a class="btn btn-default" href="#">개념글</a>
                                <sec:authorize access="isAuthenticated()">
									<a class="btn btn-default pull-right" href="/posts/${forum.slug}/registration">글쓰기</a>
								</sec:authorize>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-9 -->
                
                <div class="col-lg-3">
                	<div class="panel panel-default" style="margin-top:60px">
                		<div class="panel-body" style="height:320px; text-align:center">
                			<p>광고란</p>
                		</div>
                	</div>
                </div>
            </div>
            
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

<%@ include file="../includes/footer.jsp" %>