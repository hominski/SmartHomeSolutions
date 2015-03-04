<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>Smart Home Solutions: Log in</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Smart Home Solutions - Ideas for Your Home" />
        <meta name="keywords" content="smart house, smart home" />
        

        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style1.css" />
		<link rel="stylesheet" href="css/login.css" media="screen" type="text/css" />

	
		<!--<script type="text/javascript" src="js/modernizr.custom.86080.js"></script> -->

</head>
<body>

        <ul class="cb-slideshow">
            <li><span>Image 01</span></li>
            <li><span>Image 02</span></li>
            <li><span>Image 03</span></li>
            <li><span>Image 04</span></li>
            <li><span>Image 05</span></li>
            <li><span>Image 06</span></li>
        </ul>
		<ul class="cb-slideshow"></ul>
		
		        <div class="container">
        
            <div class="codrops-top">
                
                <span class="right">
                    
                    <a href="index.html">Main Page</a>
                   
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
 			
<div id="login-form">
<h1>Sign Up</h1>
<fieldset>
<form method="post" action="registration">
<%if(request.getAttribute("login_error") != null && !((String) request.getAttribute("login_error")).isEmpty()){%>
<div class="alert alert-danger" role="alert">${requestScope.login_error}</div>
<%}%>
<h3>Login:</h3>
<div class="form-group">
<input type="text" name="login" id="login" value="${requestScope.login}" tabindex="4">
</div>

<%if(request.getAttribute("email_error") != null && !((String) request.getAttribute("email_error")).isEmpty()){%>
<div class="alert alert-danger" role="alert">${requestScope.email_error}</div>
<%}%>
<h3>E-Mail:</h3>
<div class="form-group">
<input type="email" name="email"  onFocus="if(this.value=='e-mail')this.value=''" id="email" value="${requestScope.email}" tabindex="4">
</div>

<%if(request.getAttribute("name_error") != null && !((String) request.getAttribute("name_error")).isEmpty()){%>
<div class="alert alert-danger" role="alert">${requestScope.name_error}</div>
<%}%>
<h3>Name:</h3>
<div class="form-group">
<input type="text" name="name"  onFocus="if(this.value=='name')this.value=''" id="name" value="${requestScope.name}" tabindex="4">
</div>

<%if(request.getAttribute("phone_error") != null && !((String) request.getAttribute("phone_error")).isEmpty()){%>
<div class="alert alert-danger" role="alert">${requestScope.phone_error}</div>
<%}%>
<h3>Phone Number:</h3>
<div class="form-group">
<input type="text" name="phone"  onFocus="if(this.value=='phone number')this.value=''" id="phone" value="${requestScope.phone}" tabindex="4">
</div>

<%if(request.getAttribute("password_error") != null && !((String) request.getAttribute("password_error")).isEmpty()){%>
<div class="alert alert-danger" role="alert">${requestScope.password_error}</div>
<%}%>
<h3>Enter Password:</h3>
<div class="form-group">
<input type="password" name="password" id="password" tabindex="4">
</div>
<h3>Confirm Password:</h3>
<div class="form-group">
<input type="password" name="repeat_password" id="repeat_password" tabindex="4">
</div>

<input type="hidden" name="check" value="">
<div>
<input type="submit" value="Sign Up">
</div>
</form>
</fieldset>
</div>
 </header>
        </div>
       
</body>
</html>