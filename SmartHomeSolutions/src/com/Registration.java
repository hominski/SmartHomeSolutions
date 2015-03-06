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


public class Registration extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearError(request);
        String check = request.getParameter("check");
        if(check == null && !check.equals("")){
            response.sendRedirect("http://google.com");
            return;
        }
        if(request.getSession().getAttribute("current_user") != null){
            response.sendRedirect("index");
        }
        
        String login = request.getParameter("login").toLowerCase();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String repeat_password = request.getParameter("repeat_password");

        UserEnt userInSession = (UserEnt)request.getSession().getAttribute("user");
        
        request.setAttribute("login", login);
        request.setAttribute("email",email);
        request.setAttribute("name",name);
        request.setAttribute("phone", phone);
        request.setAttribute("password",password);
        request.setAttribute("repeat_password",repeat_password);
        
        if(login.isEmpty()){
            request.setAttribute("login_error", "Login can`t be empty");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        if(email.isEmpty()){
            request.setAttribute("email_error", "Email can`t be empty");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }


        if(name == null || name.equals("")) {
            request.setAttribute("name_error", "Type your name");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        if(password == null || repeat_password == null ||
                (password.compareTo("") == 0) || (repeat_password.compareTo("") == 0)) {
            request.setAttribute("password_error", "Please, Type the passwords");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        if(password.compareTo(repeat_password) != 0) {
            request.setAttribute("password_error", "Passwords should be equal");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
       	
	
	try {
		if (DAO.INSTANCE.checkForEmailUniq(email)) {	
			UserEnt registeredUser = new UserEnt(0, login, password, phone, name, email);	
			int registeredUserId = DAO.INSTANCE.createUser(registeredUser);
            registeredUser = new UserEnt(registeredUserId, login, password, phone, name, email);
            if (userInSession == null) {
                //Insertion user attribute into session
                request.getSession().setAttribute("user",registeredUser);
                userInSession = registeredUser;
            }
            request.getRequestDispatcher("mysmarthome.jsp").forward(request, response);
		}
		else{
			request.setAttribute("email_error", "Such email is already exist!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
		}
	}catch (SQLException e) {
        //Insertion attribute of system error into session and redirect to registration page
        // or to administrator dashboard
       // logger.error(e);
        request.getSession().setAttribute("system_error", "System error:(");
        if(userInSession != null) request.getRequestDispatcher("index.jsp").forward(request, response);
        else request.getRequestDispatcher("index.jsp").forward(request, response);
    }
	}
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            clearError(request);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }

        private void clearError(HttpServletRequest request) {
            request.setAttribute("login_error", "");
            request.setAttribute("name_error","");
            request.setAttribute("phone_error","");
            request.setAttribute("password_error","");
            request.setAttribute("system_error", "");
        }

	}
