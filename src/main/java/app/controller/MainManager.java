
package app.controller;

import app.model.Post;
import app.service.PostService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
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
        
        //Gson gson = new Gson();
        //java.lang.reflect.Type listType = new TypeToken<List<Post>>() {}.getType();
        //String postsJSON = gson.toJson(posts, listType);
        
        //model.addAttribute("postsJSON", postsJSON);
        model.addAttribute("posts", posts);

        return "index";
    }
}
