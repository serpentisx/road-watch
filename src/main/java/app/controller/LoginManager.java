
package app.controller;

import app.exceptions.HashException;
import app.exceptions.PasswordVerificationException;
import app.service.AccountService;
import app.service.MailService;
import app.service.LoginEventService;
import app.service.PostService;
import app.service.RecaptchaVerifier;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
 * @date Last updated on 12 November 2017
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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginManager.class);
    
    private static final String BUSINESS_EMAIL = "vegavaktin@gmail.com";

    /**
     * Renders login page with the login form
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
     * Renders login page with the registration form
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
     * Handles user's login requests;
     * If the user enters wrong information he is redirected to the login page
     *
     * @param params  the user's log-in information
     * @param session maintains information regarding the currently logged in user
     * @param model   an object with attributes which can be used when rendering
     * @return        string representing page to be rendered
     * @throws app.exceptions.PasswordVerificationException
     */
    @RequestMapping(value = "/innskraning", method = RequestMethod.POST)
    public String login (
      HttpSession session, @RequestParam Map<String,String> params, ModelMap model)
      throws PasswordVerificationException {
        String email = params.get("login_email");
        String password = params.get("login_password");

        if (accountService.verifyPassword(email, password)) {
            session.setAttribute("user", email);
            session.setAttribute("username", accountService.findUsernameByEmail(email));
            
            // model.addAttribute("posts", postService.getAllPosts());
            // model.addAttribute("postsJSON", postService.getAllPostsJSON(email));
            // model.addAttribute("user", (String) session.getAttribute("user"));
            // model.addAttribute("username", (String) session.getAttribute("username"));
            
            loginEventService.createNewLoginEvent(email);
            return "index";
            
        } else {
            model.addAttribute("error_message", "Rangt netfang eða lykilorð.");
            return "login";
        }
    }
    
    /**
     * Fetches user's new account information and creates a new user;
     * Redirects the user to login page and renders it
     *
     * @param params  the user's new account information
     * @param model   an object with attributes which can be used when rendering
     * @return        string representing page to be rendered
     * @throws        app.exceptions.HashException
     */
    @RequestMapping(value = "/nyskraning", method = RequestMethod.POST)
    public String register (
        @RequestParam Map<String,String> params, ModelMap model) throws HashException {
      
        String captchaResponse = params.get("g-recaptcha-response");
        
        boolean captcha;
        try {
            captcha = (new RecaptchaVerifier()).verify(captchaResponse);
            
        } catch (IOException e) {
            model.addAttribute("error_message", "Vefþjónn skilaði villu, reyndu aftur síðar.");
            return "login";
        }
        
        if (!captcha) {
            model.addAttribute("error_message", "Auðkenning bar ekki árangur.");
            
        } else {
            String name = params.get("register_name");
            String password = params.get("register_password");
            String email = params.get("register_email");

            boolean verification = accountService.verifyNewUser(email);
            if (!verification) {
                model.addAttribute("error_message", "Notandi er þegar til.");
                
            } else {
                accountService.createNewAccount(name, password, email);
                model.addAttribute("success_message", "Tókst að búa til nýjan notanda.");
                model.addAttribute("formType", "login");
            }
        }        
        return "login";
    }
    
    /**
     * Send email input to server side and send a new recovery password
     * to corresponding email
     *
     * @param email   email of account that user wants to recover
     * @param model   an object with attributes which can be used when rendering
     * @return        string representing page to be rendered
     * @throws        app.exceptions.HashException
     */
    @RequestMapping(value = "/gleymt-lykilord", method = RequestMethod.POST)
    public String passwordRecovery (
      @RequestParam(value="submit_email") String email, ModelMap model
    ) throws HashException {
        model.addAttribute("formType", "login");
        
        String newPassword = UUID.randomUUID().toString().replace("-", "");
        
        accountService.changePassword(email, newPassword);
        mailService.sendMail(BUSINESS_EMAIL, 
                             email, 
                             "Nýtt lykilorð " + " (" + email + ")", 
                             "Nýja lykilorðið þitt er: " + newPassword + " \nBreyttu lykilorðinu strax við næstu innskráningu. \n\nBestu kveðjur, \nVegavaktin");

        model.addAttribute("success_message", "Þér hefur verið sent nýtt lykilorð í tölvupósti.");
        
        return "login";
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
        
        // model.addAttribute("posts", postService.getAllPosts());
        // model.addAttribute("postsJSON", postService.getAllPostsJSON(null));
        return "index";
    }
    
    /**
     * Password-processing exception handler
     * 
     * @param req   http-request
     * @param e     the exception
     * @param model model used for rendering
     * @return 
     */
    @ExceptionHandler ({HashException.class, PasswordVerificationException.class})
    public String handleError(HttpServletRequest req,
            Exception e, ModelMap model) {
        
        model.addAttribute("error_message", "Einhver villa átti sér stað, reyndu aftur síðar.");
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Exception: " + e.getMessage() + " Caused by: " + e.getCause().toString());
        return "login";
    }
}
