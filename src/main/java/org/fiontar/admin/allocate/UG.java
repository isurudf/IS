package org.fiontar.admin.allocate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.fiontar.registration.Undergrad;

/**
 *
 * @author Isuru Fernando
 */
class UG {

    public Undergrad u;
    public CMP[] assigned = new CMP[3];
    private ArrayList<CMP> prefComps = new ArrayList<CMP>();
    private Queue<CMP> proposed;
    public CMP[] interviews = new CMP[3];

    public UG(Undergrad u) {
        this.u = u;
    }

    public void populateFields() {
        String companies = u.getCompanies();
        if (companies != null && companies.length() > 1) {
            companies = companies.substring(0, companies.length() - 1);
            String[] arr = companies.split(",");
            for (String company : arr) {
                CMP cmp = Assign.mapCmp.get(company);
                if (cmp != null) {
                    prefComps.add(cmp);
                    cmp.rating += Assign.arrCMP.length - prefComps.size();
                }
            }
        }
    }

    public void propose() {
        if (proposed.isEmpty()) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (assigned[i] == null) {
                CMP cmp = proposed.remove();
                if (cmp.reply(this)) {
                    assigned[i] = cmp;
                }
                return;
            }
        }
    }
    
    public boolean isProposingOver(){
        if(proposed.isEmpty())
            return true;
        for (int i = 0; i < 3; i++) {
            if (assigned[i] == null) {
                return false;
            }
        }
        return true;
    }

    public void remove(CMP cmp) {
        for (int i = 0; i < 3; i++) {
            if (assigned[i] == cmp) {
                assigned[i] = null;
            }
        }
    }

    public void completeList() {
        for (CMP cmp : Assign.arrCMP) {
            if (!prefComps.contains(cmp)) {
                prefComps.add(cmp);
            }
        }
        proposed = new LinkedList<CMP>(prefComps);
    }

    public String getAssigned(int k) {
        try {
            return assigned[k].name + "**";
        } catch (NullPointerException e) {
            return "No Company Assigned for this slot";
        }
    }

    public String getField() {
        return u.getField();
    }

    public String getName() {
        return u.getName();
    }

    public String getPhone() {
        return u.getPhone();
    }
}
