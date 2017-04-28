<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:import url="/WEB-INF/jsp/common/header.jsp" />
	<script src="js/custom/validateRegisterLogin.js"></script>
</head>
<body>

<c:import url="/WEB-INF/jsp/common/navbar.jsp"/>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="pr-wrap">
                <div class="pass-reset">
                    <label>
                        Enter the email you signed up with</label>
                    <input type="email" placeholder="Email" />
                    <input type="submit" value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
                </div>
            </div>
            <div class="wrap">
                <p class="form-title">
                    Register</p>
                    <c:url var = "formAction" value ="/register"/>
                <form method= "POST" action="${formAction}" class="login" id="register">
                <input name= "email" type="text" placeholder="Email" />
                <input name= "first_name" type="text" placeholder="First Name" />
                <input name= "last_name" type="text" placeholder="Last Name" />
                <input name= "phoneNumber" type="text" placeholder="Phone Number" />
                <input name= "password" type="password" placeholder="Password" id="password" />
                <input name = "passwordMatch" type="password" placeholder="Confirm Password" />
                <input type = "hidden" name= "CSRF_TOKEN" value = "${CSRF_TOKEN}"/>
                
                
                <input type="submit" value="register" id="registerButton" class="btn btn-success btn-sm" />
           		<c:if test="${param.registerError}">
					<div class="text-center alert alert-danger" style="margin-top: 10px"><p>That email is already associated with an account!</p></div>
					</c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>