/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.Account;
import app.model.LoginEvent;

/**
 *
 * @author Bjarki
 */
public interface LoginEventService {
    
    public void createNewLoginEvent(String user);
    
}
