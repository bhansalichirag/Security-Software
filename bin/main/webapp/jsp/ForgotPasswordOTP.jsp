<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<meta charset="ISO-8859-1">
<body>
  				<div class="panel-heading">
    				<h3 class="panel-title">Forgot Password OTP</h3>
 				 </div>
	  			 <div class="container">
		<div class="starter-template">
			<h2>OTP - Validate your OTP</h2>
			<h3>Hello :
				[[${#httpServletRequest.remoteUser}]]!</h3>
			<form id="validateOtp" name="validateOtp" method="post">
				<fieldset>
					<div if="${param.error}">
						<div class="alert alert-danger">Invalid Otp Try Again</div>
					</div>
					<div class="form-group">
						<input type="text" name="otpnum" id="otpnum"
							class="form-control input-lg" required autofocus />
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<input type="submit" class="btn btn-lg btn-primary btn-block"
								value="Submit" />
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6"></div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript">
$(document).ready(function () {
    $("#validateOtp").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        var data  = 'otpnum='+$("#otpnum").val();
        alert(data);
        $.ajax({
            type: "GET",
            url:  "/validateOtp",
            data: data,
            dataType: 'text',
            cache: false,
            timeout: 600000,
            success : function(response) {
                    alert( response );
                },
                error : function(xhr, status, error) {
                    alert(xhr.responseText);
                }
        });
    });
}); 
</script>
			  			
</body>
</html>