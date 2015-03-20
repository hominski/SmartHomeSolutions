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

public class NewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>All Emp:</h1>");
            DAO myDAO = (DAO) this.getServletContext().getAttribute("myDAO");
            for (UserEnt e : myDAO.getAllEmp()) {
                out.println(e.getName() + " " + e.getLogin() + e.getMail()+e.getPhone()+"<br>");
            }
            out.println("</body>");
            out.println("</html>");
            
        } finally { 
            out.close();
        }
    } 
}
