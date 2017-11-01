
package app.service;

import app.model.Account;
import app.model.LoginEvent;
import app.repository.AccountRepository;
import app.repository.LoginEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bjarki
 */
@Service
public class LoginEventServiceImp implements LoginEventService {
    
    @Autowired
    LoginEventRepository loginEventRep;
    
    @Autowired
    AccountRepository accountRep;
    
    @Override
    public void createNewLoginEvent(String user){
        Account account = accountRep.findByEmail(user);
        LoginEvent loginEvent = new LoginEvent(account);
        loginEventRep.save(loginEvent);
    }
}
