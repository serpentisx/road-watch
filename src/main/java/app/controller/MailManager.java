package app.controller;

import app.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param params  : the object data from post request
     * @return        : true of mail was successfully sent, else false
     */
    @RequestMapping(value = "/senda-post", method = RequestMethod.POST)
    @ResponseBody
    public boolean sendEmail(@RequestBody Map<String, String> params) {
        try {
            String email = (String) params.get("email");
            String name = (String) params.get("name");
            String content = (String) params.get("content");
            String subject = name + " (" + email + ")";
            
            mailService.sendMail(BUSINESS_EMAIL, BUSINESS_EMAIL, subject, content);
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}