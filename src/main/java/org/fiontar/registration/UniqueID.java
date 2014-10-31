/*
 * A class to generate the email varification link
 */
package org.fiontar.registration;

import org.fiontar.api.Database.DatabaseConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * @author Fiontar
 */

public class UniqueID {
    /**
     * static method generate and gives a unique id
     * @return id number
     */
    public static String generate() {
        UUID uuid = UUID.randomUUID();
        String id = (uuid + "").replace("-", "");
  //      while (searchAndAdd(id)) {
    //        uuid = UUID.randomUUID();
      //      id = (uuid + "").replace("-", "");
       // }
        return id;
    }
    /**
     * method search for a particular id and if it doesn't exists add that id to the database  
     * @param id unique id
     * @return "true" if that particular id exists, if not returns "false"
     */
    private static boolean searchAndAdd(String id) {
        DatabaseConnectionHandler db = new DatabaseConnectionHandler();
        try {
            Connection con = db.createConnection();
            String queryCheck = "SELECT COUNT(*) from id WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, id); 
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                final int count = resultSet.getInt(1);
                if (count > 0) {
                    con.close();
                    return true;
                } else {
                    Statement st = con.createStatement();
                    int rs = st.executeUpdate("INSERT INTO id VALUES ('" + id + "')");
                    con.close();
                    return false;
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return false;
    }
    
    public static boolean searchIndex(String index, String email) throws SQLException {
        System.out.println("Searching for "+index+"  "+email);
        Connection con = DatabaseConnectionHandler.getConnection();
        String queryCheck = "SELECT COUNT(*) from undergrad WHERE indexNum = ? OR email = ?";
        PreparedStatement ps = con.prepareStatement(queryCheck);
        ps.setString(1, index);
        ps.setString(2, email);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            if (count > 0) 
                return true;
    //          System.out.println(count+"");
        }
        con.close();
        return false;
    }
}
