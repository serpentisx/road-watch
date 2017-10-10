
package app.controller;

import app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
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
    public String renderHomePage(HttpSession session, ModelMap model) {
        model.addAttribute("username", (String) session.getAttribute("loggedInUsername"));
        model.addAttribute("posts", service.getAllPosts());
        return "index";
    }
}
