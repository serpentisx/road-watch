package app.service;

import app.model.Account;
import app.repository.AccountRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy Van Nguyen
 */
@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    AccountRepository accountRep;
    
    @Autowired
    PostRepository postRep;
    
  //  @Autowired
  //  private AuthenticationManager authenticationManager;

  //  @Autowired
  //  private UserDetailsService userDetailsService;

    @Override
    public boolean verifyNewUser (String email) {
        Account account = accountRep.findByUserEmail(email);
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
    public boolean verifyLoginRequest (String email, String password) {
        boolean isCorrect = false;
        
        Account user = accountRep.findByUserEmail(email);
        try {
            isCorrect = PasswordStorage.verifyPassword(password, user.getUserPassword());
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return isCorrect;
    }
    
    @Override
    public void logoutUser (String email, String password) {
        
    }

    @Override
    public String getLoggedInUser () {
        return "";
    }

    @Override
    public void authenticateUser (String email, String password) {
       // UserDetails userDetails = userDetailsService.loadUserByUsername(email);
      //  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

       // authenticationManager.authenticate(usernamePasswordAuthenticationToken);

       // if (usernamePasswordAuthenticationToken.isAuthenticated()) {
       //    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
       // }
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
         Account ac = accountRep.findByUserEmail(email);
         ac.setUserPassword(PasswordStorage.createHash(newPassword));
         return true;
         } catch(Exception e){
             e.printStackTrace();
             return false;
         }
    }

    @Override
    public boolean changeName(String email, String newName){
         try{
         Account ac = accountRep.findByUserEmail(email);
         ac.setUsername(newName);
         return true;
         } catch(Exception e){
             e.printStackTrace();
             return false;
         }
    }
}