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
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Service class for Post. Responsible for all processing regarding posts.
 */
@Service
public class PostServiceImp implements PostService {
    
    
    @Autowired
    PostRepository postRep;
    
    @Autowired
    AccountRepository accountRep;
    
    @Autowired
    RoadRepository roadRep;
    
    @Override
    public boolean createNewPost(String title, String description, String file, String latitude, String longitude, String roadName, String roadNumber, String zip, String locality, String email) {
        Road road = determineUniqueRoad(roadName, roadNumber, zip, locality);
        
        if (road != null) {
          System.out.println(road.getName() + " " + road.getId());
          Account account = accountRep.findByEmail(email);
          
          Double lat = (Double) Double.parseDouble(latitude);
          Double lng = (Double) Double.parseDouble(longitude);
          
          Post newPost = new Post(file, title, description, lat, lng, road, account);
          postRep.save(newPost);
          return true;
        }
        return false;
    }
    
    @Override
    public Road determineUniqueRoad(String roadName, String roadNumber, String zip, String locality) {        
        List<Road> roads = roadRep.findByName(roadName);
        
        for(Road road: roads) {
          System.out.println("Vegauðkenni: " + road.getId());
        }
        
        // 1. Ef einn vegur, skila honum. 
        // 2. Annars ef enginn, skila null.
        // 3. Annars (ef fleiri en einn), reyna að fækka í einn.
        //    a. Ef nákvæmlega einn sem passar, skila honum.
        //    b. Annars skila null.
        if (roads.size() == 1) {
          System.out.println("EXACTLY ONE ROAD FOUND");
          return roads.get(0);
          
        } else if (roads.isEmpty()) {
          System.out.println("NO ROADS FOUND");
          return null;
          
        } else {
          System.out.println("MORE THAN ONE ROAD FOUND");
          Road road = null;
          if (roadNumber != null && !roadNumber.isEmpty()) {
            road = roadRep.findByRoadNumberAndName(roadNumber, roadName);
            
          } else if (zip != null && !zip.isEmpty()) {
            road = roadRep.findByZipAndName((Integer) Integer.parseInt(zip), roadName);
            
          } else if (locality != null && !locality.isEmpty()) {
            roads = roadRep.findByLocalityAndName(locality, roadName);
            
            if (roads.size() == 1) {
              return roads.get(0);
            }
          }
          return road;
        }
    }
    
    @Override
    public List<Post> getAllPosts() {
      ArrayList<Post> posts = (ArrayList<Post>) (postRep.findAll());
      return posts;
    }
    
    @Override
    public String postsToJSON(List<Post> posts) {
      return new Gson().toJson(posts);
    }
    
    @Override
    public Post getPostById(int id){
        Post post = postRep.findByPostId(id);
        return post;
    }
    
}
