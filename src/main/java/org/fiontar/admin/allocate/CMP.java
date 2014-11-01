package org.fiontar.admin.allocate;

import java.util.ArrayList;
import org.fiontar.admin.Company.Company;
import static org.fiontar.admin.allocate.Assign.mapUndergrad;

public class CMP {

    public Company c;
    int capacity;
    String fields;
    public String name;
    double rating = 0;
    public ArrayList<UG> prefUndergrads = new ArrayList<UG>();
    public UG[] arrUG;

    public CMP(Company c) {
        this.c = c;
        name = c.getShortName();
        capacity = c.getCapacity();
        arrUG = new UG[capacity];
    }

    public void populateFields() {
        //Adding preferred undergraduates to company
        String[] ugs = c.getUndergrads().split(",");
        for (String ug : ugs) {
            if (ug.trim().length() == 0) {
                continue;
            }
            Integer id2 = Integer.parseInt(ug);
            UG u1 = mapUndergrad.get(id2);
            prefUndergrads.add(u1);
        }
    }

    public boolean reply(UG proposer) {
        for (int i = 0; i < arrUG.length; i++) {
            if (arrUG[i] == null) {
                arrUG[i] = proposer;
                return true;
            }
        }
        for (int i = 0; i < arrUG.length; i++) {
            if (rank(arrUG[i]) > rank(proposer)) {
                arrUG[i].remove(this);
                arrUG[i] = proposer;
                return true;
            }
        }
        return false;
    }

    private int rank(UG ug) {
        return prefUndergrads.indexOf(ug);
    }

    public void completeList() {
        for (UG ug : Assign.arrUG) {
            if (!prefUndergrads.contains(ug)) {
                prefUndergrads.add(ug);
            }
        }
    }

}
