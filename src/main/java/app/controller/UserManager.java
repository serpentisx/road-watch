
package app.controller;

import app.exceptions.HashException;
import app.exceptions.PasswordVerificationException;
import app.model.Post;
import app.service.AccountService;
import app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 13 November 2017
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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);

    /**
     * Handles user's request to delete account
     * Verifies whether the password entered by the user
     * matches an existing account, and deletes the account if so.
     * 
     * @param session   maintains the user's session
     * @param password  the password of the account to be deleted
     * @param model     an object with attributes which can be used when rendering
     * @return          string representing page to be rendered:
     *                    login-page if the attempt is successful, otherwise the account page
     * @throws          PasswordVerificationException 
     */
    @RequestMapping(value = "/reikningur/eyda-reikningi", method = RequestMethod.POST)
    private String deleteAccountHandler (
      HttpSession session, @RequestParam(value="password") String password, ModelMap model) 
      throws PasswordVerificationException {
        String loggedInUserEmail = (String) session.getAttribute("user");
        
        if (!accountService.verifyPassword(loggedInUserEmail, password)) {
            model.addAttribute("message", "Rangt lykilorð.");
        }
        
        if (accountService.deleteAccount(loggedInUserEmail)) {
            session.setAttribute("user", null);
            model.addAttribute("success_message", "Aðgangi þínum hefur verið eytt.");
            return "login";
            
        } else {
            model.addAttribute("message", "Ekki tókst að eyða reikningnum þínum, reyndu aftur síðar.");
        }
        return "settings";
    }
    
    /**
     * Handles user's request to change password
     * Verifies whether the password entered by the user
     * matches an existing account, and changes the account password if so.
     *
     * @param session maintains the user's session
     * @param params  user input parameters
     * @param model   an object with attributes which can be used when rendering
     * @return        string representing page to be rendered
     *                front page if the attempt is successful, otherwise the account page
     */
    @RequestMapping(value = "/reikningur/breyta-lykilordi", method = RequestMethod.POST)
    private String changePasswordHandler (
      HttpSession session, @RequestParam Map<String,String> params, ModelMap model) 
      throws PasswordVerificationException, HashException {
        String oldPassword = params.get("old-password");
        String newPassword1 = params.get("new-password1");
        String newPassword2 = params.get("new-password2");
        
        String loggedInUserEmail = (String) session.getAttribute("user");
        
        if (!newPassword1.equals(newPassword2)) {
            model.addAttribute("message", "Ný lykilorð eru ekki eins, reyndu aftur.");
        }
        else if (!accountService.verifyPassword(loggedInUserEmail, oldPassword)) {
            model.addAttribute("message", "Lykilorð er ekki rétt, reyndu aftur.");
        }
        else if (accountService.changePassword(loggedInUserEmail, newPassword1)) {
            model.addAttribute("message", "Lykilorði þínu hefur verið breytt.");
            
        } else {
            model.addAttribute("message", "Úúúps! Eitthvað fór úrskeiðis. Reyndu aftur síðar.");
        }
        
        return "settings";
    }
    
    /**
     * Handles user's request to change username
     * 
     * @param session   maintains the user's session
     * @param username  new username
     * @param model     an object with attributes which can be used when rendering
     * @return          string representing page to be rendered
     */
    @RequestMapping(value = "/reikningur/breyta-nafni", method = RequestMethod.POST)
    private String changeUsernameHandler (
      HttpSession session, @RequestParam(value="username") String username, ModelMap model) {
     
        String email = (String) session.getAttribute("user");
        accountService.changeName(email, username);
        session.setAttribute("username", username);
        model.addAttribute("message", "Nafni þínu hefur verið breytt!");
        return "settings";
    }
    
    /**
     * Responds to post deletion user requests
     * 
     * @param postId    the id of the post to be deleted
     * @param session   maintains the user's session
     * @return          true if postId matches some post
     */
    @RequestMapping(value = "/delete-post", method = RequestMethod.POST)
    public @ResponseBody
    boolean deletePostHandler(@RequestBody int postId, HttpSession session) {
        return postService.deletePost(postId);
    }
    
    /**
     * Responds to post archive user requests
     * 
     * @param postId    the id of the post to be archived
     * @param session   maintains the user's session
     * @return          true if postId matches some post
     */
    @RequestMapping(value = "/archivePost", method = RequestMethod.POST)
    public @ResponseBody
    boolean archivePostHandler(@RequestBody String postId, HttpSession session) {
        return postService.toggleArchivePost(Integer.parseInt(postId));
    }
    
    /**
     * Password-processing exception handler
     * 
     * @param req     http-request
     * @param e       the exception
     * @param model   model used for rendering
     * @return        page to be rendered
     */
    @ExceptionHandler ({HashException.class, PasswordVerificationException.class})
    public String handleError(HttpServletRequest req,
            Exception e, Model model) {
        
        model.addAttribute("message", "Einhver villa átti sér stað, reyndu aftur síðar.");
        LOGGER.error("Path: " + req.getRequestURL());
        LOGGER.error("Exception: " + e.getMessage() + " Caused by: " + e.getCause().toString());
        return "settings";
    }
}
