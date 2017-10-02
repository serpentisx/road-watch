/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

/**
 *
 * @author Bjarki
 */
public interface MainService {
    
   /**
    * Verifies whether the email address used in an attempt to
    * create a new account already exists in the database or not.
    *
    * @param email the email address
    * @return      true if email address does not already exists
    */
    public boolean verifyNewUser(String email);
    
   /**
    * Verifies whether the email address and password used in
    * a log-in attempt match an existing account or not.
    *
    * @param email      the email address
    * @param password   the password
    * @return           true if the email and password match
    */
    public boolean verifyLoginRequest(String email, String password);
    
}
