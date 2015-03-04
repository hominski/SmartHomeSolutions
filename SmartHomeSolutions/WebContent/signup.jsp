<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form method="post" action="registration">
                       <%if(request.getAttribute("login_error") != null && !((String) request.getAttribute("login_error")).isEmpty()){%>
                        <div class="alert alert-danger" role="alert">${requestScope.login_error}</div>
                        <%}%>
                        <div class="form-group">
                            <input type="text" name="login" id="login" value="${requestScope.login}" tabindex="4">
                        </div>

                        <%if(request.getAttribute("email_error") != null && !((String) request.getAttribute("email_error")).isEmpty()){%>
                        <div class="alert alert-danger" role="alert">${requestScope.email_error}</div>
                        <%}%>
                        <div class="form-group">
                            <input type="email" name="email" id="email" value="${requestScope.email}" tabindex="4">
                        </div>

                        <%if(request.getAttribute("name_error") != null && !((String) request.getAttribute("name_error")).isEmpty()){%>
                        <div class="alert alert-danger" role="alert">${requestScope.name_error}</div>
                        <%}%>
                        <div class="form-group">
                            <input type="text" name="name" id="name"  value="${requestScope.name}" tabindex="4">
                        </div>
                        
                        <%if(request.getAttribute("phone_error") != null && !((String) request.getAttribute("phone_error")).isEmpty()){%>
                        <div class="alert alert-danger" role="alert">${requestScope.phone_error}</div>
                        <%}%>
                        <div class="form-group">
                            <input type="text" name="phone" id="phone"  value="${requestScope.phone}" tabindex="4">
                        </div>

                        <%if(request.getAttribute("password_error") != null && !((String) request.getAttribute("password_error")).isEmpty()){%>
                        <div class="alert alert-danger" role="alert">${requestScope.password_error}</div>
                        <%}%>
                        <div class="form-group">
                            <input type="password" name="password" id="password"  tabindex="4">
                        </div>

                        <div class="form-group">
                            <input type="password" name="repeat_password" id="repeat_password" tabindex="4">
                        </div>
                        
                        <input  type="hidden" name="check" value="">
                        <div>
                            <input type="submit"  value="Sign Up">
                        </div>
                        </form>
</body>
</html>