<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Account</title>
</head>
<body>
<div class="content-wrapper">
<%@include file="HeaderPage.jsp" %>
		<div class="col-md-12" id="page-content" align="center">
			<div class="panel panel-primary">
  				<div class="panel-heading">
    				<h3 class="panel-title">Open an Account</h3>
 				 </div>
 			</div>
 		</div>
 		<div>
 		<div class="panel-body" align="center">
					<form class="form-horizontal" id="OpenAccount" action="/openaccount" method="post">
			  			<fieldset>
			  				<div class="form-group">
						      <label for="select" class="col-lg-2 control-label">Type of Account</label>
						      <div class="col-lg-5">
						        <select class="form-control" name="account_type" id="account_type" required>
						          <option value="">Select Option</option>
						          <option value="Savings">Savings Account</option>
						          <option value="CreditCard">Credit Card</option>
						          <option value="Checking">Checking Account</option>
						        </select>
						       </div>
						     </div>
						     <div class="form-group">
						      <div class="col-lg-7 col-lg-offset-2">
						      	<button type="reset" class="btn btn-default">Reset</button>
						        <button id="openaccount" name="action" value="open_account">Submit</button>
						      </div>
						    </div>
						     </fieldset>
						     </form>
 		</div>		 
	</div>
</div>
</body>
</html>