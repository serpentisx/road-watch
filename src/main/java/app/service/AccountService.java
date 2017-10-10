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
     * Deletes an account corresponding to the given email
     *
     * @param email     the user's email address
     * @return          true if successful
     *
     */
    public boolean deleteAccount(String email);

    /**
     * Changes the password of the account corresponding to the given email
     * 
     * @param email         the user's email address
     * @param newPassword   the new password
     * @return              true if successful
     */
    public boolean changePassword(String email, String newPassword);

    /**
     * Changes the username of the account corresponding to the given email
     *
     * @param email         the user's email address
     * @param newName       the new name
     * @return              true if successful
     */
    public boolean changeName(String email, String newName);

    /**
     * Find username by email
     *
     * @param email         the user's email address
     * @return              the username for corresponding email
     */
    public String findUsernameByEmail(String email);
}
