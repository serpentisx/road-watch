
package app.controller;

import app.model.Post;
import app.service.Mail;
import app.service.PostService;
import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * MainManager handles home-page get-requests 
 */
@Controller
@RequestMapping("") 
public class MainManager {

    @Autowired
    PostService service;
    /**
     * 
     * @param session  session maintaining logged in user
     * @param model    an object with attributes which can be used when rendering
     * @return         string representing page to be rendered
     */
    @RequestMapping("/")
    public String renderHomePage(HttpSession session, ModelMap model) {
        model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
        
        List<Post> posts = service.getAllPosts();
        String postsJSON = service.postsToJSON(posts);
        
        model.addAttribute("posts", posts);
        model.addAttribute("postsJSON", postsJSON);
        return "index";
    }
    
    // Testing purpose
    @RequestMapping("/settings")
    public String settings(HttpSession session, ModelMap model) {
        return "settings";
    }
    
    @RequestMapping(value = "/senda-post", method = RequestMethod.POST)
    public String sendEmail(HttpSession session, @RequestParam Map<String, String> params, ModelMap model) throws GeneralSecurityException {
        model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

        MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
        socketFactory.setTrustAllHosts(true);
        
        String name = params.get("contact-name");
        String email = params.get("contact-email");
        String message = params.get("contact-message");
   
        try {
            Mail mm = (Mail) context.getBean("mailMail");
            mm.sendMail("vegavaktin@gmail.com", "vegavaktin@gmail.com", name + " (" + email + ")", message);
            model.addAttribute("message", "Skilaboð þitt hefur verið móttekið. Við munum hafa samband eins fljótt og auðið er.");
            
            return "message";
        }
        catch (Exception e) {
            model.addAttribute("message", "Úúps, eitthvað fór úrskeiðis. Vinsamlega reyndu aftur síðar.");
            return "message";
        }
    }
}
