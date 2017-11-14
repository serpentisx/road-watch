
package app.service;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 11 November 2017
 * 
 * Intermediate for mail-dispatching services
 */
@Service
public class MailService {
    
    private Mailer mailer; // handles e-mailing
    
    public MailService() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
            this.mailer = (Mailer) context.getBean("mailer");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sends an e-mail
     *
     * @param from      sender
     * @param to        receiver
     * @param subject   e-mail message subject
     * @param content   e-mail message content
     */
    public void sendMail(String from, String to, String subject, String content) {
        mailer.sendMail(from, to, subject, content);
    }
}
