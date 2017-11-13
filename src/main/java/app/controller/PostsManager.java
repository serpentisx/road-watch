
package app.controller;

import app.exceptions.FileUploadException;
import app.exceptions.RoadNotFoundException;
import app.model.Post;
import app.service.AccountService;
import app.service.PostService;
import java.util.List;
import java.util.Map;
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
 * @date Last updated on 12 November 2017
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
     * @param model   an object with attributes which can be delivered to the view
     * @return        string representing page to be rendered
     */
    @RequestMapping(value = "/innlegg", method = RequestMethod.GET)
    public String renderPostPage(HttpSession session, ModelMap model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("formType", "login");
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
     * @throws app.exceptions.RoadNotFoundException
     * @throws app.exceptions.FileUploadException
     */
    @RequestMapping(value = "/innlegg", method = RequestMethod.POST)
    public String newPost(
      HttpSession session, @RequestParam  Map<String, String> params, 
      ModelMap model, @RequestParam("file") MultipartFile file
    ) throws RoadNotFoundException, FileUploadException {
      
        String email = (String) session.getAttribute("user");
        postService.createNewPost(params, file, email);
        
        model.addAttribute("user", email);
        
        List<Post> posts = postService.getAllPosts();
        String postsJSON = postService.getAllPostsJSON(email);
        
        model.addAttribute("posts", posts);
        model.addAttribute("postsJSON", postsJSON);
        
        return "index";
    }
    
    /**
     * Calls a method for supporting/unsupporting a post for currently logged in user
     * 
     * @param id      the post id to support
     * @param session the current session
     */
    @RequestMapping(value = "/supportPost", method = RequestMethod.POST)
    public @ResponseBody
    void support(@RequestBody int id, HttpSession session) {
        Post post = postService.getPostById(id);
        String userEmail = (String) session.getAttribute("user");
        if (post != null) {
            if (post.getSupporters().contains(userEmail)) {
                postService.unsupportPost(post, userEmail);
            } else {
                postService.supportPost(post, userEmail);
            }
        }
    }
}
