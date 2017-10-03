
package app.controller;

import app.service.MainService;
import app.service.PostService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("")
public class PostsManager {
  
  @Autowired
  PostService service;
     
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
        String title = params.get("title");
        String description = params.get("description");
        String latitude = params.get("latitude");
        String longitude = params.get("longitude");
        String road = params.get("road");
        //String file = params.get("file");
        String file = "img/road-desert.png";
        String road_number = params.get("road_number");
        String zip = params.get("zip");
        String locality = params.get("locality");
        String email = "a@a.is"; //master email, every post created under this email temporarily
                
        boolean createPost = service.createNewPost(title, description, latitude, longitude, road, file, road_number, zip, locality);
        
        return "index";
    }
}
