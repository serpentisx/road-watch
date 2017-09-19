/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.Post;
import app.model.User;
import app.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * 
 * MainController defines the routes at the root of the website and the actions 
 * to be performed at respectively routes.
 * 
 */

@Controller
@RequestMapping("") // Request Mapping for the root
public class MainController {
    
    @Autowired
    UserService userService;
    
    // Route for home page 
    // @return String filePath.  filePath is supposed to point to a .jsp file.
    @RequestMapping("/")
    public String main_page(){
        return "login_register_page";
    }
    
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String login(
        @RequestParam Map<String,String> params, ModelMap model) {
        String name = params.get("login_name");
        String password = params.get("login_password");

        if (userService.validateUser(name, password)) {
            return "posts_page";
        }
        else {
            model.addAttribute("invalid_input", "Rangt notandanafn eða lykilorð");
            return "login_register_page";
        }
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register (
        @RequestParam Map<String,String> params, ModelMap model) {
        String name = params.get("register_name");
        String password = params.get("register_password");
        String email = params.get("register_email");
 
        User user = new User(name, password, email);
        userService.addUser(user);

        return "login_register_page";
    }
    
    
    @RequestMapping(value = "/new-entry", method = RequestMethod.POST)
    public String newentry(@RequestParam Map<String,String> params, ModelMap model){
        String title = params.get("title");
        String description = params.get("description");
        
        Post post = new Post(title, null, null, description, null);
        
        model.addAttribute("post", post);
        return "post_page";
    }
}
