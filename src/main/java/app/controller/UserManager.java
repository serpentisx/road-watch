
package app.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.service.AccountService;
import app.service.PostService;

/**
 *
 * @author Bjarki Viðar Kristjánsson, bvk1
 * @author Valentin Oliver Loftsson, vol1
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
    
    // provisional resort while we haven't figured out how to save the user's session
    String provisionalEmail = "notandi@hi.is";

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
        
        model.addAttribute("username", accountService.getLoggedInUserName());
        
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
     * @param model an object with attributes which can be used when rendering
     * @return      string representing page to be rendered
     */
    @RequestMapping(value = "/utskra", method = RequestMethod.GET)
    public String logout (
        ModelMap model) {
        accountService.logoutUser();
        model.addAttribute("username", null);
        return "index";
    }
   
    /**
     * Handles user's login requests.
     * If the user enters wrong information he is redirected to the login page
     *
     * @param params the user's log-in information
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur", method = RequestMethod.POST)
    public String login (
        @RequestParam Map<String,String> params, ModelMap model) {
        String email = params.get("login_email");
        String password = params.get("login_password");
        
        model.addAttribute("username", accountService.getLoggedInUserName());

        boolean verification = accountService.loginUser(email, password);
        
        if (verification) {
            model.addAttribute("posts", postService.getAllPosts());
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
     * @param model an object with attributes which can be used when rendering
     * @return      string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/eyda-reikningi", method = RequestMethod.GET)
    public String renderAccountDeletion (
        ModelMap model) {
        model.addAttribute("form_switch", "delete");
        model.addAttribute("username", accountService.getLoggedInUserName());
        return "account";
    }
    
    /**
     * Renders account modification page for changing an account's password
     *
     * @param model an object with attributes which can be used when rendering
     * @return      string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/breyta-lykilordi", method = RequestMethod.GET)
    public String renderPasswordChange (
        ModelMap model) {
        model.addAttribute("form_switch", "password");
        model.addAttribute("username", accountService.getLoggedInUserName());                
        return "account";
    }
    
     /**
     * Renders account modification page for changing an account's username
     *
     * @param model an object with attributes which can be used when rendering
     * @return      string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/breyta-nafni", method = RequestMethod.GET)
    public String renderUsernameChange (
        ModelMap model) {
        model.addAttribute("form_switch", "username");
        model.addAttribute("username", accountService.getLoggedInUserName());
        return "account";
    }
    
    /**
     * Handles user's request to delete account
     * 
     * @param params the user's log-in information
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/eyda-reikningi", method = RequestMethod.POST)
    private String deleteAccountHandler (
        @RequestParam Map<String,String> params, ModelMap model) {
        String password = params.get("password");

        if (accountService.verifyPassword(provisionalEmail, password)) {
          model.addAttribute("username", accountService.getLoggedInUserName());
          model.addAttribute("message", "Lykilorðið er rangt.");
          model.addAttribute("form_switch", "delete");
          return "account";
        } else {
          // vantar að ná í email núverandi notanda session
          accountService.deleteAccount(provisionalEmail);
          model.addAttribute("username", null);
          model.addAttribute("success_message", "Aðgangi þínum hefur verið eytt");
          return "login";
        }
    }
    
    /**
     * Handles user's request to change password
     * 
     * @param params the user's log-in information
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/breyta-lykilordi", method = RequestMethod.POST)
    private String changePasswordHandler (
        @RequestParam Map<String,String> params, ModelMap model) {
        String oldPassword = params.get("old_password");
        String newPassword1 = params.get("new_password_1");
        String newPassword2 = params.get("new_password_2");
        model.addAttribute("username", accountService.getLoggedInUserName());
        
        if (!newPassword1.equals(newPassword2)) {
            model.addAttribute("message", "Nýja lykilorðið eru ekki eins, reyndu aftur.");
            model.addAttribute("form_switch", "password");
            return "account";
        } else if (accountService.verifyPassword(provisionalEmail, oldPassword)) {
            // vantar að sækja email núverandi notanda 
            accountService.changePassword(provisionalEmail, newPassword1);
            model.addAttribute("posts", postService.getAllPosts());
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
     * @param params the user's log-in information
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/breyta-nafni", method = RequestMethod.POST)
    private String changeUsernameHandler (
        @RequestParam Map<String,String> params, ModelMap model) {
        model.addAttribute("username", accountService.getLoggedInUserName());
        String newUsername = params.get("username");
        accountService.changeName(provisionalEmail, newUsername);
        return "index";
    }
}
