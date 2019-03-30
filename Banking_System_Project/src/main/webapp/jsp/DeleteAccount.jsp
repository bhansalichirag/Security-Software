<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<body>
	<div class="content-wrapper">
		<%@include file="HeaderPageTier2.jsp"%>
	</div>
	<div class="col-md-12" id="page-content" align="center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Delete an Account</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" id="DeleteAccount"
					action="/deleteaccount" method="post">
					<fieldset>
						<div class="form-group">
							<label for="accountnumber" class="col-lg-2 control-label">Account
								Number</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" id="accountnumber"
									name="accountnumber" placeholder="Account Number" required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-7 col-lg-offset-2">
								<button type="reset" class="btn btn-default">Reset</button>
								<button id="delete_account" name="action" value="delete_account">Delete</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
									<sec:csrfInput /> 
							</div>
							<div>
								<p>${message}</p>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

</body>
</html>