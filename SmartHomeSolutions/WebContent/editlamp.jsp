<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.dao.*" import="com.entities.*" import="com.Info"%>
<%@ page import ="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SHS : Edit Lamp</title>
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
    LampEnt lamp = DAO.INSTANCE.getLampById(id);
    request.setAttribute("lamp", lamp);
%>
<%
request.setAttribute("lamps", DAO.INSTANCE.getLampsByUserId(user.getUserId()));
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
	<li class="mydevices"><a href="#">Lighting</a></li>
	<li class="mydevices"><a href="#">Climate</a></li>
	<li class="mydevices"><a href="#">Security</a></li>
	<li class="mydevices"><a href="#">Entertainment</a></li>
	<li class="myrooms"><a href="myrooms.jsp">My Rooms</a></li>
	<li class="mymods"><a href="#">My Modes</a></li>
	<li class="myprofile"><a href="#">My Profile</a></li>
	</ul>
</div>



<div class="content">
	<ul class="features">

<c:forEach var="LampEnt" items="${lamps}">


   		<c:choose>
        <c:when test="${LampEnt.getLampId() == lamp.getLampId()}">
        <li class="feature">
        </c:when>
        <c:otherwise>
        <li class="unactive_feature">
        </c:otherwise>
        </c:choose>
      
    <div class="lamp"></div>    
    <form id="deleteForm${LampEnt.getLampId()}" action="deletelamp" method="post">
      
        <div class="data">     	
        <p>Name of the Lamp :</p>
        <c:choose>
        <c:when test="${LampEnt.getLampId() == lamp.getLampId()}">
        <input name="name" type="text" value="${LampEnt.getLampName()}">
         <input name="id" type="hidden" value="${LampEnt.getLampId()}"/>
        </c:when>
        <c:otherwise>
        ${LampEnt.getLampName()}
        </c:otherwise>
        </c:choose>
        
        
        <p> Brightness :</p>
        <c:choose>
        <c:when test="${LampEnt.getLampId() == lamp.getLampId()}">
        <input type="range" name="points" min="0" max="100" value="${LampEnt.getBrightness()}"> 
        </c:when>
        <c:otherwise>
        <input type="range" name="points" min="0" max="100" value="${LampEnt.getBrightness()}" disabled> 
        </c:otherwise>
        </c:choose>
        
        <p> Color :</p>
        <c:choose>
        <c:when test="${LampEnt.getLampId() == lamp.getLampId()}">
        <input type="color" name="favcolor" value="${LampEnt.getColour()}">  
        </c:when>
        <c:otherwise>
        <input type="color" name="favcolor" value="${LampEnt.getColour()}" disabled> 
        </c:otherwise>
        </c:choose>
        
        <input name="id" type="hidden" value="${LampEnt.getLampId()}"/>
      </div>
      
      
      <div class="edit_remove">
      


      	<c:if test="${LampEnt.getLampId() == lamp.getLampId()}">
        <input type="submit" value="SAVE">
        <a href="lighting.jsp">CANCEL</a>
	  </c:if>
      </div>
    </form>
    <div class="clear"></div>
   </li>


</c:forEach>


<c:forEach var="LapmEnt" items="${rooms}">

<c:if test="${LampEnt.getRoomType() eq 'Kitchen'}">
   

    <div class="kitchen"></div>    
    <form id="editForm${RoomEnt.roomid}" action="edit" method="post">
      <div class="data">

      </div>
      <div class="edit_remove">
      
      <c:if test="${RoomEnt.roomid == room.getroomid()}">
        <input type="submit" value="SAVE">
        <a href="myrooms.jsp">CANCEL</a>
	  </c:if>
	  
      </div>
    </form>
    <div class="clear"></div>
   </li>
</c:if>









</c:forEach>
<div class="clear"></div>
</ul><!--end of Features -->
</div>

</body>
</html>