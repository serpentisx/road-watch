
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

   /**
    * Fetches user's login information and creates a new user
    * Redirects the user to login page and renders it
    *
    * @param params the user's login information
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
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login (ModelMap model) {
        return "login";
    }
   
   /**
    * Fetches user's login information and renders posts page if the user exists
    * If the user does not exist a login page will be rendered
    *
    * @param params the user's new account information
    * @param model  an object with attributes which can be used when rendering
    * @return       string representing page to be rendered
    **/
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
    * Handles account modification.
    * Determines which modification user has selected
    * and renders account modification page accordingly.
    *
    * @param param  name of button that user has clicked
    * @param model  an object with attributes which can be used when rendering
    * @return       string representing page to be rendered
    */
    /*
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String modifyAccount (
      @RequestParam String param, ModelMap model) {
        model.addAttribute("form_switch", param);
        return "account";
    }
    */

    @RequestMapping(value = "/account/delete-account", method = RequestMethod.GET)
    public String renderAccountDeletion (
      ModelMap model) {
        model.addAttribute("form_switch", "delete");
        return "account";
    }

    @RequestMapping(value = "/account/change-password", method = RequestMethod.GET)
    public String renderPasswordChange (
      ModelMap model) {
        model.addAttribute("form_switch", "password");
        return "account";
    }

    @RequestMapping(value = "/account/change-username", method = RequestMethod.GET)
    public String renderUsernameChange (
      ModelMap model) {
        model.addAttribute("form_switch", "username");
        return "account";
    }

    
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

        if (service.verifyPassword("a@a.is", password)) {
          model.addAttribute("message", "Lykilorðið er rangt.");
          model.addAttribute("form_switch", "delete");
          return "account";
        } else {
          service.deleteAccount("a@a.is"); //email úr sessioni
          model.addAttribute("success_message", "Aðgangi þínum hefur verið eytt");
          return "index";
        }
    }

    private String changePasswordHandler (Map<String,String> params, ModelMap model) {
        /* Notandi setur inn gamalt lykilorð og nýtt lykilorð,
           staðfestum hvort upplýsingar séu réttar */
        return "index";
    }

    private String changeUsernameHandler (Map<String,String> params, ModelMap model) {
        /* Notandi setur inn password tvisvar og nýtt username,
           staðfestum hvort upplýsingar séu réttar */
        return "index";
    }
}
