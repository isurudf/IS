/*
 * A class that allows to access database with school information
 */
package org.fiontar.registration.dao;

import org.fiontar.api.Database.DatabaseConnectionHandler;
import org.fiontar.api.Mail.sendMail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.fiontar.admin.algo.Assign;
import org.fiontar.registration.Undergrad;

/**
 * @author Fiontar
 */
public class UndergradDA {

    public static ArrayList<Undergrad> list;
    public static ArrayList<Undergrad> getUndergradList(){
        if(list==null)
            Assign.initialise();
        return list;
    }
    
    public static void addUndergrad(Undergrad undergrad) throws SQLException {

        Connection con = DatabaseConnectionHandler.createConnection();
        String queryCheck = "INSERT INTO undergrad ("
                + "indexNum, email,name,password,addr,phone,resume,verification,verified,companies,field,passwordReset,emailLink,gpa"
                + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(queryCheck);
        ps.setString(1, undergrad.getIndex());
        ps.setString(2, undergrad.getEmail());
        ps.setString(3, undergrad.getName());
        ps.setString(4, undergrad.getPassword());
        ps.setString(5, undergrad.getAddr());
        ps.setString(6, undergrad.getPhone());
        ps.setString(7, undergrad.getResume());
        ps.setString(8, undergrad.getVerification());
        ps.setString(9, undergrad.getVerified() + "");
        ps.setString(10, undergrad.getCompanies());
        ps.setString(11, undergrad.getField());
        ps.setString(12, " ");
        ps.setString(13, undergrad.getEmailLink());
        ps.setFloat(14, undergrad.getGpa());
        ps.executeUpdate();
        con.close();
        sendVerification(undergrad);
    }

    /**
     * method sends an email to verify the email using student's data in the
     * database
     *
     * @param undergrad school
     */
    public static void sendVerification(Undergrad undergrad) {
        sendMail.sendmail(undergrad.getEmail(), "Email Verification for RUR 2013",
                "Thank you for registering for Are You Ready? 2013.\n\n"
                + "If you did not register, please ignore this email.\n\n"
                + "Please click the following to verify your email address \n\n\n"
                + "http://www.areyouready.uom.lk/EmailConfirmation?id="
                + undergrad.getEmailLink()
                + "\nRtr. Sumudu Herath,\n\n"
                + "Project Co-Chairperson,\n"
                + "Are you Ready? 2013,\n"
                + "Official Careers Fair,\n"
                + "University of Moratuwa\n");

    }
        
    private static Undergrad getUndergradFromRS(ResultSet rs) throws SQLException{
        return new Undergrad(
                    rs.getString("indexNum"),
                    rs.getString("email"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("addr"),
                    rs.getString("phone"),
                    rs.getString("resume"),
                    rs.getInt("id"),
                    rs.getString("verification"),
                    rs.getInt("verified"),
                    rs.getString("companies"),
                    rs.getString("field"),
                    rs.getString("passwordReset"),
                    rs.getString("emailLink"),
        			rs.getFloat("gpa"));
    }
    public static Undergrad getUndergrad(String indexNum) {
        Undergrad s = null;
        try {
            Connection con = DatabaseConnectionHandler.createConnection();
            String queryCheck = "SELECT * from undergrad WHERE indexNum = ?";
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, indexNum);
            ResultSet rs = ps.executeQuery();
            rs.next();
            s = getUndergradFromRS(rs);
            con.close();
            // SQL query to get school with the email

            System.out.println("Undergrad gotten from DB");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    public static Undergrad getUndergrad(String link, String index) {
        Undergrad s = null;
        try {
            Connection con = DatabaseConnectionHandler.createConnection();
            String queryCheck = "SELECT * from undergrad WHERE verification = ? AND indexNum = ? ";
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, link);
            ps.setString(2, index);
            ResultSet rs = ps.executeQuery();
            rs.next();
            s = getUndergradFromRS(rs);
            con.close();
            // SQL query to get school with the email

            System.out.println("Undergrad gotten from DB");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    /**
     * method gives a list of all schools
     *
     * @return list of schools
     */
    public static ArrayList<Undergrad> getAllUnderGrads() {
        ArrayList<Undergrad> underGradList = new ArrayList<Undergrad>();
        try {
            DatabaseConnectionHandler.closeConnection();
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "SELECT * FROM undergrad ORDER BY id";

            PreparedStatement ps = con.prepareStatement(queryCheck);

            ResultSet rs = ps.executeQuery();
            Undergrad s;

            while (rs.next()) {
                s = getUndergradFromRS(rs);
                underGradList.add(s);

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return underGradList;
    }
    public static HashMap<Integer, Undergrad> getAllUnderGradMap() {
    	HashMap<Integer, Undergrad> underGradList = new HashMap<Integer, Undergrad>();
        try {
            DatabaseConnectionHandler.closeConnection();
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "SELECT * FROM undergrad ORDER BY id";

            PreparedStatement ps = con.prepareStatement(queryCheck);

            ResultSet rs = ps.executeQuery();
            Undergrad s;

            while (rs.next()) {
                s = getUndergradFromRS(rs);
                underGradList.put(s.getId(), s);

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return underGradList;
    }

    public static ArrayList<Undergrad> getAllUnderGradsPreferred(String company) {
        ArrayList<Undergrad> underGradList = new ArrayList<Undergrad>();
        try {
            DatabaseConnectionHandler.closeConnection();
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "SELECT * FROM undergrad where companies LIKE ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, "%"+company+"%");
            ResultSet rs = ps.executeQuery();
            Undergrad s;

            while (rs.next()) {
                s = getUndergradFromRS(rs);
                underGradList.add(s);

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return underGradList;
    }

    public static ArrayList<Undergrad> getAllUnderGradsByField(String field) {
        ArrayList<Undergrad> underGradList = new ArrayList<Undergrad>();
        try {
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "SELECT * FROM undergrad where field = ? AND verified = ? ORDER BY indexNum";
            
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, field);
            ps.setString(2, "1");
            ResultSet rs = ps.executeQuery();
            Undergrad s;

            while (rs.next()) {
                s = getUndergradFromRS(rs);
                underGradList.add(s);

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return underGradList;
    }
     public static ArrayList<Undergrad> getAllUnderGradsByCompany(String company) {
        ArrayList<Undergrad> underGradList = new ArrayList<Undergrad>();
        try {
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "SELECT * FROM undergrad where assigned0 = ? or assigned0 = ? or assigned0 = ? "
                    + "ORDER BY indexNum";
            
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, company);
            ps.setString(2, company);
            ps.setString(3, company);
            ResultSet rs = ps.executeQuery();
            Undergrad s;

            while (rs.next()) {
                s = getUndergradFromRS(rs);
                underGradList.add(s);

            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return underGradList;
    }

    public static void updateResume(Undergrad u) {
        try {

            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "UPDATE undergrad "
                    + "SET resume = ? "
                    + "WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, u.getResume());
            ps.setInt(2, u.getId());

            ps.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void resetPassword(String indexNum) {
        try {

            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "UPDATE undergrad "
                    + "SET password = ? "
                    + "WHERE indexNum = ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, "202cb962ac5975b964b7152d234b70");
            ps.setString(2, indexNum);

            ps.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void removePhone(String indexNum) {
        try {

            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "UPDATE undergrad "
                    + "SET phone = ? "
                    + "WHERE indexNum = ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, "0");
            ps.setString(2, indexNum);

            ps.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean changePwdLink(String email, String link) {
        try {

            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "SELECT * from undergrad "
                    + "WHERE email = ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("password") == null || rs.getString("password").length() < 5) {
                    return false;
                }
                queryCheck = "UPDATE undergrad "
                        + "SET passwordReset = ? "
                        + "WHERE email = ? ";

                ps = con.prepareStatement(queryCheck);
                ps.setString(1, link);
                ps.setString(2, email);
                ps.executeUpdate();
                return true;
            }
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static void updatePassword(int id, String newPassword) {
        try {

            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "UPDATE undergrad "
                    + "SET password = ? "
                    + "WHERE id = ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setInt(2, id);
            ps.setString(1, newPassword);

            ps.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
	
    public static void updatePassword(String pwdResetLink, String newPassword) {
        try {

            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "UPDATE undergrad "
                    + "SET password = ? , passwordReset = ?, verified = ? "
                    + "WHERE passwordReset = ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, newPassword);
            ps.setString(2, "");
            ps.setInt(3, 1);
            ps.setString(4, pwdResetLink);

            ps.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateCompanies(String verification, String index, String companies) {
        try {
            System.out.println("Updating companies of " + index + " to " + companies);
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "UPDATE undergrad "
                    + "SET companies = ? "
                    + "WHERE verification = ? AND indexNum = ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, companies);
            ps.setString(2, verification);
            ps.setString(3, index);

            ps.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void updateNameAddr(String verification, String index, String name, String addr) throws SQLException {
    
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "UPDATE undergrad "
                    + "SET name = ? , addr = ?"
                    + "WHERE verification = ? AND indexNum = ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, name);
            ps.setString(2, addr);
            ps.setString(3, verification);
            ps.setString(4, index);

            ps.executeUpdate();
            con.close();

    }


    public static void delete(int id) {
        try {
            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "UPDATE undergrad "
                    + "SET verified = ? , password = ? "
                    + "WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setInt(1, 0);
            ps.setString(2, " ");
            ps.setInt(3, id);

            ps.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
