/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fiontar.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.fiontar.api.Database.DatabaseConnectionHandler;

/**
 *
 * @author New
 */
public class SuggestedQs {

    public static ArrayList<String> getQs() {
        Connection con = DatabaseConnectionHandler.createConnection();
        ResultSet rs;
        String queryCheck = "SELECT * FROM questions ";
        ArrayList<String> list = new ArrayList<String>();
        try {
            PreparedStatement ps = con.prepareStatement(queryCheck);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString("indexNum")+rs.getString("message"));
            }
            con.close();
            return list;
        } catch (SQLException e) {
            System.out.println("Stats SQL Exception " + e.getMessage());
        }
        return null;
    }
    public static void addQs(String indexNum, String message) throws SQLException {
            Connection con = DatabaseConnectionHandler.createConnection();
            String queryCheck = "INSERT INTO questions (indexNum, message) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, indexNum);
            ps.setString(2, message);
            ps.executeUpdate();
            con.close();
    }
}