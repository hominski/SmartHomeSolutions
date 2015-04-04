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
        <title>SHS : Edit Room</title>
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
<%
    int id = Integer.parseInt(request.getParameter("id"));
    RoomEnt room = DAO.INSTANCE.getRoomById(id);
    request.setAttribute("room", room);
%>
<%
request.setAttribute("rooms", DAO.INSTANCE.getRoomsByUserId(user.getUserId()));
request.setAttribute("types", DAO.INSTANCE.getAllRoomTypes());
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
	<li class="myhome"><a href="#">My Smart Home</a></li>
	<li class="mydevices"><a href="#">My Devices</a></li>
	<li class="myrooms"><a href="#">My Rooms</a></li>
	<li class="mymods"><a href="#">My Modes</a></li>
	<li class="myprofile"><a href="#">My Profile</a></li>
	</ul>
</div>


<jsp:useBean class="com.dao.DAO" id="myDAO" scope="application"/>

<div class="content">
	<ul class="features">





<c:forEach var="RoomEnt" items="${rooms}">

<c:if test="${RoomEnt.getRoomType() eq 'kitchen'}">
   <li class="feature">
    <a href="#">
    <div class="kitchen"></div>    
    <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
      <div class="data">
      	
        <p>Name of the Room : 
        <c:choose>
        <c:when test="${RoomEnt.roomid == room.getroomid()}">
        <input type="text" value="${RoomEnt.getRoomName()}">
        </c:when>
        <c:otherwise>
        ${RoomEnt.getRoomName()}
        </c:otherwise>
        </c:choose>
        </p>
         
        <p>Type of the Room : 
        <c:choose>
        <c:when test="${RoomEnt.roomid == room.getroomid()}">
        <select>
        <option value="${RoomEnt.getRoomType()}">${RoomEnt.getRoomType()}</option>
        <c:forEach var="RoomType" items="${types}">  
        <option value="${RoomEnt.getRoomType()}">${RoomType.getRoomTypes()}</option>
        </c:forEach>
        </select>
        </c:when>
        <c:otherwise>
        ${RoomEnt.getRoomType()}
        </c:otherwise>
        </c:choose>
        </p>
        
        
        <a href="users.jsp?id=${RoomEnt.roomid}">${RoomEnt.getRoomName()}</a>
      </div>
      <div class="edit_remove">
        <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
        <input type="submit" value="EDIT">
        <input type="submit" value="REMOVE"
        onclick="if (confirm('Do you want to delete contact: ${RoomEnt.getRoomName()}?')) document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
      </div>
    </form>
    <div class="clear"></div>
    </a>
  </li>
</c:if>

<c:if test="${RoomEnt.getRoomType() eq 'livingroom'}">
  <li class="feature">
    <a href="#">
    <div class="living_room"></div>    
    <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
      <div class="data">
        <p>Type of the Room : ${RoomEnt.getRoomType()}</p>
        <p> Name of the Room : ${RoomEnt.getRoomName()}</p>
      </div>
      <div class="edit_remove">
        <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
        <input type="submit" value="EDIT">
        <input type="submit" value="REMOVE"
        onclick="document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
      </div>
    </form>
    <div class="clear"></div>
    </a>
  </li>
</c:if>

<c:if test="${RoomEnt.getRoomType() eq 'bedroom'}">
  <li class="feature">
    <a href="#">
    <div class="bedroom"></div>    
    <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
      <div class="data">
        <p>Type of the Room : ${RoomEnt.getRoomType()}</p>
        <p> Name of the Room : ${RoomEnt.getRoomName()}</p>
      </div>
      <div class="edit_remove">
        <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
        <input type="submit" value="EDIT">
        <input type="submit" value="REMOVE"
        onclick="document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
      </div>
    </form>
    <div class="clear"></div>
    </a>
  </li>
</c:if>

<c:if test="${RoomEnt.getRoomType() eq 'bathroom'}">
  <li class="feature">
    <a href="#">
    <div class="bathroom"></div>    
    <form id="deleteForm${RoomEnt.roomid}" action="delete" method="post">
      <div class="data">
        <p>Type of the Room : ${RoomEnt.getRoomType()}</p>
        <p> Name of the Room : ${RoomEnt.getRoomName()}</p>
      </div>
      <div class="edit_remove">
        <input name="id" type="hidden" value="${RoomEnt.roomid}"/>
        <input type="submit" value="EDIT">
        <input type="submit" value="REMOVE"
        onclick="document.getElementById('deleteForm${RoomEnt.roomid}').submit();">
      </div>
    </form>
    <div class="clear"></div>
    </a>
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