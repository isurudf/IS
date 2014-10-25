package org.fiontar.admin.algo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import org.apache.commons.net.ftp.FTPClient;
import org.fiontar.admin.Company.Company;
import org.fiontar.admin.Company.CompanyDA;
import org.fiontar.api.Database.DatabaseConnectionHandler;
import org.fiontar.api.Database.FTPTransfer;
import org.fiontar.registration.Undergrad;
import org.fiontar.registration.dao.UndergradDA;

public class Assign {

    static String[] fields = {"ARCH", "CPE", "CE", "CSE", "ERM", "EE", "ENTC", "FM", "IT", "FD", "ME", "MECH", "QS", "TC", "TCP", "TLM"};
    public static ug[] arrUG;
    public static cmp[] arrCMP;
    static int[] cmpByField = new int[16];
    static int[] ugByField = new int[16];
    static ArrayList<ArrayList<cmp>> arrCmpByField = new ArrayList<ArrayList<cmp>>(16);

    public static void initialise() {
        if (arrUG == null) {
            assign();
        }
    }

    public static void assign() {
        arrUG = null;
        arrCMP = null;
        cmpByField = new int[16];
        ugByField = new int[16];
        arrCmpByField = new ArrayList<ArrayList<cmp>>(16);

        ArrayList<Undergrad> listUndergrad = UndergradDA.getAllUnderGrads();
        UndergradDA.list = listUndergrad;
        ArrayList<Company> listCompany = CompanyDA.getCompany();

        for (int i = 0; i < 16; i++) {
            arrCmpByField.add(new ArrayList<cmp>());
        }

        arrCMP = new cmp[listCompany.size()];
        for (int i = 0; i < listCompany.size(); i++) {
            arrCMP[i] = new cmp(listCompany.get(i));
        }

        arrUG = new ug[listUndergrad.size()];
        for (int i = 0; i < listUndergrad.size(); i++) {
            arrUG[i] = new ug(listUndergrad.get(i));
        }
        System.out.println("Info gotten from DB");
        for (int i = 0; i < arrUG.length; i++) {
            arrUG[i].assign(0);
        }
        System.out.println("Assigned First");
        for (int i = 0; i < arrUG.length; i++) {
            arrUG[i].assign(1);
        }
        System.out.println("Assigned Second");
        for (int i = 0; i < arrUG.length; i++) {
            arrUG[i].assign(2);
        }
        System.out.println("Assigned Third");
        for (int i = 0; i < 16; i++) {
            comp c = new comp(i);
            Collections.sort(arrCmpByField.get(i), c);
        }

        for (int i = 0; i < arrUG.length; i++) {
            arrUG[i].assignOther(0);
        }
        System.out.println("Assigned first for no preference");
        for (int i = 0; i < arrUG.length; i++) {
            arrUG[i].assignOther(1);
        }
        System.out.println("Assigned second for no preference");
        for (int i = 0; i < arrUG.length; i++) {
            arrUG[i].assignOther(2);
        }
        System.out.println("Assigned third for no preference");
        for (int i = 0; i < arrCMP.length; i++) {
            sortUGByName(arrCMP[i].list);
        }
        
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
                ps.setInt(1, arrCMP[j].list.size());
                ps.setString(2, arrCMP[j].c.getId()+"");
                ps.executeUpdate();
                System.out.println("Saved assigned number for company " + j);
            }
                
            
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<ug> sortUG() {
        ArrayList<ug> list = new ArrayList<ug>();
        list.addAll(Arrays.asList(arrUG));

        Comparator<ug> comparator = new Comparator<ug>() {
            @Override
            public int compare(ug u1, ug u2) {
                if(u1.u.getCompanies().length()<2)
                    return 1;
                if(u2.u.getCompanies().length()<2)
                    return 1;
                if (u1.field == u2.field) {
                    return u1.u.getIndex().substring(0, 6).compareTo(u2.u.getIndex().substring(0, 6));
                } else {
                    return u1.field - u2.field;
                }
            }
        };
        Collections.sort(list, comparator);

        return list;
    }
    public static void sortUGByName(ArrayList<ug> list) {

        Comparator<ug> comparator = new Comparator<ug>() {
            @Override
            public int compare(ug u1, ug u2) {
                if (u1.field == u2.field) {
                    return u1.getName().compareTo(u2.getName());
                } else {
                    return u1.field - u2.field;
                }
            }
        };
        Collections.sort(list, comparator);

    }

    public static HashSet<Integer> getNumbersAssigned(String company) {
        HashSet<Integer> numbers = new HashSet<Integer>();
        for (int i = 0; i < arrCMP.length; i++) {
            if (arrCMP[i].name.equals(company)) {
                cmp c = arrCMP[i];
                for (int j = 0; j < c.list.size(); j++) {
                    if (c.list.get(j).getPhone().length() == 10) {
                        numbers.add(Integer.parseInt(c.list.get(j).getPhone()));
                    }
                }
                return numbers;
            }
        }
        return numbers;
    }

    public static HashSet<Integer> getNumbersPreferred(String company) {
        HashSet<Integer> numbers = new HashSet<Integer>();
        for (int i = 0; i < arrCMP.length; i++) {
            if (arrCMP[i].name.equals(company)) {
                cmp c = arrCMP[i];
                for (int j = 0; j < c.pref.size(); j++) {
                    if (c.pref.get(j).getPhone().length() == 10) {
                        numbers.add(Integer.parseInt(c.pref.get(j).getPhone()));
                    }
                }
                return numbers;
            }
        }
        return numbers;
    }
}

class comp implements Comparator<cmp> {

    int field;

    public comp(int field) {
        this.field = field;
    }

    public int compare(cmp o1, cmp o2) {
        if (o2.fieldRating[field] - o1.fieldRating[field] < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}