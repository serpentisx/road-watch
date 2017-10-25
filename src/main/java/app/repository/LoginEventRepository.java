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
 *
 * @author Bjarki Viðar Kristjánsson
 */
public interface LoginEventRepository extends JpaRepository<LoginEvent, Integer>{
    
    /**
     * Find all LoginEvents in database
     * @return list of all post found in database
     */
    @Override
    public List<LoginEvent> findAll();
    
    @Override
    public LoginEvent save(LoginEvent loginEvent);
}
