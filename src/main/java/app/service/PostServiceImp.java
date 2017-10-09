/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.Account;
import app.model.Post;
import app.model.Road;
import app.repository.AccountRepository;
import app.repository.PostRepository;
import app.repository.RoadRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Service class for Post. Reponsible for all processing regarding posts.
 */
@Service
public class PostServiceImp implements PostService {
    
    
    @Autowired
    PostRepository postRep;
    
    @Autowired
    AccountRepository accountRep;
    
    @Autowired
    RoadRepository roadRep;
    
    // provisional resort while we haven't figured out how to save the user's session
    String provisionalEmail = "notandi@hi.is";
    
    @Override
    public boolean createNewPost(String title, String description, String latitude, String longitude, String road, String file, String road_number, String zip, String locality){
        try {
            Account a = accountRep.findByEmail(provisionalEmail);
            Road r = roadRep.findByRoadNumber(road_number);
            Post p = new Post(file, title, description, Double.parseDouble(latitude), Double.parseDouble(longitude), r, a);
            postRep.save(p);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    @Override
    public List<Post> getAllPosts() {
      System.out.println("getAllPosts!");
      ArrayList<Post> posts = (ArrayList<Post>) (postRep.findAll());
      System.out.println(posts);
      return posts;
    }
    
}
