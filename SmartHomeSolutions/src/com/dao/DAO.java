package com.dao;

import com.entities.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;

public class DAO {
	
	
    private DataSource dataSource;

    public DAO() throws ServletException {
        if (dataSource != null) {
            return;
        }
        try {
	Context ic = new InitialContext();
	dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/SMARTHOME");
        } catch (NamingException ex) {
            throw new ServletException(
                    "Cannot retrieve java:comp/env/jdbc/SMARTHOME", ex);
        }


    }
    public Connection getConnection() throws ServletException {
        try {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot obtain connection", ex);
        }
    }

    public void releaseConnection(Connection conn) throws ServletException {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot release connection", ex);
        }
    }
   
    public List<UserEnt> getAllEmp() throws ServletException {
        List<UserEnt> allEmp = new ArrayList<UserEnt>();
        Connection conn = null;
        Locale.setDefault(Locale.ENGLISH);
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT login,name FROM USERS");
            while (rs.next()) {
                allEmp.add(new UserEnt(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return allEmp;
    }
}

