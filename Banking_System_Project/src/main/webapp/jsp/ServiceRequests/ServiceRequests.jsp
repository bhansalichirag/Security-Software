<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Service Requests</title>
</head>
<body>
<%@include file="../HeaderPage.jsp" %>
<div style="display: flex; flex-direction: column; margin: auto">
	<button class="customButton" onclick="window.location.href = '/OrderCCheck'">Schedule Appointment</button>
	<button class="customButton" onclick="window.location.href = '/ScheduleAppointment'">Order Cashier's Check</button>
	<button class="customButton" onclick="window.location.href = '/OpenAccount'">Open New Account</button>
</div>

</body>
</html>
