
package app.service;

import app.exceptions.HashException;
import app.exceptions.PasswordVerificationException;
import app.model.Account;
import app.repository.AccountRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 12 November 2017
 * 
 */
@Service
public class AccountServiceImp implements AccountService {
    
    @Autowired
    AccountRepository accountRep;
    
    @Autowired
    PostRepository postRep;
    
    @Autowired 
    PasswordService passwordService;
    
    @Override
    public boolean verifyNewUser (String email) {
        Account account = accountRep.findByEmail(email);
        return account == null;
    }
    
    @Override
    @Transactional
    public void createNewAccount(String username, String password, String email)
      throws HashException {
        String hashedPassword = passwordService.createHash(password);
        Account account = new Account(username, hashedPassword, email);
        accountRep.save(account);
    }
    
    @Override
    public boolean verifyPassword (String email, String password) throws PasswordVerificationException {
        Account account = accountRep.findByEmail(email);
        
        // The email entered might not match an existing account
        if (account != null) { 
            try {
                return passwordService.verifyPassword(password, account.getPassword()); 
            } catch (HashException e) {
                throw new PasswordVerificationException(e);
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteAccount(String email) {
        Account account = accountRep.findByEmail(email);
        
        if (account != null) {
            accountRep.delete(account);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean changePassword(String email, String newPassword) throws HashException {
        Account account = accountRep.findByEmail(email);
        
        if (account != null) {
          account.setPassword(passwordService.createHash(newPassword));
          return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean changeName(String email, String newName) {
        Account account = accountRep.findByEmail(email);
        
        if (account != null) {
            account.setUsername(newName);
            return true;
        }
        return false;
    }

    @Override
    public String findUsernameByEmail(String email) {
        Account account = accountRep.findByEmail(email);
        if (account != null) return account.getUsername();
        return null;
    }
}