<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
        <link rel="stylesheet" href="css/cstyles.css"/>     
	</head>
	<body>
		<div class="content-container">
			<div class="banner-container">
				<header role="banner">
			        <nav role="navigation">
			            <ul class="top-bar">
			            	<li class="cta"><a class="OutLineButton" href="/accinfo">Home</a></li>
			                <li class="cta"><a class="OutLineButton" href="/ChangePassword">Change Password</a></li>
			                <li class="cta"><a class="OutLineButton" href="/ServiceRequest">Service Requests</a></li>
			                <li class="cta"><a class="OutLineButton" href="/login">Log Out</a></li>
			                <sec:csrfInput /> 
			            </ul>
			        </nav>
				</header>
		    </div>
	    </div>
	</body>
</html>