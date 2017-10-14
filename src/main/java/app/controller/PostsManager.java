
package app.controller;

import app.repository.RoadRepository;
import app.service.AccountService;
import app.service.PostService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Listens to requests at defined routes related to handling posts
 * and is responsible for fetching and processing data as well as rendering pages.
 */
@Controller
@RequestMapping("")
public class PostsManager {
  
  @Autowired
  PostService postService;
  
  @Autowired
  AccountService accountService;
     
  @RequestMapping(value = "/innlegg", method = RequestMethod.GET)
    public String renderPostPage(HttpSession session, @RequestParam  Map<String, String> params, ModelMap model) {
        
        return "new_post";
    }
    
    /**
     * Handles new post submissions
     * Fetches user's input and tries to create a new post.
     * If successful, renders home page, which should then include the new post.
     *
     * @param session maintains the user's session
     * @param params  the user's input from the new-post form
     * @param model   an object with attributes which can be delivered to the view
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/innlegg", method = RequestMethod.POST)
    public String newPost(HttpSession session, @RequestParam  Map<String, String> params, ModelMap model) {
              
        model.addAttribute("username", (String) session.getAttribute("username"));
        
        if (params.get("btn") != null) { return "new_post"; }
        
        String title = params.get("title");
        String description = params.get("description");
        
        /* NEED TO CHANGE WHEN FILE UPLOADING HAS BEEN IMPLEMENTED */
        // String file = params.get("file");
        String file = "../img/road-desert.png";
        
        // Hidden inputs
        String latitude = params.get("latitude");
        String longitude = params.get("longitude");
        String roadName = params.get("road");
        String roadNumber = params.get("road_number");
        String zip = params.get("zip");
        String locality = params.get("locality");
        
        String userEmail = (String) session.getAttribute("user");
        
        boolean postCreated = postService.createNewPost(title, description, file, latitude, longitude, roadName, roadNumber, zip, locality, userEmail);
        if (postCreated) {
          model.addAttribute("posts", postService.getAllPosts());
          return "index";
        }
        model.addAttribute("message", "Ekki tókst að búa til innleggið, reyndu aftur");
        return "new_post";
    }
}
