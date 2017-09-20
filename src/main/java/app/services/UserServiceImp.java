package app.services;

import app.model.User;
import app.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * An implementation of a service class for users
 * 
 * This class implements the general user service interface
 * Controller classes should use this class for services and use of repository.
 */

@Service
public class UserServiceImp implements UserService {
    
    // A connection to user repository class
    @Autowired
    UserRepository user;

    /**
    * Checks for a user existence in the repository
    * 
    * @param String username:  the user's username
    * @param String password:  the user's password
    */
    @Override
    public boolean validateUser(String username, String password) {
        for (User u : getAllUsers()) {
            if (u.getUsername().equals(username) && u.getUserPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * Adds a user to the repository
    * 
    * @param User user:  user to be added to the repository
    */
    @Override
    public void addUser(User user) {
        this.user.add(user);
    }

    /**
    * Get all users in the repository
    * 
    * @return List<User>:  a list of users.
    */
    @Override
    public List<User> getAllUsers() {
        return this.user.getAll();
    }
}
