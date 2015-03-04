package com.dao;

import com.entities.*;

import java.util.Date;
import java.util.*;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;

public class DAO {
	
	public static final DAO INSTANCE = new DAO();
    private DataSource dataSource;

    public DAO() {
        if (dataSource != null) {
            return;
        }
        try {
	Context ic = new InitialContext();
	dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/SMARTHOME");
        } catch (NamingException ex) {
           
        }


    }
    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void releaseConnection(Connection conn) throws ServletException {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot release connection", ex);
        }
    }
    
    private void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        connection.setAutoCommit(true);
        if (!preparedStatement.isClosed()) preparedStatement.close();
        if (!connection.isClosed()) connection.close();
    }
    
    /* ------------------------------------------------------------------------------ */
   
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
    
    /* -------------------------------------------------------------------------------------- */
    
    /**
     * @param us       User to create
     * @param password User password
     * @return -1 if error occured, otherwise id of created user
     */
    @SuppressWarnings("resource")
	public int createUser(UserEnt us) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        int result = -1;
        try {
            connection.setAutoCommit(false);
          
            preparedStatement = connection.prepareStatement("INSERT INTO USERS (ID_USER,EMAIl,PASSWORD,LOGIN,NAME,TELEPHONE)" +
                    "VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1, us.getUserId());
            preparedStatement.setString(2, us.getMail());
            preparedStatement.setString(3, us.getPassword());
            preparedStatement.setString(4, us.getLogin());
            preparedStatement.setString(5, us.getName());
            preparedStatement.setString(6, us.getPhone());
          
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT MAX(ID_USER) MAX_ID FROM USERS");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("MAX_ID");
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
               // logger.error("Transaction is being rolled back");
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                 //   logger.error("ROLLBACK transaction Failed of creating new user");
                }
            }
            e.printStackTrace();
            throw e;
        } finally {
            try {

                close(connection, preparedStatement);

            } catch (SQLException e) {
               // logger.warn("Smth wrong with closing connection or preparedStatement!");
                e.printStackTrace();
            }

        }
        return result;
    }


    /**
     * Checks if user with specified email exists in database.
     *
     * @param email email to check
     * @return true if exists, false if doesn't
     */
    public boolean checkForEmailUniq(String email) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(Id_User) RES FROM USERS WHERE EMAIL = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            int checkResult = -1;
            if (resultSet.next()) {
                checkResult = resultSet.getInt("RES");
            }
            if (checkResult == 0) {
                result = true;
            } else {
                result = false;
            }

        } finally {
            try {
                close(connection, preparedStatement);
            } catch (SQLException e) {
                //logger.warn("Smth wrong with closing connection or preparedStatement!");
                e.printStackTrace();
            }
        }

        return result;
    }
}

