<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default">
  <div class="container-fluid" id="myNavbar">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/capstone/"><img class ="navbarlogo" src="/capstone/img/brand_logo.png"></a>
    </div>



    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<c:choose>
      		<c:when test="${empty currentUser}">
      			<c:url var="loginHref" value="/login"/>
      			<li><a href="${loginHref}">Login</a></li>
      			<c:url var="registerHref" value="/register"/>
      			<li><a href="${registerHref}">Register</a></li>
      		</c:when>
      		<c:when test="${not empty currentUser}">
      			<c:url var="reportHref" value="/user/report"/>
      			<li><a href="${reportHref}">Report A Pothole</a></li>
      			<c:if test="${currentUser.userType == 3}">
      				<c:url var="adminHref" value="/admin/admin"/>
      				<li><a href="${adminHref}">Admin</a></li>
      			</c:if>
      			<c:if test="${currentUser.userType >= 2}">
      				<c:url var="potholeManagementHref" value="/employee/potholeManagement"/>
      				<li><a href="${potholeManagementHref}">Pothole Management</a></li>
      			</c:if>
      			<c:url var="logoutHref" value="/logout"/>
      			<li><a href="${logoutHref}">Logout</a></li>
      		</c:when>
      	</c:choose>

      </ul>

    </div>
  </div>
</nav>