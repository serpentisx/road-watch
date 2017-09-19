package app.repository;

import app.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @date september 2017
 * HBV501G Software Development 1
 * University of Iceland
 * @author Huy Van Nguyen
 * 
 * Storage for all users
 * 
 */

public interface UserRepository  {
    /**
     * Fetches all users
     * @return list of users
     */
    List <User> getAll();
    
    /**
     * Adds user to the storage
     * @param user 
     */
    public void add (User user);
}
