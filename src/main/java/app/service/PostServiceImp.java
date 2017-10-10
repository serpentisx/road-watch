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
 *
 * @author Bjarki
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
    public boolean createNewPost(String title, String description, String latitude, String longitude, String roadName, String file, String road_number, String zip, String locality){
        try {
            Account account = accountRep.findByEmail(provisionalEmail);
            Road road = roadRep.findByRoadNumber(road_number);
            Post post = new Post(file, title, description, Double.parseDouble(latitude), Double.parseDouble(longitude), road, account);
            postRep.save(post);
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
