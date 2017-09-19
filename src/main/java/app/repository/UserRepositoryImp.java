/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.repository;

import app.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImp implements UserRepository {

    // List of users 
    private final List<User> user;

    public UserRepositoryImp() {
        this.user = new ArrayList<User>();
    }
    
    @Override
    public List<User> getAll() {
        return user; 
    }

    @Override
    public void add (User user) {
        this.user.add(user);
    }
}
