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
 * @author Team 20 HBV501G - Fall 2017
 *
 * MainController listens to requests at defined routes and is responsible for
 * fetching and processing data as well as rendering pages.
 */

// Request Mapping for the root
// Every sub-pages in this method will be mounted to the root, i.e. at /
@Controller
@RequestMapping("") 
public class MainController {

    // Creates a connection (autowire)to user's service class
    @Autowired
    UserService userService;

    // Default homepage
    // @return String filename: filename is supposed to point to a .jsp file
    @RequestMapping("/")
    public String main_page(){
        return "login_register_page";
    }

    // Page for all posts
    // @return String filename: filename is supposed to point to a .jsp file
    @RequestMapping("/posts")
    public String posts_page(){
        return "posts_page";
    }

    /**
    * Fetches user's login information and renders posts page if the user exists
    * If the user does not exist a login page will be rendered
    * 
    * @param Map<String, String> params: the user's login information
    * @param ModelMap model: an object with attributes which can be used when rendering
    * @return String filename: posts page or login page (.jsp file to be rendered)
    */
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

    /**
    * Fetches user's login information and creates a new user
    * Redirects the user to login page and renders it
    * 
    * @param Map<String, String> params: the user's login information
    * @param ModelMap model: an object with attributes which can be used when rendering
    * @return String filename: login page to be rendered
    */
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
    
    /**
    * Fetches user's input and creates a new post
    * Renders posts page with respectively post
    * 
    * @param Map<String, String> params: the user's input
    * @param ModelMap model: an object with attributes which can be used when rendering
    * @return String filename: login page to be rendered
    */
    @RequestMapping(value = "/new-entry", method = RequestMethod.POST)
    public String newEntry(@RequestParam Map<String,String> params, ModelMap model){
        String title = params.get("title");
        String description = params.get("description");

        Post post = new Post(title, null, null, description, null);

        model.addAttribute("post", post);
        return "posts_page";
    }
}
