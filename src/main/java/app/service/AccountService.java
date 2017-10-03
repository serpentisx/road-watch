/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

/**
 *
 * @author Huy Van Nguyen
 */
public interface AccountService {

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
    
    /**
    * Creates a new user/account
    *
    * @param email      the email address
    * @param password   the password
    * @param username   the display name
    * @return           true if the email and password match
    */
    public boolean createNewAccount(String username, String password, String email);
    
    /**
    * Log out the user
    *
    * @param email      the email address
    * @param password   the password
    */
    public void logoutUser(String email, String password);
    
    /**
    * Get the current logged in user.
    *
    * @return           the logged in user's email
    */
    
    public String getLoggedInUser();
    
  
    /**
    * Authenticates the user and store it as current logged in user.
    *
    * @param email      the email address
    * @param password   the password
    */
    
    public void authenticateUser(String username, String password);
    
    public boolean deleteAccount(String email);

    public boolean changePassword(String email, String newPassword);

    public boolean changeName(String email, String newName);
}
