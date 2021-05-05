<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/resources/vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">
		
		<!-- Navigation -->
		<%@include file="../includes/adminNavigation.jsp" %>

        <div id="admin-page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">게시판 생성</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
            	<div class="col-lg-12">
                	<div class="panel panel-default">
                    	<div class="panel-heading">
                        	<h3 class="panel-title">게시판 생성</h3>
                    	</div>
                    	<div class="panel-body">
                       		<form role="form">
                       			<fieldset>
                       				<div class="container-fluid">
                                		<div class="form-group row">
											<label for="" class="col-md-3 col-form-label">게시판 이름</label>
											<div class="col-md-9">
												<input class="form-control" placeholder="" maxlength="30" name="forumName" type="text" autofocus>
											</div>
                                		</div>
                                		<div class="form-group row">
											<label for="" class="col-md-3 col-form-label">게시판 설명</label>
											<div class="col-md-9">
												<input class="form-control" placeholder="" maxlength="255" name="forumDescription" type="text" autofocus>
											</div>
                                		</div>
                                		<div class="form-group row">
											<label for="" class="col-md-3 col-form-label">게시판 Slug</label>
											<div class="col-md-9">
												<input class="form-control" placeholder="" maxlength="30" name="forumSlug" type="text" autofocus>
											</div>
                                		</div>
                                
                                		<!-- Change this to a button or input when using this as a form -->
                                		<button class="btn btn-primary" formaction="/admin/forums" formmethod="post">제출</button>
                                	</div>
                            </fieldset>
                            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" />
                        </form>
                    </div>
                </div>
            </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="/resources/vendor/raphael/raphael.min.js"></script>
    <script src="/resources/vendor/morrisjs/morris.min.js"></script>
    <script src="/resources/data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js"></script>

</body>

</html>