/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bjarki Viðar Kristjánsson
 * 
 * Model for the  LoginEvent
 */
@Entity
@Table (name="LoginEvent")
public class LoginEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LoginEventId;
    
    @Column (name="timestamp")
    private LocalTime time;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "email")
    private Account account;    // user account associated with the LoginEvent
    
    
    
    
    public LoginEvent(Account account){
        this.account = account;
        time = LocalTime.now();
    }
    
    public LoginEvent () {}

    public int getLoginEventId() {
        return LoginEventId;
    }

    public Account getAccount() {
        return account;
    }


    public LocalTime getTime() {
        return time;
    }
    
    
    
}