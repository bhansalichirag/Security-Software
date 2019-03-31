<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/jquery.keyboard.js"></script>
<script src="js/jquery.keyboard.extension-typing.js"></script>
<script src="js/jquery.mousewheel.js"></script>
<link rel="stylesheet" href="css/keyboard.css">
<link rel="stylesheet" href="css/jquery-ui.css">
</head>
<body>
	<div th:replace="header :: header" />
	<div class="container">
		<div class="starter-template">
			<h2>OTP - Validate your OTP</h2>
			<form id="validateOtp" action="/ScheduleAppointment" method="post">
				<fieldset>
					<%-- <div th:if="${param.error}">
						<div class="alert alert-danger">Invalid Otp Try Again</div>
					</div> --%>
					<div class="form-group">
						<input type="number" name="otpnum" id="otpnum" step="1"
							max="1000000" class="form-control input-lg" required="true"
							autofocus="true" />
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<button id="otpsender" class="btn btn-lg btn-primary btn-block">Submit</button>

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6"></div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<script type="text/javascript">
$(document).ready(function () {
    $("#otpsender").click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        var data  = 'otpnum='+$("#otpnum").val();
        $.ajax({
            type: "GET",
            url:  "/validateOtp",
            data: data,
            dataType: 'text',
            cache: false,
            timeout: 600000,
            success : function(response) {
                    alert( response );
                    $( "#validateOtp" ).submit();
                },
                error : function(xhr, status, error) {
                    alert(xhr.responseText);
                    return false;
                }
        });
    });
}); 

$('#otpnum')
.keyboard({
	layout : 'num',
	restrictInput : true, // Prevent keys not in the displayed keyboard from being typed in
	preventPaste : true,  // prevent ctrl-v and right click
	autoAccept : true
})
.addTyping();
</script>
</body>
</html>