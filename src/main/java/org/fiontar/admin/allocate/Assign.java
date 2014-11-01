package org.fiontar.admin.allocate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import org.fiontar.admin.Company.Company;
import org.fiontar.admin.Company.CompanyDA;

import org.fiontar.api.Database.DatabaseConnectionHandler;

import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class Assign {

    public static UG[] arrUG;
    public static CMP[] arrCMP;

    public static HashMap<Integer, UG> mapUndergrad = new HashMap<Integer, UG>();
    public static HashMap<String, CMP> mapCmp = new HashMap<String, CMP>();

    public static void initialise() {
        if (arrUG == null) {
            assign();
        }
    }

    public static void assign() {
        arrUG = null;
        arrCMP = null;

        ArrayList<Undergrad> listUndergrad = UndergradDA.getAllUnderGrads();
        ArrayList<Company> listCompany = CompanyDA.getCompany();
        UndergradDA.list = listUndergrad;

        arrCMP = new CMP[listCompany.size()];
        for (int i = 0; i < listCompany.size(); i++) {
            arrCMP[i] = new CMP(listCompany.get(i));
            mapCmp.put(arrCMP[i].name, arrCMP[i]);
        }

        arrUG = new UG[listUndergrad.size()];
        for (int i = 0; i < listUndergrad.size(); i++) {
            arrUG[i] = new UG(listUndergrad.get(i));
            mapUndergrad.put(arrUG[i].u.getId(), arrUG[i]);
        }

        for (UG ug : arrUG) {
            ug.populateFields();    //Make an arraylist of CMPs inside UG according to preference of undergraduate 
        }
        for (CMP cmp : arrCMP) {
            cmp.populateFields();   //Make an arraylist of UGs inside CMP according to preference of company
        }
        sortCMP();  //Sort companies by their ratings
        sortUG();   //Sort undergraduates by a field (GPA at the moment) 

        for (UG ug : arrUG) {
            ug.completeList();  //Complete the preference list of UG by assigning the not-assigned-companies in sorted order
        }
        for (CMP cmp : arrCMP) {
            cmp.completeList(); //Complete the preference list of CMP by assigning the not-assigned-undergraduates in sorted order
        }
        while (!matchingFinished()) {
            for (UG ug : arrUG) {
                ug.propose();
            }
        }
    }

    private static boolean matchingFinished() {
        for (UG ug : arrUG) {
            if (!ug.isProposingOver()) {
                return false;
            }
        }
        return true;
    }

    public static void persist() {
        try {
            DatabaseConnectionHandler.closeConnection();
            Connection con = DatabaseConnectionHandler.createConnection();

            PreparedStatement s = con.prepareStatement("UPDATE undergrad SET assigned0 = ? , assigned1 = ?, assigned2 = ?");
            s.setString(1, "none");
            s.setString(2, "none");
            s.setString(3, "none");
            s.executeUpdate();

            String queryCheck = "SELECT * FROM undergrad ORDER BY id";

            PreparedStatement ps = con.prepareStatement(queryCheck, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                if (arrUG[i].assigned[0] != null) {
                    rs.updateString("assigned0", arrUG[i].assigned[0].name);
                }
                if (arrUG[i].assigned[1] != null) {
                    rs.updateString("assigned1", arrUG[i].assigned[1].name);
                }
                if (arrUG[i].assigned[2] != null) {
                    rs.updateString("assigned2", arrUG[i].assigned[2].name);
                }
                rs.updateRow();
                i++;
                System.out.println("Saved assigned companies for " + i);
            }

            queryCheck = "UPDATE company set assigned = ? where id =?";

            ps = con.prepareStatement(queryCheck);

            ps.setString(1, queryCheck);
            for (int j = 0; j < arrCMP.length; j++) {
                //ps.setInt(1, arrCMP[j].list.size());
                ps.setString(2, arrCMP[j].c.getId() + "");
                ps.executeUpdate();
                System.out.println("Saved assigned number for company " + j);
            }

            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void sortCMP() {
        Comparator<CMP> comparator = new Comparator<CMP>() {
            public int compare(CMP o1, CMP o2) {
                if (o2.rating - o1.rating < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        Arrays.sort(arrCMP, comparator);
    }

    public static void sortUG() {
        Comparator<UG> comparator = new Comparator<UG>() {
            public int compare(UG u1, UG u2) {
                return u1.u.getId() - u2.u.getId();
            }
        };
        Arrays.sort(arrUG, comparator);
    }
    
    public static void sortUGbyId() {
        Comparator<UG> comparator = new Comparator<UG>() {
            public int compare(UG u1, UG u2) {
                return u1.u.getId() - u2.u.getId();
            }
        };
        Arrays.sort(arrUG, comparator);
    }

}
