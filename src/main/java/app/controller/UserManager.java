
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
        HttpSession session, @RequestParam(value="password") String password, ModelMap model) {
        String loggedInUserEmail = (String) session.getAttribute("user");
        
        if (!accountService.verifyPassword(loggedInUserEmail, password)) {
            model.addAttribute("message", "Rangt lykilroð");
            return "settings";
        }
        
        if (accountService.deleteAccount(loggedInUserEmail)) {
            session.setAttribute("user", null);
            model.addAttribute("success_message", "Aðgangi þínum hefur verið eytt");
            return "login";
        }
        else {
            model.addAttribute("message", "Því miður tókst ekki að eyða reikningnum þínum, reyndu aftur síðar.");
            return "settings";
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
        String oldPassword = params.get("old-password");
        String newPassword1 = params.get("new-password1");
        String newPassword2 = params.get("new-password2");

        String loggedInUserEmail = (String) session.getAttribute("user");
        
        if (!newPassword1.equals(newPassword2)) {
            model.addAttribute("message", "Nýja lykilorðið eru ekki eins, reyndu aftur.");
            return "settings";
        }
        if (!accountService.verifyPassword(loggedInUserEmail, oldPassword)) {
            model.addAttribute("message", "Lykilorð er ekki rétt, reyndu aftur.");
            return "settings";
        }
        if (accountService.changePassword(loggedInUserEmail, newPassword1)) {
            model.addAttribute("message", "Lykilorðið þitt hefur verið breytt");
            return "settings";
        }
        else {
            model.addAttribute("message", "Úúps! Eitthvað fór úrskeiðis. Reyndu aftur síðar.");
            return "settings";
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
        HttpSession session, @RequestParam(value="username") String username, ModelMap model) {
     
        String email = (String) session.getAttribute("user");
        accountService.changeName(email, username);
        session.setAttribute("username", accountService.findUsernameByEmail(email));
        model.addAttribute("message", "Nafni þínu hefur verið breytt!");
        return "settings";
    }
}
