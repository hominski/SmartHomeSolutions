<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page 
import="com.dao.*" import="com.entities.*" import="com.Info"%>
<%@ page import ="java.util.*" %>

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
<%
Integer i;
i=0;
ArrayList<RoomEnt> RoomList = new ArrayList<RoomEnt>();
for (RoomEnt e : myDAO.getRoomsByUserId(user.getUserId())) {
	RoomList.add(new RoomEnt(e.getRoomId(),e.getUserId(),e.getRoomName(),e.getRoomType()));
	i++;}

for(RoomEnt e : RoomList)
{
	if(e.getRoomType().equalsIgnoreCase("living room")){
		
%>
 							<li class="feature">
                                <a href="#">
                           <div class="living_room">
                            </div>    
                             <div>
                                    <h1>Type of the Room : <%=e.getRoomType() %></h1>
                                    <p>
                                       Name of the Room : <%=e.getRoomName()%>
                                    </p>

                                </div>
                                <div class="clear"></div>
                                </a>
                            </li>


<%;};
if(e.getRoomType().equalsIgnoreCase("bathroom")){%>

 <li class="feature">
     <a href="#">
     <div class="bathroom"></div>
         <div>
         <form>
         <p>Name of the Room :  <input type="text" name = "RName" value="<%=e.getRoomType() %>"></p>
         <p>Type of the Room : 
                            	<select>
                                  	<option value="<%=e.getRoomType()%>"><%=e.getRoomType()%></option>    
            						<%for (RoomType t : myDAO.getAllRoomTypes()){ %>
          							 <option value="<%=t.getRoomType()%>"><%=t.getRoomType()%></option>
         							<% } %>
        						</select>
         </p>
     	 </form>
     	 <div class="edit_save_remove">
         <input type="submit" value="EDIT">
         <input type="submit" value="SAVE">
         <input type="submit" value="REMOVE">
         </div>
     </div>
     <div class="clear"></div>
     </a>
 </li>
 <%;};

if(e.getRoomType().equalsIgnoreCase("bedroom")){%>

 <li class="feature">
                                <a href="#">
                                <div class="bedroom"></div>
                                <div>
                               <h1>Type of the Room : <%=e.getRoomType() %></h1>
                                    <p>
                                       Name of the Room : <%=e.getRoomName()%>
                                    </p>
                                    
                                    
                                    
                                </div>
                                <div class="clear"></div>
                                </a>
                            </li><!-- end of: FEATURE  -->              
                            
                            
                             <%;};
if(e.getRoomType().equalsIgnoreCase("kitchen")){%>

 <li class="feature">
                                <a href="#">
                                <div class="kitchen"></div>
                                <div>
                                    <h1>Type of the Room : <%=e.getRoomType() %></h1>
                                    <p>
                                       Name of the Room : <%=e.getRoomName()%>
                                    </p>
                                    <a href="#">link # <%=i%></a> 
                                    
                                </div>
                                <div class="clear"></div>
                                </a>
                            </li><!-- end of: FEATURE  -->             
                                                        
<%;}i++;}%>


                                   <div class="clear"></div>
        </ul><!--end of Features -->
	
<form  method="post" action="addroom">

<input type="submit" value="ADD ROOM">

</form>       
</div>


                          
</body>
</html>