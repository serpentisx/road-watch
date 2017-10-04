
package app.controller;

import app.model.Post;
import app.service.PostService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * MainManager handles home page get-requests 
 */
@Controller
@RequestMapping("") 
public class MainManager {

    @Autowired
    PostService service;
   /**
    * 
    * @param model  an object with attributes which can be used when rendering
    * @return       string representing page to be rendered
    */
    @RequestMapping("/")
    public String renderHomePage(ModelMap model){
        ArrayList<Post> posts;
        posts = (ArrayList<Post>) service.getAllPosts();
        
        System.out.println("posts variable declared");
        
        model.addAttribute("posts", posts);
        
        System.out.println("MAIN MANAGER RENDER HOME PAGE");
        return "index";
    }
}
