<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css">
<link rel="stylesheet" href="index.css">

</head>
<body>
	<%@include file="../HeaderPage.jsp" %>
	<div class="container text-center">
		<div class="row">
			<div class="col-sm-8" id="credit">
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default text-left">
							<div class="panel-body">
								<p contenteditable="false">${accountType} Detail</p>
								<p>
								<li><span>Account Number: ${accountid}</span>&nbsp;&nbsp; <span>$ ${balance}</span>&nbsp;&nbsp;
									 <span style="float: right;">Placeholder</span></li>
								</p>
							</div>
						</div>
					</div>
				</div>


				<c:forEach items="${transactions}" var="transaction">
					<tr>
						<div class="row">
							<div class="col-sm-3">
								<div class="well">
									<p>Transaction ID: ${transaction.transactionID}</p>
								</div>
							</div>
							<div class="col-sm-9">
								<div class="well">
									<p align="left">
										<b> <span>Status: </span> <span> <c:choose>
													<c:when test="${transaction.approvalStatus}">Approved</c:when>
													<c:otherwise>Pending</c:otherwise>
												</c:choose>
										</span>
										</b>
									</p>
									<p align="left">
										<span><c:choose>
												<c:when
													test="${transaction.payee.accountNumber == accountid}">Credit from</c:when>
												<c:otherwise>Debit to</c:otherwise>
											</c:choose> </span>&nbsp;&nbsp;<c:choose>
												<c:when
													test="${transaction.payee.accountNumber == accountid}">${transaction.payer.accountNumber}</c:when>
												<c:otherwise>${transaction.payee.accountNumber}</c:otherwise>
											</c:choose> <span style="float: right;">  <c:choose>
												<c:when
													test="${transaction.payee.accountNumber == accountid}">$ ${transaction.amount}</c:when>
												<c:otherwise>-$ ${transaction.amount}</c:otherwise>
											</c:choose>
										</span>
									</p>
								</div>
							</div>
						</div>
				</c:forEach>
			</div>
			<div class="col-sm-4 well" id="transfer">
				<div class="thumbnail">
					<p>Brief Statement</p>
				</div>
				<div class="well">
					<p>account1</p>
					<p>balance</p>
					<p>date</p>
				</div>
				<div class="well">
					<p>account1</p>
					<p>balance</p>
					<p>date</p>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>
</body>
</html>
