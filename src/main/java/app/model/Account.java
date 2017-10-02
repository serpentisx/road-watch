package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * Model for the user. 
 * Stores login information and user's email
 */

@Entity
@Table (name="Account")
public class Account {
 
    @Column (name="name")
    private String username;
    
    @Column (name="password")
    private String userPassword;
    
    @Id
    @Column (name="email")
    private String userEmail;
    
    public Account(String user_name, String user_password, String user_email) {
        this.username = user_name;
        this.userPassword = user_password;
        this.userEmail = user_email;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
