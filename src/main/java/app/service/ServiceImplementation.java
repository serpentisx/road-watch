/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.repository.AccountRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Bjarki
 */
@Service
public class ServiceImplementation implements MainService {
    
    
    @Autowired
    AccountRepository ar;
    
    @Autowired
    PostRepository pr;
    
    @Override
    public boolean verifyNewUser(String email) {
        /* vantar */
        return true;
    }
   
    
    @Override
    public boolean verifyLoginRequest(String email, String password){
        /* vantar */
        return true;
    }
}
