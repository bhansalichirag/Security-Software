<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/loginCSS.css" rel="stylesheet">
<title>Employee Login Page</title>
</head>
<body>
	<center>
		<h2>Login Form</h2>
	
		<form action="/redirectuser" method="post">
		  <a href="#" style="padding-left: 20px"> <img src="img/avatar.png"
						 style="vertical-align:top;"
						 width="35px" height="65px" alt="avatar"></a>
		   <div class="container">
		    <label for="uname"><b>Username</b></label>
		    <input type="text" placeholder="Enter Username" id = "userName" name="uname" required>	
		    <label for="psw"><b>Password</b></label>
		    <input type="password" placeholder="Enter Password" id = "password" name="psw" required>	        
		    <button type="submit">Login</button>	   
		  </div>	 
		  <div class="container" style="background-color:#f1f1f1;height:30px">
		    <span><a href="#">Forgot password?</a></span>
		  </div>
		  </form>
 	</center>
</body>
</html>