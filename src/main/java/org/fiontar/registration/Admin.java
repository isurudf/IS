/*
 * A class to implement the login of a user
 */
package org.fiontar.registration;

/**
 * @author Fiontar
 */

public class Admin extends User{
    /**
     * constructor of Admin class
     * @param name user name
     */
    private String email;
    public Admin(String name, String email){
        super("admin.jsp", name,name);
        this.email=email; 
    }

    public String getEmail() {
        return email;
    }
    
}
