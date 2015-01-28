<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta charset="UTF-8">
	<title>Smart Home Solutions: Sign up</title>

	<link rel="stylesheet" href="WEB-INF/css/style.css" media="screen" type="text/css" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
</head>

<body>


    <div id="login-form">
      <h1>Sign Up</h1>
        <fieldset>
            <form action="signin" method="get">
                <input type="text" name=login required value="Login" onBlur="if(this.value=='')this.value='login'" onFocus="if(this.value=='login')this.value='' "> 
				<input type="text" required value="First Name" onBlur="if(this.value=='')this.value='login'" onFocus="if(this.value=='login')this.value='' "> 
                <input type="text" required value="Last Name" onBlur="if(this.value=='')this.value='login'" onFocus="if(this.value=='login')this.value='' "> 
                <input type="email" required value="Email" onBlur="if(this.value=='')this.value='login'" onFocus="if(this.value=='login')this.value='' "> 
				Password
				<input type="password" required  value="Password" onBlur="if(this.value=='')this.value='password'" onFocus="if(this.value=='password')this.value='' "> 
                Confirm Password
				<input type="password" required value="Password" onBlur="if(this.value=='')this.value='password'" onFocus="if(this.value=='password')this.value='' "> 
				<input type="submit" value="Submit">
            </form>
			    
        </fieldset>	 
		
    </div>
</body>
</html>