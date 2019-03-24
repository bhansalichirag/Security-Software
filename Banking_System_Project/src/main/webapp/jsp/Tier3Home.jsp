<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form id="tier3home" modelAttribute="tier3home" action ="index2" method="post">
    	<table align="center">
        	<tr>
	           <td>
                 <h2>TIER-3 HOME</h2> 
              </td>
            </tr>
            <tr>
	           <td>
                 To add a new employee in TIER-1 or TIER-2 level <a href="emp_register">Add Employee</a>
              </td>
            </tr>
            <tr>
            	<td>
                 To delete an employee in TIER-1 or TIER-2 level <a href="emp_delete">Delete Employee</a>
              </td>
            </tr>
    </table>
 </form:form>
</body>
</html>