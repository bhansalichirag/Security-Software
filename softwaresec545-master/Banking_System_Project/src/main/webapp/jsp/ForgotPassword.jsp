<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<body>
<div class="col-md-12" id="page-content" align="center">
			<div class="panel panel-primary">
  				<div class="panel-heading">
    				<h3 class="panel-title">Forgot Password</h3>
 				 </div>
	  			 <div class="panel-body">
					<form class="form-horizontal" id="ForgotPassword" action="/forgotpassword" method="post">
			  			<fieldset>
			  			<div class="form-group">
						      <label for="username" class="col-lg-2 control-label">User Name</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" id="username" name="username" placeholder="User Name" required>
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="email" class="col-lg-2 control-label">Email</label>
						      <div class="col-lg-5">
						        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
						      </div>
						    </div>
						    <div class="form-group">
						      <div class="col-lg-7 col-lg-offset-2">
						      	<button id="forgot_password" name="action" value="forgot_password">Request OTP</button>
						      	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
						      </div>
						    </div>
			  			</fieldset>
			  			</form>
			  			</div>
			  			</div>
			  			</div>
			  			
</body>
</html>