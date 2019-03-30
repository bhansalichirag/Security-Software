<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/loginCSS.css" rel="stylesheet">
<title>Login Page</title>
</head>
<div class="content-wrapper">
	<div class="col-md-12" id="page-content" align="center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Login Page</h3>
			</div>
			<div class="panel-body">
				<form id="LoginPage" action="/redirectuser" method="post">
					<a href="#" style="padding-left: 20px"> <img
						src="img/avatar.png" style="vertical-align: top;" width="35px"
						height="65px" alt="avatar"></a>
					<fieldset>
						<div class="form-group">
							<p>${message}</p>
						</div>
						<div class="form-group">
							<label for="uname"><b>Username</b></label> <input type="text"
								placeholder="Enter Username" id="userName" name="uname" required>
						</div>
						<div class="form-group">
							<label for="psw"><b>Password</b></label> <input type="password"
								placeholder="Enter Password" id="password" name="psw" required>
						</div>
						<div class="form-group">
							<button type="submit">Login</button>
								 
							<!-- <div class="form-group"
								style="background-color: #f1f1f1; height: 30px">
								<span><a href="/ForgotPassword">Forgot password?</a></span>
							</div> -->
							<div class="form-group"
								style="background-color: #f1f1f1; height: 30px">
								<span><a href="/NewCustomerRegister">New Customer?</a></span>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
<sec:csrfInput />
</div>