/*
 * A class to get a connection to the database  
 */
package org.fiontar.api.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnectionHandler {
    private static Connection con = null;
    /**
     * method returns a connection to the database if exists
     * @return database connection
     */
    public static Connection createConnection(){
            try {
                Class.forName(Constants.DRIVER_NAME);
                Connection conn = DriverManager.getConnection(Constants.DB_URL,Constants.DB_USERNAME,Constants.DB_PASSWORD);
                return conn;
            } catch (ClassNotFoundException ex) {
                
                System.out.println("CNFE "+ex.getMessage());
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("SQLE "+ex.getMessage());
            }
            return null;
        
    }
    /**
     * method gives the connection to the database if exists else get a connection
     * @return database connection 
     */
    public static Connection getConnection(){
        if(con == null)
            con = createConnection();
        try {
            if(con.isValid(1))
            {
                return con;
            }
        } catch (SQLException ex) {
            
        }
        closeConnection();
        con = createConnection();
        return con;
    }  
    public static void closeConnection(){
        try {
            con.close();
        } catch (Exception ex) {
           
        }
        con=null;
    }
}
