package org.fiontar.admin.allocate;

import java.util.ArrayList;
import org.fiontar.admin.Company.Company;
import static org.fiontar.admin.allocate.Assign.mapUndergrad;

public class CMP {

    public Company c;
    int capacity;
    public String name;
    double rating = 0;
    public ArrayList<UG> prefUndergrads = new ArrayList<UG>();
    public ArrayList<UG> arrUG;
    public UG[] slots = new UG[20];

    public CMP(Company c) {
        this.c = c;
        name = c.getShortName();
        capacity = c.getCapacity();
        capacity = 20;
        arrUG = new ArrayList<UG>();
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
       
        if(arrUG.size() < 20) {
            arrUG.add(proposer);
            return true;
        }
        
        for (int i = 0; i < arrUG.size(); i++) {
            if (getRank(arrUG.get(i)) > getRank(proposer)) {
                arrUG.get(i).remove(this);
                arrUG.set(i, proposer);
                System.out.println("Matched "+this.name+" and "+proposer.getName());
                return true;
            }
        }
        return false;
    }

    private int getRank(UG ug) {
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
