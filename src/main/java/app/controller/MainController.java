/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * 
 * MainController defines the routes at the root of the website and the actions 
 * to be performed at respectively routes.
 * 
 */

@Controller
@RequestMapping("") // Request Mapping for the root
public class MainController {
    
    // Route for home page 
    // @return String filePath.  filePath is supposed to point to a .jsp file.
    @RequestMapping("/")
    public String main_page(){
        return "main_page";
    }
}
