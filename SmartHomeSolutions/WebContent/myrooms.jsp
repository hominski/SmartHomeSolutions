<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page 
import="com.dao.*" import="com.entities.*" import="com.Info"%>
<%@ page import ="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>SHS : My Rooms</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Smart Home Solutions - Ideas for Your Home" />
        <meta name="keywords" content="smart house, smart home" />
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link href="css/header.css" rel="stylesheet" type="text/css">
    </head>
    
    <body id="page">
<%
    UserEnt user = (UserEnt)request.getSession().getAttribute("user");
    if(user == null){
        response.sendRedirect("index.jsp");}
%>


<div class="container">
        <div class="codrops-top">
        <span class="right">
           You are logged in as  <a href="signup.html"> <strong><%=user.getLogin()%></strong> </a>
           <a href="logout"> <strong>Log Out</strong> </a>
        </span>
		<span class="left">
            <a href="login.html"> <strong>About</strong> </a>
            <a href="signup.html"> <strong>Contact Us</strong> </a>
			<a href="signup.html"> <strong>Help</strong> </a>
		</span>
		</div>
</div>

<div class="header_menu">
	<ul class="vmenu">
	<li class="myhome"><a href="mysmarthome.jsp">My Smart Home</a></li>
	<li class="mydevices"><a href="lighting.jsp">Lighting</a></li>
	<li class="mydevices"><a href="climate.jsp">Climate</a></li>
	<li class="mydevices"><a href="#">Security</a></li>
	<li class="mydevices"><a href="#">Entertainment</a></li>
	<li class="myrooms"><a href="myrooms.jsp">My Rooms</a></li>
	<li class="mymods"><a href="#">My Modes</a></li>
	<li class="myprofile"><a href="#">My Profile</a></li>
	</ul>
</div>


<jsp:useBean class="com.dao.DAO" id="myDAO" scope="application"/>

<div class="content">
	<ul class="features">

<%
request.setAttribute("rooms", myDAO.getRoomsByUserId(user.getUserId()));
%>


<c:forEach var="RoomEnt" items="${rooms}">


<%--KITCHEN--%>


<c:if test="${RoomEnt.getRoomType() eq 'Kitchen'}">
   <li class="feature">
    <div class="kitchen"></div>    
    <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
      <div class="data">
        <p>Type of the room : ${RoomEnt.getRoomType()}</p>
        <p> Name of the Room : ${RoomEnt.getRoomName()}</p>
        <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
      </div>
      <div class="edit_remove">
        <a href="editroom.jsp?id=${RoomEnt.roomid}">EDIT</a>
        <input type="submit" value="REMOVE"
        onclick="document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
      </div>
    </form>
    <div class="clear"></div>
   </li>
</c:if>

<%--LIVINGROOM--%>

<c:if test="${RoomEnt.getRoomType() eq 'Living room'}">
  <li class="feature">
    <div class="living_room"></div>    
     <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
      <div class="data">
        <p>Type of the Room : ${RoomEnt.getRoomType()}</p>
        <p> Name of the Room : ${RoomEnt.getRoomName()}</p>
        <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
      </div>
      <div class="edit_remove">
        <a href="editroom.jsp?id=${RoomEnt.roomid}">EDIT</a>
        <input type="submit" value="REMOVE"
        onclick="document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
      </div>
    </form>
    <div class="clear"></div>
  </li>
</c:if>

<c:if test="${RoomEnt.getRoomType() eq 'Bedroom'}">
  <li class="feature">
    <div class="bedroom"></div>    
 <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
      <div class="data">
        <p>Type of the Room : ${RoomEnt.getRoomType()}</p>
        <p> Name of the Room : ${RoomEnt.getRoomName()}</p>
        <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
      </div>
      <div class="edit_remove">
        <a href="editroom.jsp?id=${RoomEnt.roomid}">EDIT</a>
        <input type="submit" value="REMOVE"
        onclick="document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
      </div>
    </form>
    <div class="clear"></div>
  </li>
</c:if>

<c:if test="${RoomEnt.getRoomType() eq 'Bathroom'}">
  <li class="feature">
    <div class="bathroom"></div>    
     <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
      <div class="data">
        <p>Type of the Room : ${RoomEnt.getRoomType()}</p>
        <p> Name of the Room : ${RoomEnt.getRoomName()}</p>
        <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
      </div>
      <div class="edit_remove">
        <a href="editroom.jsp?id=${RoomEnt.roomid}">EDIT</a>
        <input type="submit" value="REMOVE"
        onclick="document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
      </div>
    </form>
    <div class="clear"></div>
  </li>
</c:if>

</c:forEach>
<div class="clear"></div>
</ul><!--end of Features -->

<form  method="post" action="addroom">
<input type="submit" value="ADD ROOM">
</form>       
</div>

               
</body>
</html>