<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page 
import="com.dao.*" import="com.entities.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
<h1>All Users:</h1>
        <jsp:useBean class="com.dao.DAO" id="myDAO" scope="application"/>
        <table border="1">
            <%
                        for (UserEnt e : myDAO.getAllEmp()) {
            %>
            <tr>
                <td><%=e.getName()%></td>
                <td><%=e.getLogin()%></td>
                <td><%=e.getMail()%></td>
                 <td><%=e.getPhone()%></td>
            </tr>
            <%
                        }
            %>
        </table>
</body>
</html>