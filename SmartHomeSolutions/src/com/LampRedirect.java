package com;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
public class LampRedirect extends HttpServlet {
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher view = request.getRequestDispatcher("lighting.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException e) {
            System.out.print(e.toString());
 
        } catch (IOException e) {
            System.out.print(e.toString());
        }
    }
}
