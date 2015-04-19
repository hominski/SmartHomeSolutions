package com.dao;

import com.entities.*;
import com.*;

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

public List<Mode> getModesByUserId(int userId) throws SQLException {
	ArrayList<Mode> result = new ArrayList<Mode>();
    Connection connection = getConnection();
    Locale.setDefault(Locale.ENGLISH);
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT ID_MODES,NAME_MODE,TIME_BEGIN,TIME_END,ACTIVE,ACTIVE_CAMERA, ACTIVE_LAMP, "+
                				"BRIGHTNESS_LAMP, COLOUR_LAMP, ACTIVE_AC, TEMPERATURE_AC, ACTIVE_MUSIC,VOLUME_MUSIC, "+
                		         "TYPE_MUSIC FROM MODES WHERE ID_USER = ?");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
        	GregorianCalendar beginning = new GregorianCalendar();
            Time dateOfBeg = resultSet.getTime("TIME_BEGIN");
            beginning.set(dateOfBeg.getHours(), dateOfBeg.getMinutes(), dateOfBeg.getSeconds());
            GregorianCalendar end = new GregorianCalendar();
            Time dateOfEnd = resultSet.getTime("TIME_END");
            end.set(dateOfEnd.getHours(), dateOfEnd.getMinutes(), dateOfEnd.getSeconds());
                    result.add(new Mode(resultSet.getInt("ID_MODES"), 1,
            		resultSet.getString("NAME_MODE"),
            		beginning, end,
            		resultSet.getBoolean("ACTIVE"),
                    resultSet.getBoolean("ACTIVE_CAMERA"),
                    resultSet.getBoolean("ACTIVE_LAMP"),
                    resultSet.getInt("BRIGHTNESS_LAMP"),
                    resultSet.getString("COLOUR_LAMP"),
                    resultSet.getBoolean("ACTIVE_AC"),
                    resultSet.getInt("TEMPERATURE_AC"),
                    resultSet.getBoolean("ACTIVE_MUSIC"),
                    resultSet.getInt("VOLUME_MUSIC"),
                    resultSet.getString("TYPE_MUSIC")));}
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


public Mode getModeById(int modeId) throws SQLException {
    Mode result = null;
    Connection connection = getConnection();
    Locale.setDefault(Locale.ENGLISH);
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT ID_USER,NAME_MODE,TIME_BEGIN,TIME_END,ACTIVE,ACTIVE_CAMERA"+
                                 "ACTIVE_LAMP, BRIGHTNESS_LAMP, COLOUR_LAMP, ACTIVE_AC"+
                		         "TEMPERATURE_AC, ACTIVE_MUSIC,VOLUME_MUSIC, TYPE_MUSIC"+
                                 "FROM MODES WHERE ID_USER = ?");
        preparedStatement.setInt(1, modeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
        	GregorianCalendar beginning = new GregorianCalendar();
            Date dateOfBeg = resultSet.getDate("TIME_BEGIN");
            beginning.set(dateOfBeg.getYear(), dateOfBeg.getMonth(), dateOfBeg.getDay());
            GregorianCalendar end = new GregorianCalendar();
            Date dateOfEnd = resultSet.getDate("TIME_END");
            end.set(dateOfEnd.getYear(), dateOfEnd.getMonth(), dateOfEnd.getDay());
            result = new Mode(1,resultSet.getInt("ID_USER"),
            		resultSet.getString("COLOUR_LAMP"),
            		beginning, end,
            		resultSet.getBoolean("ACTIVE"),
                    resultSet.getBoolean("ACTIVE_CAMERA"),
                    resultSet.getBoolean("ACTIVE_LAMP"),
                    resultSet.getInt("BRIGHTNESS_LAMP"),
                    resultSet.getString("COLOUR_LAMP"),
                    resultSet.getBoolean("ACTIVE_AC"),
                    resultSet.getInt("TEMPERATURE_AC"),
                    resultSet.getBoolean("ACTIVE_MUSIC"),
                    resultSet.getInt("VOLUME_MUSIC"),
                    resultSet.getString("TYPE_MUSIC"));}
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

public LampEnt getLampById(int lampId) throws SQLException {
    LampEnt result = null;
    Connection connection = getConnection();
    Locale.setDefault(Locale.ENGLISH);
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT ACTIVEL, BRIGHTNESS, COLOUR, NAME_LAMP,ID_ROOM,ID_TYPE, IP_LAMP FROM LAMP WHERE ID_LAMP = ?");
        preparedStatement.setInt(1, lampId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            result = new LampEnt(lampId,
            		resultSet.getBoolean("Activel"),
            		resultSet.getInt("Brightness"),
                    resultSet.getString("Colour"),
                    resultSet.getString("Name_Lamp"),
                    resultSet.getString("Ip_Lamp"),
                    resultSet.getInt("Id_Room"),
                    resultSet.getInt("Id_Type"));}
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

public AirConditionEnt getCondById(int condId) throws SQLException {
	AirConditionEnt result = null;
    Connection connection = getConnection();
    Locale.setDefault(Locale.ENGLISH);
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT  Activeac, Temperature, Power, Name_AC,Id_Room,Id_Type, Ip_AC FROM AIRCONDITION WHERE ID_AC = ?");
        preparedStatement.setInt(1, condId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            result = new AirConditionEnt(condId,
               		resultSet.getBoolean("Activeac"),
               		resultSet.getInt("Temperature"),
                       resultSet.getInt("Power"),
                       resultSet.getString("Name_AC"),
                       resultSet.getString("Ip_AC"),
                       resultSet.getInt("Id_Room"),
                       resultSet.getInt("Id_Type"));}
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
        preparedStatement = connection.prepareStatement("UPDATE ROOM SET TYPE = ?, NAME_R = ? WHERE ID_ROOM = ?");
        preparedStatement.setString(1, room.getRoomType());
        preparedStatement.setString(2, room.getRoomName());
        preparedStatement.setInt(3, room.getroomid());
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

/*-----------------------------------------------------------------------------------------------*/

/** Delete the lamp by id **/

public void deleteLamp(int lid) throws SQLException {
    	 Locale.setDefault(Locale.ENGLISH);
    	Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
          
            preparedStatement = connection.prepareStatement("DELETE FROM LAMP WHERE ID_LAMP = ?");
            preparedStatement.setInt(1, lid);
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

/*-----------------------------------------------------------------------------------------------*/

public void editLamp(String name, String brightness, String colour, int lampid) throws SQLException {
    Connection connection = getConnection();
    PreparedStatement preparedStatement = null;
    try {
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement("UPDATE LAMP SET NAME_LAMP = ?, BRIGHTNESS = ?, COLOUR =? WHERE ID_LAMP = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, brightness);
        preparedStatement.setString(3, colour);
        preparedStatement.setInt(4, lampid);
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
/**
 Create Air Condition
 */
public int addAirCondition(AirConditionEnt aircond) throws SQLException {
	Locale.setDefault(Locale.ENGLISH);
	Connection connection = getConnection();
   PreparedStatement preparedStatement = null;
   int result = -1;
   try {
       connection.setAutoCommit(false);
     
       preparedStatement = connection.prepareStatement("INSERT INTO AIRCONITION(ACTIVEAC,TEMPERATURE,POWER,NAME_AC,ID_ROOM,ID_TYPE)" +
               "VALUES (?,?,?,?,?,?)");
     
       preparedStatement.setBoolean(1, aircond.getIsActiveCond());
       preparedStatement.setInt(2, aircond.getCondTemp());
       preparedStatement.setInt(3, aircond.getCondPower());
       preparedStatement.setString(4, aircond.getNameCond());
       preparedStatement.setInt(5, aircond.getRoomId());
       preparedStatement.setInt(6, aircond.getTypeId());
       
       preparedStatement.executeUpdate();

       preparedStatement = connection.prepareStatement("SELECT MAX(ID_AC) MAX_ID FROM AIRCONDITION");
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
/*----------------------------------------------------------------------------------*/
/**
 Create Lamp
 */
public int addLamp(LampEnt lamp) throws SQLException {
	Locale.setDefault(Locale.ENGLISH);
	Connection connection = getConnection();
   PreparedStatement preparedStatement = null;
   int result = -1;
   try {
       connection.setAutoCommit(false);
     
       preparedStatement = connection.prepareStatement("INSERT INTO LAMP(ACTIVEL,BRIGHTNESS,COLOUR,NAME_LAMP,ID_ROOM,ID_TYPE)" +
               "VALUES (?,?,?,?,?,?)");
     
       preparedStatement.setBoolean(1, lamp.getIsActiveLamp());
       preparedStatement.setInt(2, lamp.getBrightness());
       preparedStatement.setString(3, lamp.getColour());
       preparedStatement.setString(4, lamp.getLampName());
       preparedStatement.setInt(5, lamp.getRoomId());
       preparedStatement.setInt(6, lamp.getTypeId());
       
       preparedStatement.executeUpdate();

       preparedStatement = connection.prepareStatement("SELECT MAX(ID_LAMP) MAX_ID FROM LAMP");
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
 Get all lamp devices by user id 
  */
public List<LampEnt> getLampsByUserId(int userId) throws SQLException {
    ArrayList<LampEnt> result = new ArrayList<LampEnt>();
    Connection connection = getConnection();
    Locale.setDefault(Locale.ENGLISH);
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.
                prepareStatement("SELECT L.ID_LAMP, L.ACTIVEL, L.BRIGHTNESS, L.COLOUR, L.NAME_LAMP,L.ID_ROOM,L.ID_TYPE, L.IP_LAMP " +
                				"From (Users U INNER JOIN ROOM R ON U.ID_USER = R.ID_USER) " +
                				"INNER JOIN LAMP L ON L.ID_ROOM = R.ID_ROOM " +
                				"WHERE U.ID_USER = ?");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(new LampEnt(resultSet.getInt("Id_Lamp"),
            		resultSet.getBoolean("Activel"),
            		resultSet.getInt("Brightness"),
                    resultSet.getString("Colour"),
                    resultSet.getString("Name_Lamp"),
                    resultSet.getString("Ip_Lamp"),
                    resultSet.getInt("Id_Room"),
                    resultSet.getInt("Id_Type")
                    ));
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
/**
Get all condition devices by user id 
 */
public List<AirConditionEnt> getCondByUserId(int userId) throws SQLException {
   ArrayList<AirConditionEnt> result = new ArrayList<AirConditionEnt>();
   Connection connection = getConnection();
   Locale.setDefault(Locale.ENGLISH);
   PreparedStatement preparedStatement = null;
   try {
       preparedStatement = connection.
               prepareStatement("SELECT C.Id_AC, C.Activeac, C.Temperature, C.Power, C.Name_AC,C.Id_Room,C.Id_Type, C.Ip_AC " +
               				"From (Users U INNER JOIN ROOM R ON U.Id_User = R.Id_User) " +
               				"INNER JOIN AirCondition C ON C.Id_Room = R.Id_Room " +
               				"WHERE U.Id_User = ?");
       preparedStatement.setInt(1, userId);
       ResultSet resultSet = preparedStatement.executeQuery();
       while (resultSet.next()) {
           result.add(new AirConditionEnt(resultSet.getInt("Id_AC"),
           		resultSet.getBoolean("Activeac"),
           		resultSet.getInt("Temperature"),
                   resultSet.getInt("Power"),
                   resultSet.getString("Name_AC"),
                   resultSet.getString("Ip_AC"),
                   resultSet.getInt("Id_Room"),
                   resultSet.getInt("Id_Type")
                   ));
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

}
