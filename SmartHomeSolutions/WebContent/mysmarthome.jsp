<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page 
import="com.dao.*" import="com.entities.*" import="com.Info"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SHS : My smart home</title>
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link href="css/header.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
window.onload = function alls(){
    Dates();
    UsersTimes()
    }
 
function Dates(){
    var now = new Date();
     
    var Days = now.getDate() ;
    var Months = now.getMonth()+ 1;
    var years = now.getFullYear();
    if (Days < 10)
    Days ="0"+ Days;
     if (Months < 10)
    Months ="0"+ Months;
     
    document.getElementById("Dates").innerHTML ="<h1><center>"+Days +"."+Months+"." +years + "</center></h1>";
    }
function UsersTimes(){
    var now = new Date(),hours = now.getHours(),minutes= now.getMinutes(),seconds =now.getSeconds();
    if (minutes < 10)
    minutes ="0"+ minutes;
     if (hours < 10)
    hours ="0"+ hours;
    if (seconds < 10)
    seconds ="0"+ seconds;
    document.getElementById("Times").innerHTML = "<h1><center>"+hours + ":" + minutes + ":" +seconds+ "</center></h1>";
    setTimeout(UsersTimes,1000);
    if(hours==00 && minutes==00 &&seconds==00)
    Dates();
    }
</script>
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
	<li class="mydevices"><a href="#">Lighting</a></li>
	<li class="mydevices"><a href="#">Climate</a></li>
	<li class="mydevices"><a href="#">Security</a></li>
	<li class="mydevices"><a href="#">Entertainment</a></li>
	<li class="myrooms"><a href="myrooms.jsp">My Rooms</a></li>
	<li class="mymods"><a href="#">My Modes</a></li>
	<li class="myprofile"><a href="#">My Profile</a></li>
	</ul>
</div>


<h3>my home</h3>
<div id="cont_16010f8627e7b4129e789f3b0b6152d5">
  <span id="h_16010f8627e7b4129e789f3b0b6152d5">Forecast</span>
  <script type="text/javascript" src="http://www.pogoda.com/wid_loader/16010f8627e7b4129e789f3b0b6152d5"></script>
</div>
<div id="Dates" style="border-bottom: 1px solid black ; width: 17%; margin: 0 auto"> </div>
<div id="Times"> </div>

 <a href="users.html"> <strong>All Users</strong> </a>
 <br>
 <a href="myrooms"> <strong>Your Rooms</strong> </a>
</body>
</html>