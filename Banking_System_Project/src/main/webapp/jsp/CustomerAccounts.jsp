<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Accounts</title>
        <link rel="stylesheet" href="css/cstyles.css"/>     
	</head>
	<body>
		<div class="accounts-container cards">
		<div>Howdy ${user}!</div>
		<label>Accounts</label>
		<c:forEach var="entry" items="${savings}">
			<div class="account-detail cards">
			<div>
				<div class="account-header">
					<h1>Savings Account Number: ${entry.accountNumber}</h1>
					<span></span>
				</div>
				<div class="account-body">
					<div>
						<h2>
							<label>Balance: </label>
							<label>$ ${entry.balance}</label>
						</h2>
					</div>
					<div>
						<label>Interest Rate: </label>
						<label>${entry.interest}%</label>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
		<c:forEach var="entry" items="${checking}">
			<div class="account-detail cards">
			<div>
				<div class="account-header">
					<h1>Checking Account Number: ${entry.accountNumber}</h1>
					<span></span>
				</div>
				<div class="account-body">
					<div>
						<h2>
							<label>Balance: </label>
							<label>$ ${entry.balance}</label>
						</h2>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
		<c:forEach var="entry" items="${creditcards}">
			<div class="account-detail cards">
			<div>
				<div class="account-header">
					<h1>Credit Card Number: ${entry.accountNumber}</h1>
					<span></span>
				</div>
				<div class="account-body">
					<div>
						<h2>
							<label>Current Balance: </label>
							<label>$ ${entry.balance}</label>
						</h2>
					</div>
					<div>
						<label>Available Balance: </label>
						<label>$ ${10000 - entry.balance}</label>
					</div>
					<div>
						<label>Next Payment Due: </label>
						<label>Never</label>
					</div>
					<div>
						<label>APR Charged: </label>
						<label>${entry.interest}%</label>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
		
		
		</div>
	
	</body>
</html>