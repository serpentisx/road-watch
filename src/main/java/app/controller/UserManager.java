/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

/**
 *
 * @author Bjarki
 */
public class UserManager {
    
    private void newUser(String name, String password, String email){
        
    }
    
    private void login(String email, String password){
        if(verifyLoginRequest(email,password)){
            //user kemst inn
        }
        else{
            //user kemst ekki inn
        }
    }
    
    private void deleteUser(String email){
        
    }
    
    private boolean verifyNewUser(String email){
        return true;
    }
    
    private boolean verifyLoginRequest(String email, String password){
        return true;
    }
    
    private void changePassword(String email, String password){
        
    }
    
    private void changeName(String email){
        
    }
    
}
