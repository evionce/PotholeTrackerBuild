<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:import url="/WEB-INF/jsp/common/header.jsp" />
	<script src="js/custom/validateRegisterLogin.js"></script>
</head>
<body>

	<c:import url="/WEB-INF/jsp/common/navbar.jsp" />


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="pr-wrap">
					<div class="pass-reset">
						<label> Enter the email you signed up with</label> <input
							type="email" placeholder="Email" /> <input type="submit"
							value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
					</div>
				</div>
				<div class="wrap">
					<p class="form-title">Sign In</p>
					<c:url var="formAction" value="/login" />
					<form method="POST" action="${formAction}" class="login" id="login">
						<input name="userName" type="text" placeholder="Username" /> <input
							name="password" type="password" placeholder="Password" /> <input
							type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" /> <input
							type="submit" value="Sign In" class="btn btn-success btn-sm" />
							
						<!-- TODO: implement checkbox for remember me and forgot password functionality	 -->
						<!-- <div class="remember-forgot">
							<div class="row">
								<div class="col-md-6">
									<div class="checkbox">
										<label> <input type="checkbox" /> Remember Me
										</label>
									</div>
								</div>
								<div class="col-md-6 forgot-pass-content">
									<a href="javascription:void(0)" class="forgot-pass">Forgot
										Password</a>
								</div>
							</div>
						</div> -->
						<c:if test="${param.loginError}">
					<div class="text-center alert alert-danger" style="margin-top: 10px"><p>Incorrect Username or password.</p></div>
					</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>