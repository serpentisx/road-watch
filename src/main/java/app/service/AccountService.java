/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Manages all process work regarding account
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
    public boolean verifyPassword(String email, String password);
    
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
    * Log in the user
    *
    * @param email      the email address
    * @param password   the password
    */
    public boolean loginUser(String email, String password);
    
    /**
    * Log out the user
    *
    */
    public void logoutUser();
    
    /**
    * Get the current logged in user.
    *
    * @return           the logged in user's email
    */
    public String getLoggedInUserName();

   /**
    * Get the current logged in user.
    *
    * @return the logged in user's email
    */
    public String getLoggedInUserEmail();
    
  
    /**
    * Authenticates the user and store it as current logged in user.
    *
    * @param email      the email address
    * @param password   the password
    */
    public void authenticateUser(String username, String password);

   /**
    * Delete an account with responding email in the database.
    *
    * @param email      the email address
    */
    public boolean deleteAccount(String email);

   /**
    * Change account password with responding email in the database.
    *
    * @param email      the email address
    * @param password   the new password
    */
    public boolean changePassword(String email, String newPassword);

   /**
    * Change account name with responding email in the database.
    *
    * @param email      the email address
    * @param newName    the new username
    */
    public boolean changeName(String email, String newName);
}
