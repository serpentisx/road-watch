
package app.controller;

import app.service.AccountService;
import app.service.PostService;
import app.service.VerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Listens to requests at defined routes related to user account requests
 * and is responsible for fetching and processing data as well as rendering pages.
 */
@Controller
@RequestMapping("")
public class UserManager {
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    PostService postService;

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
              // vantar: halda register-formi opnu
          } else if (accountService.createNewAccount(name, password, email)) {
              model.addAttribute("success_message", "Tókst að búa til notanda");
          } else {
              model.addAttribute("invalid_input", "Eitthvað fór úrskeiðis, vinsamlegast reyndu aftur.");
              // vantar: halda register-formi opnu
          }
        }

        return "login";
    }
    
    /**
     * Renders login page
     *
     * @return      string representing page to be rendered
     */
    @RequestMapping(value = "/innskraning", method = RequestMethod.GET)
    public String login () {
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
        session.setAttribute("loggedInUser", null);
        model.addAttribute("username", null);
        model.addAttribute("posts", postService.getAllPosts());
        return "index";
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
    @RequestMapping(value = "/reikningur", method = RequestMethod.POST)
    public String login (
        HttpSession session, @RequestParam Map<String,String> params, ModelMap model) {
        String email = params.get("login_email");
        String password = params.get("login_password");
        
        if (accountService.verifyPassword(email, password)) {
            session.setAttribute("loggedInUserEmail", email);
            session.setAttribute("loggedInUsername", accountService.findUsernameByEmail(email));
            model.addAttribute("posts", postService.getAllPosts());
            model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
            return "index";
        } 
        else {
            model.addAttribute("invalid_input", "Rangt netfang eða lykilorð");
            return "login";
        }
    }

    /**
     * Renders account modification page for deleting an account
     *
     * @param model   an object with attributes which can be used when rendering
     * @param session maintains information regarding the currently logged in user
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/eyda-reikningi", method = RequestMethod.GET)
    public String renderAccountDeletion (
        HttpSession session, ModelMap model) {
        model.addAttribute("form_switch", "delete");
        model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
        return "account";
    }
    
    /**
     * Renders account modification page for changing an account's password
     *
     * @param model   an object with attributes which can be used when rendering
     * @param session maintains information regarding the currently logged in user
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/breyta-lykilordi", method = RequestMethod.GET)
    public String renderPasswordChange (
        HttpSession session, ModelMap model) {
        model.addAttribute("form_switch", "password");
        model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
        return "account";
    }
    
     /**
     * Renders account modification page for changing an account's username
     *
     * @param model   an object with attributes which can be used when rendering
     * @param session maintains information regarding the currently logged in user
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/breyta-nafni", method = RequestMethod.GET)
    public String renderUsernameChange (
        HttpSession session, ModelMap model) {
        model.addAttribute("form_switch", "username");
        model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
        return "account";
    }
    
    /**
     * Handles user's request to delete account
     * Verifies whether the password entered by the user
     * matches an existing account, and deletes the account if so.
     * 
     * @param params user input parameters
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered:
     *               login-page if the attempt is successful, otherwise the account page
     */
    @RequestMapping(value = "/reikningur/eyda-reikningi", method = RequestMethod.POST)
    private String deleteAccountHandler (
        HttpSession session, @RequestParam Map<String,String> params, ModelMap model) {
        String password = params.get("password");
        String loggedInUserEmail = (String) session.getAttribute("loggedInUserEmail");
        System.out.println(loggedInUserEmail);
        if (!accountService.verifyPassword(loggedInUserEmail, password)) {
          model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
          model.addAttribute("message", "Lykilorðið er rangt.");
          model.addAttribute("form_switch", "delete");
          return "account";
        } else {
          boolean successful = accountService.deleteAccount(loggedInUserEmail);
          if (successful) {
            model.addAttribute("username", null);
            model.addAttribute("success_message", "Aðgangi þínum hefur verið eytt");
            return "login";
          } else {
            model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
            model.addAttribute("message", "Því miður tókst ekki að eyða reikningnum þínum, reyndu aftur síðar.");
            model.addAttribute("form_switch", "delete");
            return "account";
          }
        }
    }

    
    /**
     * Handles user's request to change password
     * Verifies whether the password entered by the user
     * matches an existing account, and changes the account password if so.
     * 
     * @param params user input parameters
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     *               front page if the attempt is successful, otherwise the account page
     */
    @RequestMapping(value = "/reikningur/breyta-lykilordi", method = RequestMethod.POST)
    private String changePasswordHandler (
        HttpSession session, @RequestParam Map<String,String> params, ModelMap model) {
        String oldPassword = params.get("old_password");
        String newPassword1 = params.get("new_password_1");
        String newPassword2 = params.get("new_password_2");
        model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));

        String loggedInUserEmail = (String) session.getAttribute("loggedInUserEmail");
        if (!newPassword1.equals(newPassword2)) {
            model.addAttribute("message", "Nýja lykilorðið eru ekki eins, reyndu aftur.");
            model.addAttribute("form_switch", "password");
            return "account";
        } else if (accountService.verifyPassword(loggedInUserEmail, oldPassword)) {
            boolean b  = accountService.changePassword(loggedInUserEmail, newPassword1);
            model.addAttribute("posts", postService.getAllPosts());
            model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
            return "index";
        } else {
            model.addAttribute("message", "Lykilorð er ekki rétt, reyndu aftur.");
            model.addAttribute("form_switch", "password");
            return "account";
        }
    }

    
    /**
     * Handles user's request to change username
     * 
     * @param params user input parameters
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered, always redirects to front-page
     */
    @RequestMapping(value = "/reikningur/breyta-nafni", method = RequestMethod.POST)
    private String changeUsernameHandler (
        HttpSession session, @RequestParam Map<String,String> params, ModelMap model) {
        model.addAttribute("username", session.getAttribute("loggedInUsername"));
        String newUsername = params.get("username");
        accountService.changeName((String) session.getAttribute("loggedInUserEmail"), newUsername);
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
        return "index";
    }
}
