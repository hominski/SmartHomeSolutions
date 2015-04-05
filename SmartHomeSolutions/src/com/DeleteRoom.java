package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import com.dao.*;
import com.entities.RoomEnt;
import com.entities.UserEnt;

public class DeleteRoom extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final UserEnt user = (UserEnt)request.getSession().getAttribute("user");

        if(user == null){
            response.sendRedirect("index");
        }
    
   String id = request.getParameter("id");
   int foo = Integer.parseInt(id);
 System.out.println(id);
 System.out.println(foo);
    try{
    	 
    	DAO.INSTANCE.deleteRoom(foo);
    	request.getRequestDispatcher("myrooms.jsp").forward(request, response);
    	}
    	  catch (SQLException e) {
              //Insertion attribute of system error into session and redirect to login page
              request.getSession().setAttribute("system_error","System error!");
              request.getRequestDispatcher("myrooms.jsp").forward(request, response);
          	
          }
}
}
