<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="content-wrapper">
		<div class="col-md-12" id="page-content" align="center">
			<div class="panel panel-primary">
  				<div class="panel-heading">
    				<h3 class="panel-title">Change Password</h3>
 				 </div>
	  			 <div class="panel-body">
					<form class="form-horizontal" id="ChangePassword" action="/passwordchanges" method="post">
			  			<fieldset>
			  				<div class="form-group">
						      <label for="email" class="col-lg-2 control-label">UserName</label>
						      <div class="col-lg-5">
						        <input type="text" class="form-control" name="username" id="username" placeholder="Username" required>
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="password" class="col-lg-2 control-label">Desired Password</label>
						      <div class="col-lg-5">
						        <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
						      </div>
						    </div>
						    <div class="form-group">
						      <label for="confirmpassword" class="col-lg-2 control-label">Confirm Password</label>
						      <div class="col-lg-5">
						        <input type="password" class="form-control" name="confirmpassword" id="confirmpassword" placeholder="Confirm Password" required>
						      </div>
						    </div>
			  				 <div class="form-group">
						      <div class="col-lg-7 col-lg-offset-2">
						      	<button type="reset" class="btn btn-default">Reset</button>
						        <button id="change_password" name="action" value="change_password">Submit</button>
						      </div>
						    </div>           
			  			</fieldset>
		  			</form>
	 			 </div>
			</div>
			
				
			</div>
		
		</div> <!-- .content-wrapper -->