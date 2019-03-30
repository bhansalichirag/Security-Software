<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Account</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/cust_validate.js"></script>
<script src="js/jquery.validate.js"></script>
<script>
		
		$(function(){
		    var dtToday = new Date();
		    
		    var month = dtToday.getMonth() + 1;
		    var day = dtToday.getDate();
		    var year = dtToday.getFullYear();
		    if(month < 10)
		        month = '0' + month.toString();
		    if(day < 10) 
		        day = '0' + day.toString();
		    
		    var maxDate = year + '-' + month + '-' + day;
		    var minDate = year - 100 + '-' + month + '-' + day;
		    //alert(minDate);
		    $('#date_of_birth').attr('max', maxDate);
		    $('#date_of_birth').attr('min', minDate);
		});
			</script>
<div class="content-wrapper">
<c:choose>
		<c:when test="${role eq 'Individual'}"><%@include file="HeaderPage.jsp" %></c:when>
		<c:otherwise><%@include file="HeaderPageMerchant.jsp" %></c:otherwise>
	</c:choose>
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
			  			<p>${message }</p>
			  				<div class="form-group">
						      <label for="firstname" class="col-lg-2 control-label">First Name</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First Name">
						      </div>
						    </div>
						    
						    <div class="form-group">
						      <label for="lastname" class="col-lg-2 control-label">Last Name</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last Name">
						      </div>
						    </div>
						    
						    <div class="form-group">
						      <label for="firstname" class="col-lg-2 control-label">Email</label>
						      <div class="col-lg-5">
						        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
						      </div>
						    </div>
			  				
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
						      <label for="date_of_birth" class="col-lg-2 control-label">DOB</label>
						      <div class="col-lg-5">
						        <input type="date" class="form-control" name="date_of_birth" id="date_of_birth" placeholder="Date of Birth">
						      </div>
						    </div>
						    
						    <div class="form-group">
						      <label for="SecurityQuestion1" class="col-lg-2 control-label">What is your childhood nickname?</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" name="secquestion1" id="secquestion1" placeholder="Security Question 1">
						      </div>
						    </div>
						    
						    <div class="form-group">
						      <label for="SecurityQuestion2" class="col-lg-2 control-label">Who was your childhood hero?</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" name="secquestion2" id="secquestion2" placeholder="Security Question 2">
						      </div>
						    </div>
						     
						     <div class="form-group">
						      <div class="col-lg-7 col-lg-offset-2">
						      	<button type="reset" class="btn btn-default">Reset</button>
						        <button id="openaccount" name="action" value="open_account">Submit</button>
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