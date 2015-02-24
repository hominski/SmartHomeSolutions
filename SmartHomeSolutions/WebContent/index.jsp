    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page 
import="com.dao.*" import="com.entities.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
      
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
                
            <div class="clr"></div>
            </div>
			
		
            <header>
           
                <H1>Smart Home Solutions</H1>
                <h2>Ideas for your home</h2>

            </header>
            
           
            
        </div>
        
"TEST"

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