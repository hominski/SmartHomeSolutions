package com;

import com.dao.*;
import com.entities.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RoomsController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final UserEnt user = (UserEnt)request.getSession().getAttribute("user");

        if(user == null){
            response.sendRedirect("index");
        }
    
    
    try{
    	RoomEnt room = new RoomEnt(0, user.getUserId(),"default","kitchen");  
    	int newRoomid = DAO.INSTANCE.addRoom(room);
    	request.getRequestDispatcher("myrooms.jsp").forward(request, response);
    	}
    	  catch (SQLException e) {
              //Insertion attribute of system error into session and redirect to login page
              request.getSession().setAttribute("system_error","System error!");
              request.getRequestDispatcher("myrooms.jsp").forward(request, response);
          	
          }
}
}