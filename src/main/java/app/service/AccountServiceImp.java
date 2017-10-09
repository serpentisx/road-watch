package app.service;

import app.model.Account;
import app.repository.AccountRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean verifyNewUser (String email) {
        Account account = accountRep.findByEmail(email);
        if (account == null) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean createNewAccount(String username, String password, String email) {
        String hashedPassword = null;
        try {
            hashedPassword = PasswordStorage.createHash(password);
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            ex.printStackTrace();
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
        boolean isCorrect = false;
        
        Account user = accountRep.findByEmail(email);
        try {
            isCorrect = PasswordStorage.verifyPassword(password, user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return isCorrect;
    }
    
    @Override
    public boolean loginUser(String email, String password) {
        boolean login_success = verifyPassword(email, password);
        if (login_success) {
            authenticateUser(email, password);
        }
        return login_success;
    }
    
    @Override
    public void logoutUser () {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    
    @Override
    public String getLoggedInUserEmail () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    
    @Override
    public String getLoggedInUserName () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRep.findByEmail(getLoggedInUserEmail());
        return account.getUsername();
    }
    
    @Override
    public void authenticateUser (String email, String password) {    
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(token);
    }
    
    @Override
    public boolean deleteAccount(String email) {
          try{
            accountRep.deleteByEmail(email);
            return true;
          } catch(Exception e){
              e.printStackTrace();
              return false;
          }
          
    }

    @Override
    public boolean changePassword(String email, String newPassword){
         try{
         System.out.print("email: "+email +" "+ "newPass: "+newPassword);
         accountRep.changePassword(email, PasswordStorage.createHash(newPassword));
         return true;
         } catch(Exception e){
             e.printStackTrace();
             return false;
         }
    }

    @Override
    public boolean changeName(String email, String newName){
         try {
         Account ac = accountRep.findByEmail(email);
         ac.setUsername(newName);
         return true;
         } catch(Exception e){
             e.printStackTrace();
             return false;
         }
    }
}