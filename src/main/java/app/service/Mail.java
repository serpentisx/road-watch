package app.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Class for sending mail
 */
public class Mail {

    // The mail sender. Handles mail sending
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {

        this.mailSender = mailSender;
    }

    /**
     * Sends a mail
     *
     * @param from
     * @param to
     * @param subject
     * @param msg
     */
    public void sendMail(String from, String to, String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
