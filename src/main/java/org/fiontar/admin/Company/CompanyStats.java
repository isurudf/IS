package org.fiontar.admin.Company;

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
public class CompanyStats {

    public static String[][] getStats() {
        
        String[] fields = {"ARCH", "CPE", "CE", "CSE", "ERM", "EE", "ENTC", "FM", "IT", "FD", "ME", "MECH", "QS", "TC", "TCP", "TLM"};
        ArrayList<Company> companyList = CompanyDA.getCompany();
        String[][] arr = new String[companyList.size() + 1][fields.length + 1];
        arr[0][0] = " ";
		System.out.println(fields.length+" "+companyList.size());
        for (int i = 1; i <= fields.length; i++) {
            arr[0][i] = fields[i-1];
        }
        for (int i = 1; i <= companyList.size(); i++) {
            arr[i][0] = companyList.get(i-1).getShortName();
        }
        Connection con = DatabaseConnectionHandler.createConnection();
        ResultSet rs ;
        String queryCheck = "SELECT COUNT(*) FROM undergrad WHERE field = ? AND companies LIKE ? ";
        try {
            PreparedStatement ps = con.prepareStatement(queryCheck);
            for (int i = 1; i <= companyList.size(); i++) {
                for (int j = 1; j <= fields.length; j++) {
                    if(companyList.get(i-1).getFields().contains(","+fields[j-1]+",")){
                        System.out.println(companyList.get(i-1).getShortName()+" "+fields[j-1]);
                        ps.setString(1, fields[j-1]);
                        ps.setString(2, "%"+companyList.get(i-1).getShortName()+"%");
                        rs = ps.executeQuery();
                        
                        rs.next();
                        arr[i][j] = rs.getInt(1)+"";
                    }
                    else{
                        arr[i][j] = "N/A";
                    }
                }
            }
            con.close();
            return arr;
        } catch (SQLException e) {
            System.out.println("Stats SQL Exception "+e.getMessage());
            return null;
        }
    }
}
