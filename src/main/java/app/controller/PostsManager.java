
package app.controller;

import app.model.Post;
import app.service.AccountService;
import app.service.PostService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
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
    
    /**
     * 
     * @param session maintains the user's session
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/innlegg", method = RequestMethod.GET)
    public String renderPostPage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "login";
        }
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
     * @param file    the image file the user uploaded
     * @return        string representing page to be rendered
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/innlegg", method = RequestMethod.POST)
    public String newPost(
      HttpSession session, @RequestParam  Map<String, String> params, 
      ModelMap model, @RequestParam("file") MultipartFile file
    ) throws IOException {              
        model.addAttribute("username", (String) session.getAttribute("username"));
        
        if (params.get("btn") != null) { return "new_post"; }
        
        String title = params.get("title");
        String description = params.get("description");
        
        // Hidden inputs
        String latitude = params.get("latitude");
        String longitude = params.get("longitude");
        String roadName = params.get("road");
        String roadNumber = params.get("road_number");
        String zip = params.get("zip");
        String locality = params.get("locality");
        
        String userEmail = (String) session.getAttribute("user");
        
        byte[] bytes = null;
        if (!file.isEmpty()) {
            bytes = file.getBytes(); 
        }
        
        boolean postCreated = postService.createNewPost(title, description, bytes, 
                latitude, longitude, roadName, roadNumber, zip, locality, userEmail);
        if (postCreated) {
          model.addAttribute("posts", postService.getAllPosts());
          return "index";
        }
        model.addAttribute("message", "Ekki tókst að búa til innleggið, reyndu aftur");
        return "new_post";
    }
    
    /**
     * Calls a method for supporting/unsupporting a post for currently logged in user
     * 
     * @param id : the post's ID to support
     * @param session : the current session
     */    
    @RequestMapping(value = "/supportPost", method = RequestMethod.POST)
    public @ResponseBody
    void support(@RequestBody int id, HttpSession session) {
        Post post = postService.getPostById(id);
        String userEmail = (String) session.getAttribute("user");
        if (post.getSupporters().contains(userEmail)) {
            postService.unsupportPost(id, userEmail);
        } else {
            postService.supportPost(id, userEmail);
        }
    }
}
