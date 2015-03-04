<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="css/login.css" media="screen" type="text/css" />

<!--[if lt IE 7 ]> <html class="ie ie6 no-js" lang="en"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie ie7 no-js" lang="en"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie ie8 no-js" lang="en"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie ie9 no-js" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--><html class="no-js" lang="en"><!--<![endif]-->
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
    <body id="page">
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
                    
                 <a href="index.html" >
                        <strong>Main Page</strong>
                    </a>
                </span>
					<span class="left">
                    
                    <a href="login.html"> <strong>About</strong> </a>
                    <a href="signup.html"> <strong>Contact Us</strong> </a>
					
                </span>
				
                <div class="clr"></div>
            </div>
			
		
            <header>
                <H1>Smart Home Solutions</H1>
                <h2>Ideas for your home.</h2>

				 <div id="login-form">
      <h1>LOG IN</h1>
        <fieldset>
            <form action="signin" method="get">
                <input type="text" required value="login" onBlur="if(this.value=='')this.value='login'" onFocus="if(this.value=='login')this.value='' "> 
                <input type="password" required value="password" onBlur="if(this.value=='')this.value='password'" onFocus="if(this.value=='password')this.value='' "> 
                <input type="submit" value="Submit">
            </form>
			<br>
			Do not have account? <a href="signup.html"style="color: #A9A9A9">SIGN UP</a>
	        </fieldset>	 
    </div>
				
            </header>
        </div>
		
		
    </body>
</html>