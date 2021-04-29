<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<a class="navbar-brand" href="/">Bibidi</a>
		<sec:authorize access="isAuthenticated()">
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a class="navbar-brand" href="/"> ADMIN</a>
			</sec:authorize>
		</sec:authorize>
		
		<!-- 로그아웃 용도로 작성된 폼을 끼워넣은 것. 나중에 자바스크립트로 대체해야 한다. -->
		<form name="logoutForm" action="/users/logout" method="post">
			<input type="hidden" name="${_csrf.parameterName }"
				value="${_csrf.token }" />
		</form>
	</div>
	<!-- /.navbar-header -->
	
	<ul class="nav navbar-top-links navbar-left">
		
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">게시판 메뉴1 <i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#"> 게시판1</a></li>
				<li><a href="#"> 게시판2</a></li>
				<li class="divider"></li>
				<li><a href="#"> 게시판3</a></li>
			</ul> <!-- /.dropdown-user -->
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->
	
	<ul class="nav navbar-top-links navbar-left">
		<li class="dropdown"><a class="dropdown-toggle" href="#">게시판 검색 </i></a>
	</ul>
	<!-- /.navbar-top-links -->

	<ul class="nav navbar-top-links navbar-right">
		<sec:authorize access="isAnonymous()">
			<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"><i class="fa fa-user fa-fw"></i><i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="/users/login"><i class="fa fa-sign-in fa-fw"></i> Login</a></li>
				<li><a href="/users/signup"><i class="fa fa-sign-in fa-fw"></i> Sign Up</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <sec:authentication property="principal.user.nickname" /> <i class="fa fa-user fa-fw"></i><i
				class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="/users/<sec:authentication property='principal.user.number'/>"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
				<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
				<li class="divider"></li>
				<li><a href="#" onclick="javascript:document.logoutForm.submit()"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
			</ul> <!-- /.dropdown-user -->
		</sec:authorize>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

</nav>


