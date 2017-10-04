
package app.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.service.AccountService;

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
    AccountService service;
    
    // provisional resort while we haven't figured out how to save the user's session
    String provisionalEmail = "notandi@hi.is";

    /**
     * Fetches user's login information and creates a new user
     * Redirects the user to login page and renders it
     *
     * @param params the user's new account information
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register (
        @RequestParam Map<String,String> params, ModelMap model) {
        String name = params.get("register_name");
        String password = params.get("register_password");
        String email = params.get("register_email");

        boolean verification = service.verifyNewUser(email);
        if (!verification) {
            model.addAttribute("invalid_input", "Notandi er þegar til");
            // vantar: halda register-formi opnu
        } 
        else {
            if (service.createNewAccount(name, password, email)) {
                model.addAttribute("success_message", "Tókst að búa til notanda");
            }
            else {
                model.addAttribute("invalid_input", "Úups! Eitthvað fór úrskeiðis. Vinsamlegast reyndu aftur.");
            }
        }

        return "login";
    }
    
    /**
     * Renders login page
     *
     * @return      string representing page to be rendered
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login () {
        return "login";
    }
   
    /**
     * Fetches user's login information and renders posts page if the user exists
     * If the user does not exist a login page will be rendered
     *
     * @param params the user's log-in information
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login (
        @RequestParam Map<String,String> params, ModelMap model) {
        String email = params.get("login_email");
        String password = params.get("login_password");

        boolean verification = service.verifyPassword(email, password);
        if (verification) {
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
    @RequestMapping(value = "/account/delete-account", method = RequestMethod.GET)
    public String renderAccountDeletion (
      ModelMap model) {
        model.addAttribute("form_switch", "delete");
        return "account";
    }
    
    /**
     * Renders account modification page for changing an account's password
     *
     * @param model an object with attributes which can be used when rendering
     * @return      string representing page to be rendered
     */
    @RequestMapping(value = "/account/change-password", method = RequestMethod.GET)
    public String renderPasswordChange (
      ModelMap model) {
        model.addAttribute("form_switch", "password");
        return "account";
    }
    
     /**
     * Renders account modification page for changing an account's username
     *
     * @param model an object with attributes which can be used when rendering
     * @return      string representing page to be rendered
     */
    @RequestMapping(value = "/account/change-username", method = RequestMethod.GET)
    public String renderUsernameChange (
      ModelMap model) {
        model.addAttribute("form_switch", "username");
        return "account";
    }

    /**
     * Handles account modification.
     * Determines which type of modification user has selected
     * and renders the proper page depending on form data processing results.
     *
     * @param params  name of button that user has clicked
     * @param model  an object with attributes which can be used when rendering
     * @return       string representing page to be rendered
     */
    @RequestMapping(value = "/account/modify", method = RequestMethod.POST)
    public String modifyAccount (
      @RequestParam Map<String,String> params, ModelMap model) {
        String submitButton = params.get("submit-button");
        String view;
        if (submitButton.equals("delete-account")) {
          view = deleteAccountHandler(params, model);
        } else if (submitButton.equals("change-password")) {
          view = changePasswordHandler(params, model);
        } else if (submitButton.equals("change-username")) {
          view = changeUsernameHandler(params, model);
        } else {
          view = "login";
        }
        return view;
    }

    private String deleteAccountHandler (Map<String,String> params, ModelMap model) {
        String password = params.get("password");

        if (service.verifyPassword(provisionalEmail, password)) {
          model.addAttribute("message", "Lykilorðið er rangt.");
          model.addAttribute("form_switch", "delete");
          return "account";
        } else {
          service.deleteAccount(provisionalEmail); // vantar að ná í email úr session
          model.addAttribute("success_message", "Aðgangi þínum hefur verið eytt");
          return "index";
        }
    }
    
    /* 
     * Notandi setur inn gamalt lykilorð og nýtt lykilorð,
     * staðfestum hvort upplýsingar séu réttar 
     */
    private String changePasswordHandler (Map<String,String> params, ModelMap model) {        
        String oldPassword = params.get("old_password");
        String newPassword1 = params.get("new_password_1");
        String newPassword2 = params.get("new_password_2");
        
        if (!newPassword1.equals(newPassword2)) {
          model.addAttribute("message", "Nýja lykilorðið eru ekki eins, reyndu aftur.");
          model.addAttribute("form_switch", "password");
          return "account";
        } else {
            // vantar að sækja email núverandi notanda 
            if (service.verifyPassword(provisionalEmail, oldPassword)){
                System.out.println(oldPassword);
                service.changePassword(provisionalEmail, newPassword1);
                System.out.println(service.verifyPassword(provisionalEmail, newPassword1));
                System.out.print("password was changed");
                return "main";
            } else {
                model.addAttribute("message", "Lykilorð er ekki rétt, reyndu aftur.");
                model.addAttribute("form_switch", "password");
                return "account";
            }
        }
    }
    
    /* 
     * Breytir notandanafni notanda.
     */
    private String changeUsernameHandler (Map<String,String> params, ModelMap model) {
        String newUsername = params.get("username");
        service.changeName(provisionalEmail, newUsername);
        return "index";
    }
}
