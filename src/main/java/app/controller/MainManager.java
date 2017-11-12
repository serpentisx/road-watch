
package app.controller;

import app.model.Post;
import app.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 12 November 2017
 *
 * MainManager handles home-page get-requests
 */
@Controller
@RequestMapping("") 
public class MainManager {

    @Autowired
    PostService service;
    
    /**
     * Renders home page with posts.
     *
     * @param session  : the user's current session
     * @param model    : the object data used when rendering the view
     * @return         : home page
     */
    @RequestMapping("/")
    public String renderHomePage(HttpSession session, ModelMap model) {
        String userEmail = (String) session.getAttribute("user");
        model.addAttribute("user", userEmail);
        
        List<Post> posts = service.getAllPosts();
        String postsJSON = service.getAllPostsJSON(userEmail);
        
        model.addAttribute("posts", posts);
        model.addAttribute("postsJSON", postsJSON);
        
        return "index";
    }

    /**
     * Renders My page where logged in users can edit and view his account and posts.
     * Login page is rendered instead if the user is not logged in.
     *
     * @param session : the user's current session
     * @param model   : the object data used when rendering the view
     * @return        : "my pages" (settings) if logged in, else returns login page
     */
    @RequestMapping(value = "/minar-sidur", method = RequestMethod.GET)
    public String settings (HttpSession session, ModelMap model) {
        Object user = session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", (String) user);
        model.addAttribute("username", (String) session.getAttribute("username"));
        
        return "settings";
    }
}
