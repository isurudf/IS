package org.fiontar.admin.algo;

import java.util.ArrayList;
import org.fiontar.registration.Undergrad;
/**
 *
 * @author Isuru Fernando
 */
public class ug {

    public Undergrad u;
    public int field;
    public cmp[] assigned = new cmp[3];
    private ArrayList<cmp> list = new ArrayList<cmp>();
    boolean[] how = new boolean[3];

    public ug(Undergrad u) {
        this.u = u;
        field = -1;
        for (int i = 0; i < 16; i++) {
            if (u.getField().equals(Assign.fields[i])) {
                field = i;
                Assign.ugByField[field]++;
                break;
            }
        }
        String companies = u.getCompanies();
        if (companies != null && companies.length() > 1) {
            companies = companies.substring(0, companies.length() - 1);
            String[] arr = companies.split(",");
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < Assign.arrCMP.length; j++) {
                    if (arr[i].equals(Assign.arrCMP[j].name)) {
                        list.add(Assign.arrCMP[j]);
                        Assign.arrCMP[j].pref.add(this);
                        Assign.arrCMP[j].fieldRating[field] += Assign.cmpByField[field] - list.size();
                    }
                }
            }
        }
    }

    public void assign(int i) {
        while (!list.isEmpty()) {
            if (list.get(0).capacity > 0) {
                cmp c = list.get(0);
                assigned[i] = c;
                c.capacity--;
                c.pref.remove(this);
                c.list.add(this);
                list.remove(0);
                how[i]=true;
                break;
            }
            list.remove(0);
        }
    }

    public void assignOther(int k) {
        if (u.getCompanies()!=null&&assigned[k] == null) {
            
            String c = u.getCompanies();
            if(c.length()<2)
                return;
       /*     if(!c.contains(","))
                return;
            if(c.indexOf(",")==c.lastIndexOf(","))
                return;*/
            ArrayList<cmp> cmplist = Assign.arrCmpByField.get(field);
            for (int i = 0; i < cmplist.size(); i++) {
                if (cmplist.get(i).capacity > 0) {
                    if(cmplist.get(i).c.getShortName().equals("fonterra"))
                        continue;
                    if(assigned[0]!=null&&assigned[0].equals(cmplist.get(i)))
                        continue;
                    if(assigned[1]!=null&&assigned[1].equals(cmplist.get(i)))
                        continue;
                    how[k]=false;
                    assigned[k] = cmplist.get(i);
                    
                    cmplist.get(i).capacity--;
                    cmplist.get(i).list.add(this);
                    cmplist.get(i).pref.remove(this);
                    break;
                }
            }
        }
    }

    public String getAssigned(int k) {
        try {
            if(how[k])
                return assigned[k].getName()+"*";
            else
                return assigned[k].getName()+"**";
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