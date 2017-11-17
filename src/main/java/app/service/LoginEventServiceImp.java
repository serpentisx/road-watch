
package app.service;

import app.model.Account;
import app.model.LoginEvent;
import app.repository.AccountRepository;
import app.repository.LoginEventRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 20 October 2017
 * 
 */
@Service
public class LoginEventServiceImp implements LoginEventService {
    
    @Autowired
    LoginEventRepository loginEventRep;
    
    @Autowired
    AccountRepository accountRep;
    
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(new Locale("is"));
    
    @Override
    public void createNewLoginEvent(String user){
        Account account = accountRep.findByEmail(user);
        LoginEvent loginEvent = new LoginEvent(account);
        loginEventRep.save(loginEvent);
    }
    
    @Override 
    public String getLatestLoginDate(String user) {
        Instant latest = loginEventRep.latestLoginStamp(user);
        if (latest != null) {
            LocalDateTime dateTime = LocalDateTime.ofInstant(latest, ZoneOffset.UTC);
            String dateString = FORMATTER.format(dateTime);
            String[] components = dateString.split(" ");
            
            return components[0] + ", kl. " + components[1];
        }
        return null;
    }
}
