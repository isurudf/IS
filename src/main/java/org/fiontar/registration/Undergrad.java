/**
 * This Undergrad class object used to get the data of a undergrad
 */
package org.fiontar.registration;
import java.io.Serializable;

public class Undergrad extends User implements Serializable{
    private String index;
    private String email;
    private String name;
    private String password;
    private String addr;
    private String phone;
    private String resume;
    private int id;
    private String verification;
    private int verified;
    private String companies;
    private String field;
    private String passwordReset;
    private String emailLink;
    
    public Undergrad(String index, String email, String name, String password, String addr, String phone, String resume, int id, String verification, int verified, String companies, String field, String passwordReset, String emailLink) {
        
        super("undergradDashboard.jsp", name, index);
        this.index=index;
        this.email = email;
        this.name = name;
        this.password = password;
        this.addr = addr;
        this.phone = phone;
        this.resume = resume;
        this.id = id;
        this.verification = verification;
        this.verified = verified;
        this.companies = companies;
        this.field=field;
        this.passwordReset=passwordReset;
        this.emailLink=emailLink;
    }
    
    
    public String getAddr() {
        
        return addr;
    }

    public String getCompanies() {
        return companies;
    }

    public String getField() {
        return field;
    }

    public String getIndex() {
        return index;
    }

    public String getEmailLink() {
        return emailLink;
    }

    public void setEmailLink(String emailLink) {
        this.emailLink = emailLink;
    }
    
    

    public void setField(String field) {
        this.field = field;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    
    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getLink() {
        return super.getLink(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getResume() {
        return resume;
    }

    public String getVerification() {
        return verification;
    }

    public int getVerified() {
        return verified;
    }

    public String getPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(String passwordReset) {
        this.passwordReset = passwordReset;
    }
    

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }
    
    
    
    
    
}
