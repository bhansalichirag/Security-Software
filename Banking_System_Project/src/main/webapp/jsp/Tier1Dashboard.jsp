<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <!-- <link href="css/loginCSS.css" rel="stylesheet" /> -->
    <link
      href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <title>Login Page</title>
  </head>
  <body>
    <center>
      <h2>Tier 1 Dashboard</h2>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
          </tr>
          <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
          </tr>
          <tr>
            <th scope="row">3</th>
            <td>Larry</td>
            <td>the Bird</td>
            <td>@twitter</td>
          </tr>
        </tbody>
      </table>
      <p>
        Capabilities: Deposit Fund (button that leads to form) Issue Cashier
        Check (button to leads to form) Money Transfer (from 1 account to
        another) (button that leads to another form) 3 tabs on the side?
      </p>

      <form action="/redirectuser" method="post">
        <a href="#" style="padding-left: 20px">
          <img
            src="img/avatar.png"
            style="vertical-align:top;"
            width="35px"
            height="65px"
            alt="avatar"
        /></a>

        <div class="container">
          <label for="uname"><b>Username</b></label>
          <input
            type="text"
            placeholder="Enter Username"
            id="userName"
            name="uname"
            required
          />
          <label for="psw"><b>Password</b></label>
          <input
            type="password"
            placeholder="Enter Password"
            id="password"
            name="psw"
            required
          />
          <button type="submit">Login</button>
        </div>

        <div class="container" style="background-color:#f1f1f1;height:30px">
          <span><a href="#">Forgot password?</a></span>
        </div>
        <div class="container" style="background-color:#f1f1f1;height:30px">
          <span><a href="/NewCustomerRegister">New User?</a></span>
        </div>
        <!--<div class="container" style="background-color:#f1f1f1;height:30px">
		    <span><a href="jsp/Employee_Login.jsp">Employee Login</a></span>
		  </div>
		    --></form>
    </center>
  </body>
  <div>
    <table align="center">
      <tr>
        <td>${message}</td>
      </tr>
    </table>
  </div>
  <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
  <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>
