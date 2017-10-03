
package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * MainManager handles home page get-requests 
 */
@Controller
@RequestMapping("") 
public class MainManager {

   /**
    * 
    * @return  string representing page to be rendered
    */
    @RequestMapping("/")
    public String renderHomePage(){
        return "index";
    }
}
