<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Employee Registration</title>
        </head>

<body>
<form:form id="emp_register" modelAttribute="emp_register" action="index2" method="post">
                <table align="center">
                    <tr>
                        <td>
                            <form:label path="firstname">First Name: </form:label>
                        </td>
                        <td>
                            <form:input path="firstname" name="firstname" id="firstname"  />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="middlename">Middle Name: </form:label>
                        </td>
                        <td>
                            <form:input path="middlename" name="middlename" id="middlename"  />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <form:label path="lastname">Last Name: </form:label>
                        </td>
                        <td>
                            <form:input path="lastname" name="lastname" id="lastname"  />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <form:label path="username">User Name: </form:label>
                        </td>
                        <td>
                            <form:input path="username" name="username" id="username"  />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">Password:</form:label>
                        </td>
                        <td>
                            <form:password path="password" name="password" id="password" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="confirmpassword">Confirm Password:</form:label>
                        </td>
                        <td>
                            <form:password path="confirmpassword" name="confirmpassword" id="confirmpassword" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="gender"> Gender : </form:label>
                        </td>
                        <td>
                            <INPUT TYPE="radio" name="gender" value="Female"/>Female
							<INPUT TYPE="radio" name="gender" VALUE="Male"/>Male
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <form:label path="DOB">Date Of Birth: </form:label>
                        </td>
                        <td>
                            <form:input path="DOB" name="DOB" id="DOB"  />
                        </td>
                        <td>
                        	<label>Format: mm/dd/yyyy</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="Access">Access Level: </form:label>
                        </td>
                        <td>
                            <INPUT TYPE="radio" name="access" value="tier1"/>tier1
							<INPUT TYPE="radio" NAME="access" VALUE="tier2"/>tier2
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="phonenumber">Phone Number:</form:label>
                        </td>
                        <td>
                            <form:input path="phonenumber" name="confirmpassword" id="phonenumber" />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <form:label path="email">Email Address:</form:label>
                        </td>
                        <td>
                            <form:input path="email" name="email" id="email" />
                        </td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td align="left">
                            <form:button id="emp_register" name="action" value="register">Register</form:button>
                        </td>
                    </tr>
                    
                     
                    
                    <tr></tr>
                   
                </table>
            </form:form>
            <table align="center">
                <tr>
                    <td >${message}</td>
                </tr>
            </table>
        </body>
</html>