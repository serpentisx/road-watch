/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy Van Nguyen
 */

public interface UserService {
    
    public boolean validateUser(String username, String password);

    public void addUser(User user);

    public List<User> getAllUsers();
}
