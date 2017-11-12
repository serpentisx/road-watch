
package app.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 11 November 2017
 *
 * Sends e-mail
 */
public class Mailer {

    // The mail-sender, dispatches e-mails
    private MailSender dispatcher;
    
    public void setMailSender(MailSender dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Sends an e-mail
     *
     * @param from    sender
     * @param to      receiver
     * @param subject e-mail message subject
     * @param msg     e-mail message content
     * @throws org.springframework.mail.MailException
     */
    public void sendMail(String from, String to, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        
        this.dispatcher.send(message);
    }
}
