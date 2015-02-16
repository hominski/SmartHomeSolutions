package com;

import com.dao.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

public class NewServletListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        try {
            DAO myDAO = new DAO();
            sce.getServletContext().setAttribute("myDAO", myDAO); // Application scope attribute
        } catch (Exception ex) {
            throw new RuntimeException("Can't create connection pool", ex);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("myDAO", null);
    }
}

	

