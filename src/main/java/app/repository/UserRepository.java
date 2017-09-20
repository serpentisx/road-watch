package app.repository;

import app.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * A repository for users
 * 
 * This is a interface that defines methods that all repository for users 
 * should meet
 */

public interface UserRepository  {
    /**
     * Fetches all users
     * @return list of users
     */
    List <User> getAll();
    
    /**
     * Adds user to the repository
     * @param user 
     */
    public void add (User user);
}
