<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="../HeaderPage.jsp"%>
	<c:choose>
		<c:when test="${role = 'Merchant'}">
			<div class="card">
				<div id="other" class="" aria-labelledby="headingTwo"
					data-parent="#accordion">
					<form method="post" class="card-body" action="/paymentactioncc"
						class="card-body" style="text-align: left;">
						<div class="input-group mb-3">
							<label>Customer Credit Card Number</label> <input type="number"
								step="1" pattern="[0-9]{,5}" class="form-control"
								placeholder="Customer Credit Card Number" name="Account"
								aria-describedby="basic-addon1">
						</div>
						<div class="input-group mb-3">
							<label>Customer CVV Number</label> <input type="number"
								oninvalid="this.setCustomValidity('Must be a 3 digit number')"
								pattern="[0-9]{3}" class="form-control" step="1"
								placeholder="Customer CVV Number" name="CVV"
								aria-describedby="basic-addon2">
						</div>
						<div class="input-group mb-3">
							<label>Amount</label> <input type="number" class="form-control"
								min="1" max="${balance}" placeholder="Amount" name="Amount"
								required="required">
						</div>
						<div class="input-group">
							<input type="submit" class="btn btn-success" value="Request">
						</div>
					</form>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="card">
				<div id="other" class="" aria-labelledby="headingTwo"
					data-parent="#accordion">
					<form method="post" class="card-body" action="/paymentactioncc"
						class="card-body" style="text-align: left;">
						<div class="input-group mb-3">
							<label>Merchant Account Number</label> <input type="number"
								step="1" pattern="[0-9]{,5}" class="form-control"
								placeholder="Customer Credit Card Number" name="Account"
								aria-describedby="basic-addon1">
						</div>
						<div class="input-group mb-3">
							<label>Enter Your CVV Number</label> <input type="number"
								oninvalid="this.setCustomValidity('Must be a 3 digit number')"
								pattern="[0-9]{3}" class="form-control" step="1"
								placeholder="Enter Your CVV Number" name="CVV"
								aria-describedby="basic-addon2">
						</div>
						<div class="input-group mb-3">
							<label>Amount</label> <input type="number" class="form-control"
								min="1" max="${balance}" placeholder="Amount" name="Amount"
								required="required">
						</div>
						<div class="input-group">
							<input type="submit" class="btn btn-success" value="Request">
						</div>
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

</body>
</html>