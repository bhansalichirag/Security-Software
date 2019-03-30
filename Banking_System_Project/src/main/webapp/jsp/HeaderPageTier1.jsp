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
			            	<li class="cta"><a class="OutLineButton" href="/Tier1Dash">Home</a></li>
			                <li class="cta"><a class="OutLineButton" href="/SearchAccount">Customer Account Info</a></li>
			                <li class="cta"><a class="OutLineButton" href="/checker">Approve/Decline Transaction</a></li>
			                <li class="cta"><a class="OutLineButton" href="/IssueCheque">Issue Cashiers Cheque</a></li>
			                <li class="ctd"><a class="OutLineButton" href="/ChangePassword">Change Password</a></li>
			                <li class="cta"><a class="OutLineButton" href="/logout">Log Out</a></li>
<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/> 
			            </ul>
			        </nav>
				</header>
		    </div>
	    </div>
	</body>
</html>