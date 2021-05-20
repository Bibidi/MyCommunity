<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<body>

	<div id="wrapper">

		<!-- navigation -->
		<%@ include file="../includes/navigation.jsp"%>

		<!-- 일단 클래스로 레이아웃을 나누듯이 이름 지음. 나중에 고쳐야 됨.  -->
		<div class="contents-wrapper" id="page-wrapper">

			<div class="contents-view col-lg-9">

				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div class="login-panel panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Sign In</h3>
								</div>
								<div class="panel-body">
									<h5>
										<c:out value="${error}" />
									</h5>
									<h5>
										<c:out value="${logout}" />
									</h5>

									<form role="form">
										<fieldset>
											<div class="form-group">
												<input class="form-control" placeholder="ID" name="userId"
													type="text" autofocus>
											</div>
											<div class="form-group">
												<input class="form-control" placeholder="Password"
													name="userPassword" type="password" value="">
											</div>
											<div class="checkbox">
												<label> <input name="remember-me" type="checkbox"
													value="Remember Me">Remember Me
												</label>
											</div>
											<!-- Change this to a button or input when using this as a form -->
											<button class="btn btn-lg btn-success btn-block"
												formaction="/users/login" formmethod="post">Login</button>
										</fieldset>
										<input type="hidden" name="${_csrf.parameterName }"
											value="${_csrf.token}" />
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /#page-wrapper -->

	</div>

	<%@ include file="../includes/footer.jsp"%>