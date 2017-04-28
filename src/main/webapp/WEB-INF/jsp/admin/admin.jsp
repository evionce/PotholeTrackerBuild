<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:import url="/WEB-INF/jsp/common/header.jsp" />
	<script src="/capstone/js/custom/validateRegisterLogin.js"></script>
	<script src="/capstone/js/custom/adminEmailLookup.js"></script>
</head>
<body>

<c:import url="/WEB-INF/jsp/common/navbar.jsp"/>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div>
                <p class="form-title">
                    User Management</p>
                <form class="login" id="emailLookup">
                <input id="emailInput" name= "email" type="text" placeholder="Search for a user by email" />
                <input type = "hidden" name= "CSRF_TOKEN" value = "${CSRF_TOKEN}"/>
                <input type="submit" value="search" id="searchForUserByEmailButton" class="btn btn-success btn-sm" />
                <br>
                <br>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container">
	<div class="row">
   		 <div class="col-md-12" id="userResultsContainer">
        	<div>
        		<form class="login">
        		<label for="firstName">First Name: </label><input type="text" id="userFirstName" disabled name="firstName"/>
        		<label for="lastName">Last Name: </label><input type="text" id="userLastName" disabled name="lastName"/>
        		<label for="phone">Phone: </label><input type="text" id="userPhone" disabled name="phone"/>
        		<label for="type">User type: </label><input type="text" id="userType" disabled name="type"/>
        		<input type = "hidden" name= "CSRF_TOKEN" value = "${CSRF_TOKEN}"/>
        		<button type="button" class="btn btn-success" id="toggleUserToType" style="margin-top:15px; width:100%; display:none">Toggle User Type</button>
        		</form>
        	</div>
        </div>
    </div>
    <div class="row">
    	<div class="col-md-12" id="userResultsContainer">
    		
    	</div>
    </div>
</div>
<script>
function getCSRFTokenValue() {return "${CSRF_TOKEN}"};

$('#toggleUserToType').on('click', function(event){
	$.ajax({
		url: "http://localhost:8080/capstone/admin/api/toggleUserType",
		type: "POST",
		data: jQuery.param({ email: $('#emailInput').val(), CSRF_TOKEN : getCSRFTokenValue()}) ,
	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	}).done(function (data) {
		console.log(data);
		$('#userFirstName').val(data.firstName);
		$('#userLastName').val(data.lastName);
		$('#userPhone').val(data.phoneNumber);
		$('#userType').val(data.userType===1?"Regular App-User":"Employee");
	}).fail(function (xhr, status, error) {
		console.log(error);
	});
	return false;
});
</script>
</body>
</html>