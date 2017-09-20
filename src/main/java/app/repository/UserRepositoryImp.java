package app.repository;

import app.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * A repository implementation for users
 * 
 * This class implements the general user repository interface
 * Service classes should use this class as their connection (autowire) to a repository
 */

@Repository
public class UserRepositoryImp implements UserRepository {

    // List of users 
    private final List<User> user;

    public UserRepositoryImp() {
        this.user = new ArrayList<User>();
    }
    
    // Get all users
    // returns a list of users
    @Override
    public List<User> getAll() {
        return user; 
    }

    // Add a user to the repository
    // @param User user: user to be added to the repository
    @Override
    public void add (User user) {
        this.user.add(user);
    }
}
