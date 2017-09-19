/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.User;
import app.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy Van Nguyen
 */

@Service
public class UserServiceImp implements UserService {
    
    // Tenging yfir í safn af kennurum 
    @Autowired
    UserRepository user;

    @Override
    public boolean validateUser(String username, String password) {
        for (User u : getAllUsers()) {
            if (u.getUsername().equals(username) && u.getUserPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addUser(User user) {
        this.user.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.user.getAll();
    }
}
