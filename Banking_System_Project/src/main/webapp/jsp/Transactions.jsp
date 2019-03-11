<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Transactions</title>
        <link rel="stylesheet" href="css/cstyles.css"/>   
	</head>
	<body>
		<%@include file="HeaderPage.jsp" %>
		<div class="content-container">
			<table>
				<thead>
					<tr>
						<th>Date</th>
						<th>Transaction ID</th>
						<th>Recipient Account Number</th>
						<th>Amount</th>
						<th>Is Approved</th>
					</tr>
				</thead>
				<tbody>
				    <c:forEach items="${transactions}" var="transaction">
				    	<tr>
				    		<td>${transaction.date}</td>
				    		<td>${transaction.transactionID}</td>
				    		<td>${transaction.payee.accountNumber}</td>
				    		<td>$ ${transaction.amount}</td>
				    		<td>${transaction.approvalStatus}</td>
				    	</tr>
				    </c:forEach>
			    </tbody>
		    </table>
		</div>
	</body>
</html>