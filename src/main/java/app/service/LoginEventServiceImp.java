/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.Account;
import app.model.LoginEvent;
import app.repository.AccountRepository;
import app.repository.LoginEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bjarki
 */
@Service
public class LoginEventServiceImp implements LoginEventService{
    
    
    @Autowired
    LoginEventRepository loginEventRep;
    
    
    @Autowired
    AccountRepository accountRep;
    
    public void createNewLoginEvent(String user){
        Account account = accountRep.findByEmail(user);
        LoginEvent loginEvent = new LoginEvent(account);
        loginEventRep.save(loginEvent);
    }
    
    
}
