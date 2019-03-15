    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Employee Registration</title>
        </head>

<body>
<h3 align="center">Employee Registration</h3>
<form id="EmployeeInsert" action="emp_insert" method="post">
                <table align="center">
                    <tr>
                        <td>
                            <label path="firstname">First Name: </label>
                        </td>
                        <td>
                            <input path="firstname" name="firstname" id="firstname"  />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label path="middlename">Middle Name: </label>
                        </td>
                        <td>
                            <input path="middlename" name="middlename" id="middlename"  />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label path="lastname">Last Name: </label>
                        </td>
                        <td>
                            <input path="lastname" name="lastname" id="lastname"  />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label path="username">User Name: </label>
                        </td>
                        <td>
                            <input path="username" name="username" id="username"  />
                        </td>
                    </tr>
                    <!-- <tr>
                        <td>
                            <label path="password">Password:</label>
                        </td>
                        <td>
                            <password path="password" name="password" id="password" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label path="confirmpassword">Confirm Password:</label>
                        </td>
                        <td>
                            <password path="confirmpassword" name="confirmpassword" id="confirmpassword" />
                        </td>
                    </tr>
                     -->
                     <tr>
                        <td>
                            <label>Address: </label>
                        </td>
                        <td>
                            <input id="Address" type="text"/>
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label path="DOB">Date Of Birth: </label>
                        </td>
                        <td>
                            <input path="DOB" name="DOB" id="DOB"  />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label path="Access">Access Level: </label>
                        </td>
                        <td>
                            <INPUT TYPE="radio" name="access" value="tier1"/>tier1
							<INPUT TYPE="radio" NAME="access" VALUE="tier2"/>tier2
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label path="phonenumber">Phone Number:</label>
                        </td>
                        <td>
                            <input path="phonenumber" name="confirmpassword" id="phonenumber" />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label path="email">Email Address:</label>
                        </td>
                        <td>
                            <input path="email" name="email" id="email" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td align="left">
                            <button id="Employee_Insert_submit" name="action" value="register">Register</button>
                        </td>
                    </tr>
                    
                     
                    
                    <tr></tr>
                   
                </table>
            </form>
            <table align="center">
                <tr>
                    <td >${message}</td>
                </tr>
            </table>
        </body>
</html>