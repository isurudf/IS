package org.fiontar.admin.Company;

import org.fiontar.api.Database.DatabaseConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompanyDA {
    private static ArrayList<Company> Company =null;

    public static ArrayList<Company> getCompany() {
        if(Company==null||Company.isEmpty()){
            processCompany();
        }
        return Company;
    }
    public static ArrayList<Company> getCompany(String field) {
        ArrayList<Company> list = getCompany();
        ArrayList<Company> listnew = new ArrayList<Company>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getFields().contains(","+field+","))
                listnew.add(list.get(i));
        }
        return listnew;
    }
    
    public static void setCompany(ArrayList<Company> Company) {
        CompanyDA.Company = Company;
    }
    public static Company getCompanyByName(String shortName){
        for(int i=0;i<Company.size();i++)
            if(Company.get(i).getShortName().equals(shortName))
                return Company.get(i);
        return null;
    }
    public static void addCompany(String name, String description, String shortName, String logo, String fields) throws SQLException {

            Connection con = DatabaseConnectionHandler.createConnection();

            String queryCheck = "INSERT INTO company (name, description, shortName, logo, fields, capacity) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, shortName);
            ps.setString(4, logo);
            ps.setString(5, fields);
            ps.setInt(6, 13);
            ps.executeUpdate();
            con.close();
    }
    public static void deleteCompany(int id) {
        Connection con;

        try {
            con = DatabaseConnectionHandler.createConnection();
            String queryCheck = "DELETE FROM company WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setInt(1, id);
            ps.execute();
             con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void processCompany() {
        ArrayList<Company> CompanyList = new ArrayList<Company>();
        try {
            Connection con = DatabaseConnectionHandler.createConnection();
            String queryCheck = "SELECT * FROM company ";
            PreparedStatement ps = con.prepareStatement(queryCheck);
            ResultSet rs = ps.executeQuery();
            Company item;
            while (rs.next()) {
                item = new Company( 
                        rs.getString("name"),
                        rs.getString("description"), 
                        rs.getString("shortName"),
                        rs.getString("logo"),
                        rs.getString("fields"),
                        rs.getInt("id"),
                        rs.getInt("capacity"),
                        rs.getString("building"),
                        rs.getString("tel"),
                        rs.getString("buildTel")
                        );
                CompanyList.add(item);
            }
            Comparator<Company> comparator = new Comparator<Company>() {
                @Override
                public int compare(Company n1, Company n2) {
                    return n1.getName().toUpperCase().compareTo(n2.getName().toUpperCase());
                }
            };
            con.close();
            Collections.sort(CompanyList,comparator);
            Company = CompanyList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        CompanyDA.setCompany(CompanyList);
    }
    public static void updateCompany(Company company){
        try {
            Connection con = DatabaseConnectionHandler.createConnection();
            String queryCheck = "UPDATE company "
                    + "SET name = ? , description = ? , capacity = ? , logo = ?, building = ?, tel= ?, buildTel = ? "
                    + "WHERE id = ? ";

            PreparedStatement ps = con.prepareStatement(queryCheck);
            ps.setString(1, company.getName());
            ps.setString(2, company.getDescription());
            ps.setInt(3, company.getCapacity());
            ps.setString(4, company.getLogo());
            ps.setString(5, company.getBuilding());
            ps.setString(6, company.getTel());
            ps.setString(7, company.getBuildTel());
            ps.setInt(8, company.getId());
            ps.executeUpdate();
            
            queryCheck = "UPDATE company SET buildTel = ? WHERE building = ? ";
            ps = con.prepareStatement(queryCheck);
            ps.setString(2, company.getBuilding());
            ps.setString(1, company.getBuildTel());
            ps.executeUpdate();
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
