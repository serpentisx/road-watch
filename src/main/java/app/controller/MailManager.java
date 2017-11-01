package app.controller;

import app.service.Mail;
import app.service.MailService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * MailManager handles all mail interactions on the website
 */
@Controller
public class MailManager {
    
    @Autowired
    MailService mailService;

    /**
     * Sends mail
     *
     * @param session : the user's current session
     * @param params  : the object data from post request
     * @param model   : the object data used when rendering the view
     * @return        : message page with message indicating if the mail was successfully sent or not
     */
    @RequestMapping(value = "/senda-post", method = RequestMethod.POST)
    public String sendEmail(HttpSession session, @RequestParam Map<String, String> params, ModelMap model) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

        String name = params.get("contact-name");
        String email = params.get("contact-email");
        String message = params.get("contact-message");

        try {
            mailService.sendMail("vegavaktin@gmail.com", "vegavaktin@gmail.com", name + " (" + email + ")", message);
            model.addAttribute("message", "Skilaboð þitt hefur verið móttekið. Við munum hafa samband eins fljótt og auðið er.");

            return "message";
        }
        catch (Exception e) {
            model.addAttribute("message", "Úúps, eitthvað fór úrskeiðis. Vinsamlega reyndu aftur síðar.");
            return "message";
        }
    }
}