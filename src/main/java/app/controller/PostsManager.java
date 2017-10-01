
package app.controller;

import app.model.Post;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Valentin Oliver Loftsson, vol1
 *
 * Listens to requests at defined routes related to handling posts on the posts page
 * and is responsible for fetching and processing data as well as rendering pages.
 */
@Controller
@RequestMapping("/posts")
public class PostsManager {
     
   /**
    * Fetches user's input and creates a new post.
    * Renders posts page, which should then include the new post.
    *
    * @param params the user's input from the new-post form
    * @param model  an object with attributes which can be delivered to the view
    * @return       string representing page to be rendered
    */
    @RequestMapping(value = "/new-post", method = RequestMethod.POST)
    public String newPost(@RequestParam Map<String,String> params, ModelMap model){
        
        /* vantar útfærslu */
        
        return "posts";
    }
}
