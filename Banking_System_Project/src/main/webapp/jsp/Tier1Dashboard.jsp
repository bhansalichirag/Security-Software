<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
  <!-- <link href="css/dashboard.css" rel="stylesheet" /> -->
  <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />

  <title>Tier 1 Dashboard</title>
</head>

<body>
  <h2>Tier 1 Dashboard</h2>
  <p>
    Capabilities: Deposit Fund (button that leads to form) Issue Cashier
    Check (button to leads to form) Money Transfer (from 1 account to
    another) (button that leads to another form) 3 tabs on the side?
  </p>
  <div class="container" />
  <div class="row">
    <div className="col-5" id="sidebar">
      <ul class="list-group">
        <a class="list-group-item list-group-item-action <c:if test="${param.sidebar == 0}">active</c:if>" href="Tier1Dashboard?sidebar=0">Deposit Fund</a>
        <a class="list-group-item list-group-item-action <c:if test="${param.sidebar == 1}">active</c:if>" href="Tier1Dashboard?sidebar=1">Issue Cashier Check</a>
        <a class="list-group-item list-group-item-action <c:if test="${param.sidebar == 2}">active</c:if>" href="Tier1Dashboard?sidebar=2">Money Transfer</a>
      </ul>
    </div>

    <!-- Parameter myparam: <%= request.getParameter("myparam") %> -->
    <c:choose>
      <c:when test="${param.sidebar == 0}">
        <!-- render deposit fund form -->
        <div className="col col-offset-2">
          <h1>Deposit Fund</h1>
          <form name='depositForm'>
            <div class="form-group">
              <label for="targetAccountInput">Target Account</label>
              <input type="text" class="form-control" id="targetAccountInput" aria-describedby="targetAccountHelp"
                placeholder="Account#">
              <small id="targetAccountHelp" class="form-text text-muted">Which account would you like to deposit the money
                in?</small>
            </div>
            <div class="form-group">
              <label for="depositAmountInput">Target Account</label>
              <input type="text" class="form-control" id="depositAmountInput" placeholder="$$$">
            </div>
            
            <button type="button" onclick="javascript:SubmitForm()">Test Function</button>
            
            <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
            
            <script type="text/javascript">
              SubmitForm = function () {
                console.log("Test Function activated.");
                console.log(document.getElementById("targetAccountInput").value)
                depositForm.submit();
              }
            </script>
          </form>
        </div>
        <!-- <c:set var = "sideBarSelection" scope = "session" value = "${1}"/> -->
      </c:when>

      <c:when test="${param.sidebar == 1}">
        <!-- render cashier check form -->
        <div className="col col-offset-2">
          <h1>Issue Cashier Check</h1>
          <form name='issueCashierCheckForm'>
            <div class="form-group">
              <label for="targetAccountInput">Target Account</label>
              <input type="text" class="form-control" id="targetAccountInput" aria-describedby="targetAccountHelp"
                placeholder="Account#">
              <small id="targetAccountHelp" class="form-text text-muted">Which account would you like to deposit the
                money in?</small>
            </div>
            <div class="form-group">
              <label for="depositAmountInput">Target Account</label>
              <input type="text" class="form-control" id="depositAmountInput" placeholder="$$$">
            </div>

            <button type="button" onclick="javascript:SubmitForm()">Test Function</button>

            <!-- <button type="submit" class="btn btn-primary">Submit</button> -->

            <script type="text/javascript">
              SubmitForm = function () {
                console.log("Test Function activated.");
                console.log(document.getElementById("targetAccountInput").value)
                depositForm.submit();
              }
            </script>

          </form>
        </div>
      </c:when>
      <c:when test="${param.sidebar == 2}">
        <!-- render money transfer form -->
        <div className="col col-offset-2">
          <h1>Money Transfer</h1>

          <form name='moneyTransferForm'>
              <div class="form-group">
                <label for="fromAccount">From Account</label>
                <input type="text" class="form-control" id="fromAccount" aria-describedby="targetAccountHelp"
                  placeholder="Account#">
                <small id="targetAccountHelp" class="form-text text-muted">Which account would you like to deposit the money
                  in?</small>
              </div>
              <div class="form-group">
                <label for="toAccount">To Account</label>
                <input type="text" class="form-control" id="toAccount" aria-describedby="targetAccountHelp"
                  placeholder="Account#">
                <small id="targetAccountHelp" class="form-text text-muted">Which account would you like to deposit the money
                  in?</small>
              </div>
              <div class="form-group">
                <label for="depositAmountInput">Target Account</label>
                <input type="text" class="form-control" id="depositAmountInput" placeholder="$$$">
              </div>
  
              <button type="button" onclick="javascript:SubmitForm()">Test Function</button>
  
              <!-- <button type="submit" class="btn btn-primary">Submit</button> -->
  
              <script type="text/javascript">
                SubmitForm = function () {
                  console.log("Test Function activated.");
                  console.log(document.getElementById("targetAccountInput").value)
                  depositForm.submit();
                }
              </script>
            </form>
          
        </div>
      </c:when>
    </c:choose>




  </div>
  <!--End Row p-1-->
  </div>
  <!--End Container-->
</body>

<div>
  <table align="center">
    <tr>
      <td>${message}</td>
    </tr>
  </table>
</div>



<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>\

</html>