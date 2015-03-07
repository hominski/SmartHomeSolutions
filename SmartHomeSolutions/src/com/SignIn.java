package com;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


import java.io.IOException;

import com.entities.*;
import com.dao.*;


public class SignIn extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", "");
        String check = request.getParameter("check");
        if(check != null && !check.isEmpty()){
            response.sendRedirect("www.google.com");
            return;}
    
        if(request.getSession().getAttribute("current_user") != null){
            response.sendRedirect("index");
        }
        
        String login = request.getParameter("login").toLowerCase();
        String password = request.getParameter("password");
        
        UserEnt userInSession = (UserEnt)request.getSession().getAttribute("user");
        if(userInSession == null) {        	
        
        request.setAttribute("login", login);
        request.setAttribute("password",password);
        
        if(login.isEmpty()){
            request.setAttribute("login_error", "Login can`t be empty");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        if(password.isEmpty()){
            request.setAttribute("password_error", "Please, enter the password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        UserEnt userForLogin = null;
        
        try{
        	
        	if(DAO.INSTANCE.checkForLoginUniq(login)){
        		request.setAttribute("login_error", "Such login doesn't exist!");
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	            return;
        	}
        	//else{
        	userForLogin = DAO.INSTANCE.getUserByLoginAndPassword(login, password);
            if (userForLogin == null) {
                //Insertion attribute of login error into session and redirect to login page
            	request.setAttribute("password_error", "Wrong password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            else{
            request.getSession().setAttribute("user", userForLogin);	
            request.getRequestDispatcher("mysmarthome.jsp").forward(request, response);
            }
        }
        catch (SQLException e) {
            //Insertion attribute of system error into session and redirect to login page
            //logger.error(e);
            request.getSession().setAttribute("system_error","System error!");
            response.sendRedirect("index.jsp");
        }
    }else{
        //Insertion attribute of permission error into session and redirect to home page
        request.getSession().setAttribute("error", "error");
        response.sendRedirect("index.jsp");
    }   
	
}      

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    clearError(request);
    request.getRequestDispatcher("login.jsp").forward(request, response);
}

private void clearError(HttpServletRequest request) {
    request.setAttribute("login_error", "");
    request.setAttribute("name_error","");
    request.setAttribute("phone_error","");
    request.setAttribute("password_error","");
    request.setAttribute("system_error", "");
}

}