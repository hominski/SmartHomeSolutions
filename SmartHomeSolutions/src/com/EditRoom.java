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

public class EditRoom extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final UserEnt user = (UserEnt)request.getSession().getAttribute("user");

        if(user == null){
            response.sendRedirect("index");
        }
    
   String id = request.getParameter("id");
   String name = request.getParameter("name");
   String type = request.getParameter("type");
   int foo = Integer.parseInt(id);

  try{
    	 
    	RoomEnt newroom = new RoomEnt(foo, user.getUserId(), name, type);
    	DAO.INSTANCE.editRoom(newroom);
    	request.getRequestDispatcher("myrooms.jsp").forward(request, response);
    	}
    	  catch (SQLException e) {
              //Insertion attribute of system error into session and redirect to login page
              request.getSession().setAttribute("system_error","System error!");
              request.getRequestDispatcher("myrooms.jsp").forward(request, response);
          	
          }
}
}
