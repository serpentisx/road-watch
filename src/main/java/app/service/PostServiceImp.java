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
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.io.IOException;
import java.io.File;

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
    public boolean createNewPost(String title, String description, byte[] file, String latitude, String longitude, String roadName, String roadNumber, String zip, String locality, String email) {
        Road road = determineUniqueRoad(roadName, roadNumber, zip, locality);

        // Connect to the image cloud
        Cloudinary cloudinary = new Cloudinary("cloudinary://881482785141911:XQOJQQ11mhNgiQVMC1W5LDEwOlc@vegavaktin");

        Map imageResultMap = null;

        // upload image to cloud
        try {
            System.out.println("Trying to upload image");
            imageResultMap = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            System.out.println(imageResultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (road != null) {
          System.out.println(road.getName() + " " + road.getId());
          Account account = accountRep.findByEmail(email);
          
          Double lat = (Double) Double.parseDouble(latitude);
          Double lng = (Double) Double.parseDouble(longitude);
          
          Post newPost = new Post(imageResultMap.get("secure_url").toString(), title, description, lat, lng, road, account);
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
    public String generateDisplayPostsJSON(List<Post> posts) {
        List<HashMap<String, Object>> displayPosts = new ArrayList();
        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);
            HashMap<String, Object> post = new HashMap();
            
            post.put("title", p.getTitle());
            post.put("description", p.getDescription());
            post.put("photo", p.getPhotoURL());
            post.put("author", p.getAccount().getUsername());
            post.put("date", p.getDating());
            post.put("support", Integer.toString(p.getSupport()));
            post.put("road", p.getRoad());
            post.put("roadName", p.getRoad().toString());
            post.put("longitude", p.getLongitude());
            post.put("latitude", p.getLatitude());
            
            displayPosts.add(post);
        }
        return postsToJSON(displayPosts);
    }
    
    @Override
    public String postsToJSON(List<?> posts) {
      return new Gson().toJson(posts);
    }

    @Override
    @Transactional
    public void supportPost(int postId, String userEmail) {
        Post p = postRep.findByPostId(postId);
        p.setSupport(p.getSupport() + 1);
        p.getSupporters().add(accountRep.findByEmail(userEmail));
    }

    @Override
    @Transactional
    public void unsupportPost(int postId, String userEmail) {
        Post p = postRep.findByPostId(postId);
        p.setSupport(p.getSupport() - 1);
        p.getSupporters().remove(accountRep.findByEmail(userEmail));
    }
    
    @Override
    public Post getPostById(int id){
        Post post = postRep.findByPostId(id);
        return post;
    }
}
