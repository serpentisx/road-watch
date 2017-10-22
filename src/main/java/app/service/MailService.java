/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy Van Nguyen
 */

@Service
public class MailService {
    
    private Mail mail;
    
    public MailService() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
            MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
            this.mail = (Mail) context.getBean("mailMail");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
        mail.sendMail(from, to, subject, msg);
    }
}
