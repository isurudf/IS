/*
 * A class to implement the login of a user
 */
package org.fiontar.registration;

/**
 * @author Fiontar
 */

public class User {
    private final String link;
    private final String name;
    private final String index;
    /**
     * Constructor of user class
     * @param link link address
     * @param name user name 
     */
    public User(String link, String name, String index) {
        this.link = link;
        this.name = name;
        this.index = index;
    }
    /**
     * method gives the link address
     * @return link address
     */
    public String getLink() {
        return link;
    }
    /**
     * method gives the name of the user
     * @return user name
     */
    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }
    
}
