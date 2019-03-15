    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Employee Registration</title>
        </head>

<body>
<p align="center">To delete an employee please provide the following information.</p>
<form id="EmployeeDelete"  action="emp_delete" method="post">

                <table align="center">
                    <tr>
                    	<td></td>
                    </tr>
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
                    <tr>
                        <td></td>
                        <td align="left">
                            <button id="emp_del_search" name="action" value="emp_del_search">Submit</button>
                        </td>
                    </tr>
                </table>
                
</form> 
<table align="center">
                <tr>
                    <td >${message}</td>
                </tr>
            </table>                   
</body>
</html>
