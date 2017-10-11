package app.service;

import app.model.Account;
import app.repository.AccountRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 */
@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    AccountRepository accountRep;
    
    @Autowired
    PostRepository postRep;
    
    // provisional resort while we haven't figured out how to save the user's session
    String provisionalEmail = "notandi@hi.is";

    @Override
    public boolean verifyNewUser (String email) {
        Account account = accountRep.findByEmail(email);
        return account == null;
    }
    
    @Override
    public boolean createNewAccount(String username, String password, String email) {
        String hashedPassword = null;
        try {
            hashedPassword = PasswordStorage.createHash(password);
        } catch (PasswordStorage.CannotPerformOperationException e) { 
            e.printStackTrace(System.out); 
        }
        if (hashedPassword != null) {
            Account account = new Account(username, hashedPassword, email);
            accountRep.save(account);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean verifyPassword (String email, String password) {
        boolean verification = false;
        Account account = accountRep.findByEmail(email);
        // The email entered might not match an existing account
        if (account == null) { 
          return verification; 
        }
        try {
            verification = PasswordStorage.verifyPassword(password, account.getPassword());
        } catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace(System.out);
        } catch (PasswordStorage.InvalidHashException e) {
            e.printStackTrace(System.out);
        }
        return verification;
    }

    @Override
    public boolean deleteAccount(String email) {
        Account account = accountRep.findByEmail(email);
        return accountRep.findAll().remove(account);
    }

    @Override
    @Transactional
    public boolean changePassword(String email, String newPassword){
        Account account = accountRep.findByEmail(email);
        try {
            account.setPassword(PasswordStorage.createHash(newPassword));
            return true;
        }
        catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean changeName(String email, String newName){
        try {
            Account account = accountRep.findByEmail(email);
            account.setUsername(newName);
            return true;
        } catch(Exception e){
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    public String findUsernameByEmail(String email){
        Account account = accountRep.findByEmail(email);
        return account.getUsername();
    }
}