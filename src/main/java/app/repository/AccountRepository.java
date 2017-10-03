/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Bjarki
 */
public interface AccountRepository extends JpaRepository<Account, String> {
    
    @Override
    public List<Account> findAll();
    
    @Override
    public Account save(Account account);
    
    public Account findByEmail(String email);
    
    @Modifying
    @Query (value="UPDATE Account SET password = ?2 WHERE email = ?1")
    public void changePassword(String email, String password);
    
    @Query (value="DELETE FROM Account WHERE email=?1")
    public void deleteByEmail(String email);
    
}
