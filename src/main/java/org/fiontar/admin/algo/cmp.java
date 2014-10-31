package org.fiontar.admin.algo;

import java.util.ArrayList;
import org.fiontar.admin.Company.Company;

public class cmp {

    public Company c;
    int capacity;
    String fields;
    String name;
    String undergrads;
    boolean[] field = new boolean[16];
    double[] fieldRating = new double[16];
    public ArrayList<ug> list = new ArrayList<ug>();
    public ArrayList<ug> pref = new ArrayList<ug>();

    public cmp(Company c) {
        this.c = c;
        name = c.getShortName();
        capacity = c.getCapacity();
        fields = c.getFields();
        for (int i = 0; i < 16; i++) {
            if (fields.contains("," + Assign.fields[i] + ",")) {
                field[i] = true;
                Assign.cmpByField[i]++;
                Assign.arrCmpByField.get(i).add(this);
            }
        }
        undergrads = c.getUndergrads();
    
    }

    public String getName() {
        return c.getName();
    }
    public String getUndergrads() {
	    return undergrads;
    }
    
}