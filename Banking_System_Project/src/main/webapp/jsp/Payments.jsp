
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Funds Transfer</title>
	</head>
	<body>
		<%@include file="HeaderPage.jsp" %>
		<div class="content-container">
		Pay from Account Number: ${accountid}
			<form action="/paymentaction" method="post">
				<div>
					<label for="Recipient" class="lbel">Recipient Name</label>
					<input type="text" class="texter" name="Recipient" id="Recipient" required>
				</div>
				<div>
					<label for="AccountNumber" class="lbel">Recipient Account Number</label>
					<input type="text" class="texter" name="AccountNumber" id="AccountNumber" required>
				</div>
				<div>
					<label for="Amount" class="lbel">Amount</label>
					<input type="text" class="texter" name="Amount" id="Amount" required>
				</div>
				<input type="submit" value="Confirm">
				<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				 
			</form>
		</div>
	</body>
</html>