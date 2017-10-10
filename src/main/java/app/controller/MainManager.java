
package app.controller;

import app.service.PostService;
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
    public String renderHomePage(ModelMap model) {
        model.addAttribute("username", null); // update when session issue resolved
        model.addAttribute("posts", service.getAllPosts());
        return "index";
    }
}
