/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.service.AccountService;
import app.service.PostService;
import app.service.VerifyUtils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Huy Van Nguyen
 */
@Controller
@RequestMapping("")
public class LoginManager {
    
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
}
