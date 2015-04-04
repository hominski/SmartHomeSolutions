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
            ResultSet rs = stmt.executeQuery("SELECT login,name,email, telephone FROM USERS");
            while (rs.next()) {
                allEmp.add(new UserEnt(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)));
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
    	 Locale.setDefault(Locale.ENGLISH);
    	Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        int result = -1;
        try {
            connection.setAutoCommit(false);
          
            preparedStatement = connection.prepareStatement("INSERT INTO USERS (EMAIl,PASSWORD,LOGIN,NAME,TELEPHONE)" +
                    "VALUES (?,?,?,?,?)");
          
            preparedStatement.setString(1, us.getMail());
            preparedStatement.setString(2, us.getPassword());
            preparedStatement.setString(3, us.getLogin());
            preparedStatement.setString(4, us.getName());
            preparedStatement.setString(5, us.getPhone());
          
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
     * @return true if not exists, false if exists
     */
    public boolean checkForEmailUniq(String email) throws SQLException {
    	 Locale.setDefault(Locale.ENGLISH);
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
    
    /*---------------------------------------------------------------------*/
    
    /**
     * Checks if user with specified login exists in database.
     *
     * @param login to check
     * @return true if user not exists, false if exists
     */
    public boolean checkForLoginUniq(String login) throws SQLException {
   	 Locale.setDefault(Locale.ENGLISH);
   	Connection connection = getConnection();
       PreparedStatement preparedStatement = null;
       boolean result = false;
       try {
           preparedStatement = connection.prepareStatement("SELECT COUNT(Id_User) RES FROM USERS WHERE LOGIN = ?");
           preparedStatement.setString(1, login);
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


    /*---------------------------------------------------------------------*/

/**
 * Get user by its login and password
 *
 * @param login    user login
 * @param password user password
 * @return found user or null if user does not exist
 * @throws java.sql.SQLException
 */
public UserEnt getUserByLoginAndPassword(String login, String password) throws SQLException {
    Connection connection = getConnection();
    UserEnt user = null;
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT *  " +
                        "FROM USERS  " +
                        "WHERE LOGIN = ? AND PASSWORD = ? ");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int idUser = resultSet.getInt("ID_USER");
            String mail = resultSet.getString("EMAIL");
            String name = resultSet.getString("NAME");
            String phone = resultSet.getString("TELEPHONE");
            user = new UserEnt(idUser, login, password, phone, name, mail);
        }
        resultSet.close();
    } finally {
        try {
            close(connection, preparedStatement);
        } catch (SQLException exc) {
          //  logger.warn("Can't close connection or preparedStatement!");
            exc.printStackTrace();
        }
    }
    return user;
  }


/*---------------------------------------------------------------------------*/
/**
 * Get user's rooms by his id
 *
 * @param userId
 * @param password user password
 * @return found user's rooms or null 
 * @throws java.sql.SQLException
 */
public List<RoomEnt> getRoomsByUserId(int userId) throws SQLException {
    ArrayList<RoomEnt> result = new ArrayList<RoomEnt>();
    Connection connection = getConnection();
    Locale.setDefault(Locale.ENGLISH);
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT id_room, type, name_r FROM ROOM WHERE ID_USER = ?");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(new RoomEnt(resultSet.getInt("ID_ROOM"),
                    userId,
                    resultSet.getString("NAME_R"),
                    resultSet.getString("TYPE")));
                    }
    } finally {
        try {
            close(connection, preparedStatement);
        } catch (SQLException exc) {
           // logger.warn("Can't close connection or preparedStatement!");
            exc.printStackTrace();
        }
    }
    
    return result;
}
/*----------------------------------------------------------------------------------------*/

public RoomEnt getRoomById(int roomId) throws SQLException {
    RoomEnt result = null;
    Connection connection = getConnection();
    Locale.setDefault(Locale.ENGLISH);
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT id_user, type, name_r FROM ROOM WHERE ID_ROOM = ?");
        preparedStatement.setInt(1, roomId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            result = new RoomEnt(roomId,
            		resultSet.getInt("ID_USER"),
                    resultSet.getString("NAME_R"),
                    resultSet.getString("TYPE"));}
        resultSet.close();
               
    } finally {
        try {
            close(connection, preparedStatement);
        } catch (SQLException exc) {
           // logger.warn("Can't close connection or preparedStatement!");
            exc.printStackTrace();
        }
    }
    
    return result;
}

/*----------------------------------------------------------------------------------------*/
public int addRoom(RoomEnt room) throws SQLException {
    	 Locale.setDefault(Locale.ENGLISH);
    	Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        int result = -1;
        try {
            connection.setAutoCommit(false);
          
            preparedStatement = connection.prepareStatement("INSERT INTO ROOM (ID_USER,TYPE, NAME_R)" +
                    "VALUES (?,?,?)");
          
            preparedStatement.setInt(1, room.getUserId());
            preparedStatement.setString(2, room.getRoomType());
            preparedStatement.setString(3, room.getRoomName());
           
          
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT MAX(ID_ROOM) MAX_ID FROM ROOM");
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

/*----------------------------------------------------------------------------------------*/
public void editRoom(RoomEnt room) throws SQLException {
    Connection connection = getConnection();
    PreparedStatement preparedStatement = null;
    try {
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement("UPDATE ROOM SET TYPE = ? AND SET NAME_R = ?");
        preparedStatement.setString(1, room.getRoomType());
        preparedStatement.setString(2, room.getRoomName());
        preparedStatement.executeUpdate();
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
 
}


/*----------------------------------------------------------------------------------------*/
/** Delete the room by id **/

public void deleteRoom(int rid) throws SQLException {
    	 Locale.setDefault(Locale.ENGLISH);
    	Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
          
            preparedStatement = connection.prepareStatement("DELETE FROM ROOM WHERE ID_ROOM = ?");
            preparedStatement.setInt(1, rid);

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
     
    }

/*-----------------------------------------------------------------------------------------------*/

/**
 * Get all possible room types
 */
public List<RoomType> getAllRoomTypes() throws SQLException {
    ArrayList<RoomType> result = new ArrayList<RoomType>();
    Connection connection = getConnection();
    Locale.setDefault(Locale.ENGLISH);
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT id_type, type FROM TYPE_ROOM");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(new RoomType(resultSet.getInt("ID_TYPE"),
            		resultSet.getString("TYPE")));
                    }
    } finally {
        try {
            close(connection, preparedStatement);
        } catch (SQLException exc) {
           // logger.warn("Can't close connection or preparedStatement!");
            exc.printStackTrace();
        }
    }
    
    return result;
}

/*----------------------------------------------------------------------------------*/

}
