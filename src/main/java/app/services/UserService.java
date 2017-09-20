package app.services;

import app.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * A service class for users
 * 
 * This is a interface that defines methods that all service classes for users 
 * should meet
 */

public interface UserService {
    
    /**
    * Checks for a user existence in the repository
    * 
    * @param String username:  the user's username
    * @param String password:  the user's password
    */
    public boolean validateUser(String username, String password);

    /**
    * Adds a user to the repository
    * 
    * @param User user:  user to be added to the repository
    */
    public void addUser(User user);

    /**
    * Get all users in the repository
    * 
    * @return List<User>:  a list of users.
    */
    public List<User> getAllUsers();
}
