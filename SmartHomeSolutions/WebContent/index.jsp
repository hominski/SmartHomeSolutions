    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page 
import="com.dao.*" import="com.entities.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
      <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>Smart Home Solutions</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Smart Home Solutions - Ideas for Your Home" />
        <meta name="keywords" content="smart house, smart home" />
    
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style1.css" />
	
		<!--<script type="text/javascript" src="js/modernizr.custom.86080.js"></script> -->
    </head>
    <body id="page">
        <ul class="cb-slideshow">
            <li><span>Image 01</span></li>
            <li><span>Image 02</span></li>
            <li><span>Image 03</span></li>
            <li><span>Image 04</span></li>
            <li><span>Image 05</span></li>
            <li><span>Image 06</span></li>
        </ul>
		
		
		    <div class="container">
        
            <div class="codrops-top">
                
                <span class="right">
              
                    <a href="login.html" >Log In</a>
               
                    <a href="signup.html" >Sign Up</a>
                </span>
                
                		
				<span class="left">
                    
                    <a href="login.html"> <strong>About</strong> </a>
                    <a href="signup.html"> <strong>Contact Us</strong> </a>
					
                </span>
                
            <div class="clr"></div>
            </div>
			
		
            <header>
           
                <H1>Smart Home Solutions</H1>
                <h2>Ideas for your home</h2>

            </header>
            
           
            
        </div>
        
          <h1>All Users:</h1>
        <jsp:useBean class="com.dao.DAO" id="myDAO" scope="application"/>
        <table border="1">
            <%
                        for (UserEnt e : myDAO.getAllEmp()) {
            %>
            <tr>
                <td><%=e.getName()%></td>
                <td><%=e.getLogin()%></td>
            </tr>
            <%
                        }
            %>
        </table>
</body>
</html>