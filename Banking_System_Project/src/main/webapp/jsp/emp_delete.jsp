<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Employee Registration</title>
        </head>

<body>
<p align="center">To delete an employee please provide the following information.</p>
<form:form id="emp_delete" modelAttribute="emp_delete" action="index3" method="post">

                <table align="center">
                    <tr>
                    	<td></td>
                    </tr>
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
                        <td></td>
                        <td align="left">
                            <form:button id="emp_del_search" name="action" value="emp_del_search">Submit</form:button>
                        </td>
                    </tr>
                </table>
                
</form:form> 
<table align="center">
                <tr>
                    <td >${message}</td>
                </tr>
            </table>                   
</body>
</html>
                   