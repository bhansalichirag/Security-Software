<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/employee_validate.js"></script>
<script src="js/jquery.validate.js"></script>

	<div class="content-wrapper">
		<%@include file="HeaderPageTier3.jsp" %>
		<div class="col-md-12" id="page-content" align="center">
			<div class="panel panel-primary">
  				<div class="panel-heading">
    				<h3 class="panel-title">Employee Update</h3>
 				 </div>
	  			 <div class="panel-body">
					<form class="form-horizontal" id="EmployeeUpdate" action="emp_update" method="post">
			  			<fieldset>
			  				<div class="form-group">
						      <label for="email" class="col-lg-2 control-label">Email</label>
						      <div class="col-lg-5">
						        <input type="email" class="form-control" name="email" id="email" placeholder="Email" value=<%=request.getAttribute("Email") %>>
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="firstname" class="col-lg-2 control-label">First Name</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First Name" value=<%=request.getAttribute("FirstName") %>>
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="middlename" class="col-lg-2 control-label">Middle Name</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" id="middlename" name="middlename" placeholder="Middle Name" value=<%=request.getAttribute("MiddleName") %>>
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="lastname" class="col-lg-2 control-label">Last Name</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last Name" value=<%=request.getAttribute("LastName") %>>
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="phone" class="col-lg-2 control-label">Phone Number</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" name="phone" id="phone" placeholder="Phone" value=<%=request.getAttribute("Phone") %>>
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="date_of_birth" class="col-lg-2 control-label">DOB</label>
						      <div class="col-lg-5">
						        <input type="date" class="form-control" name="date_of_birth" id="date_of_birth" placeholder="Date of Birth" value=<%=request.getAttribute("DOB") %>>
						      </div>
						    </div>
						     
						    <div class="form-group">
						      <label for="ssn" class="col-lg-2 control-label">SSN</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" name="ssn" id="ssn" placeholder="SSN Number">
						      </div>
						    </div>
						    
						    <div class="form-group">
						      <label for="securityquestion1" class="col-lg-2 control-label">What is your childhood nickname?</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" name="seqquestion1" id="seqquestion1" placeholder="Security Question 1">
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="securityquestion2" class="col-lg-2 control-label">Who was your childhood hero?</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" name="seqquestion2" id="seqquestion2" placeholder="Security Question 2">
						      </div>
						    </div>
						    
						        <br>
						     <div class="form-group">
						      <div class="col-lg-7 col-lg-offset-2">
						      	<button type="reset" class="btn btn-default">Reset</button>
						        <button id="update_internal" name="action" value="update_internal">Submit</button>
						      </div>
						    </div>           
			  			</fieldset>
		  			</form>
	 			 </div>
			</div>
			
				
			</div>
		
		</div> <!-- .content-wrapper -->
	
	<script type="text/javascript">
	$(document).ready(function() {
		sideNavigationSettings();
	});
	</script>
</body>
</html>
