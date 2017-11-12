package app.controller;

import app.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date   Last updated on 12 November 2017
 *
 * MailManager handles all e-mail interactions
 */
@Controller
public class MailManager {
    
    @Autowired
    MailService mailService;
    
    private static final String BUSINESS_EMAIL = "vegavaktin@gmail.com";
    private static final String SUCCESS_MESSAGE = //
            "Skilaboð þín hafa verið móttekin. Við munum hafa samband við þig eins fljótt og auðið er.";
    
    /**
     * Handles message-requests
     *
     * @param session : the user's current session
     * @param params  : the object data from post request
     * @param model   : the object data used when rendering the view
     * @return        : message-page to be rendered, displaying message indicating 
     *                  if the message was successfully dispatched or not
     */
    @RequestMapping(value = "/senda-post", method = RequestMethod.POST)
    public String sendEmail(HttpSession session, @RequestParam Map<String, 
      String> params, ModelMap model) {
        
        String name = params.get("contact-name");
        String email = params.get("contact-email");

        String subject = name + " (" + email + ")";
        String content = params.get("contact-message");

        mailService.sendMail(BUSINESS_EMAIL, BUSINESS_EMAIL, subject, content);
        model.addAttribute("message", SUCCESS_MESSAGE);
        
        return "message";
    }
}