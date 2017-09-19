/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

/**
 *
 * @author Huy Van Nguyen
 */
public class User {
    
    private String username;
    private String userPassword;
    private String userEmail;
    
    public User(String user_name, String user_password, String user_email) {
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
