
package app.service;

import org.springframework.beans.BeansException;
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
        } catch (BeansException e) {
            // Catches beancreation runtime exception when running locally
            // because the bean for the mail service uses environment variables
            // not set locally. This could be fixed later.
            // In that case we do nothing
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
