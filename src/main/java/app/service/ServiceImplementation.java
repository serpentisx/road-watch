/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

/**
 *
 * @author Bjarki
 */
public class ServiceImplementation implements Service {
    
    
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
