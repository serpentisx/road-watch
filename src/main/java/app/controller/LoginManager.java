
package app.controller;

import app.service.AccountService;
import app.service.MailService;
import app.service.LoginEventService;
import app.service.PostService;
import app.service.VerifyUtils;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Handles login, logout and registering
 */
@Controller
@RequestMapping("")
public class LoginManager {
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    PostService postService;
    
    @Autowired
    MailService mailService;
       
    @Autowired
    LoginEventService loginEventService;

    /**
     * Renders login page
     *
     * @param model an object with attributes which can be used when rendering
     * @return      login page with login form
     */
    @RequestMapping(value = "/innskraning", method = RequestMethod.GET)
    public String login (ModelMap model) {
        model.addAttribute("formType", "login");
        return "login";
    }
    
    /**
     * Renders login page
     *
     * @param model an object with attributes which can be used when rendering
     * @return      login page with register form
     */
    @RequestMapping(value = "/nyskraning", method = RequestMethod.GET)
    public String renderRegisterPage (ModelMap model) {
        model.addAttribute("formType", "register");
        return "login";
    }
    
     /**
     * Handles user's login requests.
     * If the user enters wrong information he is redirected to the login page
     *
     * @param params  the user's log-in information
     * @param session maintains information regarding the currently logged in user
     * @param model   an object with attributes which can be used when rendering
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/innskraning", method = RequestMethod.POST)
    public String login (
        HttpSession session, @RequestParam Map<String,String> params, ModelMap model) {
        String email = params.get("login_email");
        String password = params.get("login_password");

        if (accountService.verifyPassword(email, password)) {
            session.setAttribute("user", email);
            session.setAttribute("username", accountService.findUsernameByEmail(email));
            model.addAttribute("posts", postService.getAllPosts());
            model.addAttribute("postsJSON", postService.generateDisplayPostsJSON(postService.getAllPosts(), email));
            model.addAttribute("user", (String) session.getAttribute("user"));
            model.addAttribute("username", (String) session.getAttribute("username"));
            loginEventService.createNewLoginEvent(email);
            return "index";
        } 
        else {
            model.addAttribute("invalid_input", "Rangt netfang eða lykilorð");
            return "login";
        }
    }
    
    /**
     * Fetches user's new account information and creates a new user
     * Redirects the user to login page and renders it
     *
     * @param params the user's new account information
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     */
    @RequestMapping(value = "/nyskraning", method = RequestMethod.POST)
    public String register (
        @RequestParam Map<String,String> params, ModelMap model) {
      
        String captchaResponse = params.get("g-recaptcha-response");
        
        boolean valid = VerifyUtils.verify(captchaResponse);

        if (!valid) {
            model.addAttribute("invalid_input", "Auðkenning tókst ekki.");
        } else {
            String name = params.get("register_name");
            String password = params.get("register_password");
            String email = params.get("register_email");

            boolean verification = accountService.verifyNewUser(email);
            if (!verification) {
                model.addAttribute("invalid_input", "Notandi er þegar til");
            } else if (accountService.createNewAccount(name, password, email)) {
                model.addAttribute("success_message", "Tókst að búa til notanda");
            } else {
                model.addAttribute("invalid_input", "Eitthvað fór úrskeiðis, vinsamlegast reyndu aftur.");
            }
        }
        return "login";
    }
    
    /**
     * Send email input to server side and send a new recovery password
     * to corresponding email
     *
     * @param email   email of account that user wants to recover
     * @param model
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/gleymt-lykilord", method = RequestMethod.POST)
    public String passwordRecovery (
      @RequestParam(value="submit_email") String email, ModelMap model
    ) {
        model.addAttribute("formType", "login");
        
        try {
            String pw = UUID.randomUUID().toString().replace("-", "");
            accountService.changePassword(email, pw);
            mailService.sendMail("vegavaktin@gmail.com", 
                                 email, 
                                 "Nýtt lykilorð " + " (" + email + ")", 
                                 "Nýja lykilorðið þitt er: " + pw + " \nBreyttu lykilorðinu strax við næstu innskráningu. \n\nBestu kveðjur, \nVegavaktin");
            
            model.addAttribute("success_message", "Nýtt lykilorð hefur verið sent þér í tölvupósti");

            return "login";
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            model.addAttribute("invalid_input", "Úúúps, eitthvað fór úrskeiðis. Reyndu aftur síðar.");
            return "login";
        }
    }

    /**
     * Handles log out requests. Renders home page.
     *
     * @param session maintains information regarding the currently logged in user
     * @param model   an object with attributes which can be used when rendering
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/utskra", method = RequestMethod.GET)
    public String logout (HttpSession session, ModelMap model) {
        session.setAttribute("user", null);
        model.addAttribute("username", null);
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("postsJSON", postService.generateDisplayPostsJSON(postService.getAllPosts(), null));
        return "index";
    }
    
    /**
     * Checks if a user is logged in
     * 
     * @param session
     * @return true if a user is logged in
     */
    @RequestMapping(value = "/isLoggedIn", method = RequestMethod.POST)
    public @ResponseBody
    boolean support(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
