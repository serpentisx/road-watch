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
    
    public boolean createNewPost(String title, String description, String latitude, String longitude, String road, String file, String road_number, String zip, String locality){
        try{
            Account a = accountRep.findByUserEmail("a@a.is");
            Road r = roadRep.findByRoadNumber(road_number);
            Post p = new Post(file, title, description, Double.parseDouble(latitude), Double.parseDouble(longitude), r, a);
            postRep.save(p);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }
    
}
