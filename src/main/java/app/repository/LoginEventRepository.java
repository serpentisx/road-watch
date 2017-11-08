/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.model.LoginEvent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Stores all login events by users
 */
public interface LoginEventRepository extends JpaRepository<LoginEvent, Integer>{
    
    /**
     * Find all LoginEvents in database
     * @return list of all post found in database
     */
    @Override
    public List<LoginEvent> findAll();
    
    /**
     * Save a login event in the database
     * @param loginEvent : the login event to save
     * @return : the LoginEvent saved
     */
    @Override
    public LoginEvent save(LoginEvent loginEvent);
}
