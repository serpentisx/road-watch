/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.Post;
import java.util.List;

/**
 *
 * @author Bjarki
 */
public interface PostService {
    
    public boolean createNewPost(String title, String description, String latitude, String longitude, String road, String file, String road_number, String zip, String locality);
    
    public List<Post> getAllPosts();
}
