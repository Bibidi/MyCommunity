<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/header.jsp"%>

<body>
    <div id="wrapper">
    
    	<%@include file="../includes/navigation.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">회원가입</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
           
            
            <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Sign Up</h3>
                    </div>
                    <div class="panel-body">
                    	
                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="아이디 (6~12자 이내, 영문/숫자 외 사용 불가)" maxlength="12" name="userId" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="비밀번호 (8자 이상, 영문/숫자/!, @, # 사용 가능)" maxlength="30" name="userPassword" type="password">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="이메일 (255자 이내)" maxlength="255" name="userEmail" type="text">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="닉네임 (최대 8글자 한글 1자, 영문/숫자는 0.5자)" maxlength="16" name="userNickname" type="text">
                                </div>
                                
                                <!-- Change this to a button or input when using this as a form -->
                                <button class="btn btn-lg btn-success btn-block" formaction="/users/signup" formmethod="post">Sign Up</button>
                            </fieldset>
                            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
            
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
	
	<%@ include file="../includes/footer.jsp"%>