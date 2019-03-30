<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Service Requests</title>
</head>
<body>
	<%@include file="../HeaderPage.jsp"%>
	<sec:csrfInput /> 
	<div style="display: flex; flex-direction: column; margin: auto">
		<button class="customButton"
			onclick="window.location.href = '/generateAppointmentOtp'">Schedule
			Appointment</button>
		<button class="customButton"
			onclick="window.location.href = '/OrderCCheck'">Order
			Cashier's Check</button>
		<button class="customButton"
			onclick="window.location.href = '/generateAccountOtp'">Open New
			Account</button>
		<button class="customButton"
			onclick="window.location.href = '/PrimeAccount'">Set Primary
			Account</button>
	</div>

</body>
</html>
