/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Bjarki
 */
public interface AccountRepository extends JpaRepository<Account, String>{
    
    List<Account> findAll();
    
    
}
