<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page 
import="com.dao.*" import="com.entities.*" import="com.Info"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
<%
    UserEnt user = (UserEnt)request.getSession().getAttribute("user");
    if(user == null){
        response.sendRedirect("index.jsp");}
%>

Hello, <%=user.getLogin()%>    

<h1>All Rooms:</h1>
        <jsp:useBean class="com.dao.DAO" id="myDAO" scope="application"/>
        <table border="1">
            <%
                        for (UserEnt e : myDAO.getAllEmp()) {
            %>
            <tr>
                <td><%=e.getLogin()%></td>
                <td><%=e.getName()%></td>
                <td><%=e.getMail()%></td>
                <td><%=e.getPhone()%></td>
            </tr>
            <% } %>
        </table>  
        
        <%
    request.setAttribute("rooms", myDAO.getRoomsByUserId(user.getUserId()));
%>

<table border="1">
    <caption>Room Table</caption>
    <tr>
    <th>Name</th>
    <th>Type</th>
    </tr>

    <c:forEach var="RoomEnt" items="${rooms}">
        <tr>
            <td><a href="users.jsp?id=${RoomEnt.roomid}">${RoomEnt.getRoomName()}</a></td>
            <td>${RoomEnt.getRoomType()}</td>
            <td>
                <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
                    <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
                    <input type="button"
                           value="Delete?"
                           onclick="if (confirm('Do you want to delete contact: ${RoomEnt.getRoomName()}?')) document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
                </form>
            </td>
        </tr>
    </c:forEach>
    
    
</table>
<a href="new.jsp">New room</a>

  
</body>
</html>