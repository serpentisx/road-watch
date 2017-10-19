/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.Post;
import app.model.Road;
import java.util.List;
import static sun.security.krb5.Confounder.bytes;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Manages all process work regarding posts.
 */
public interface PostService {

    /**
     * Creates a new post and associates it with a Road and an Account;
     * if a unique Road is not found, the post is not created.
     * 
     * 
     * @param title       new post title
     * @param description new post description
     * @param file        image file name 
     * @param latitude    latitude of road defect
     * @param longitude   longitude of road defect
     * @param roadName    road name from new post form
     * @param roadNumber  road number from new post form
     * @param zip         zip from new post form
     * @param locality    locality from new post form
     * @param email       email corresponding to currently logged in user
     * @return            true if successful, else false
     */
    public boolean createNewPost(String title, String description, byte[] file, String latitude, String longitude, String roadName, String roadNumber, String zip, String locality, String email);
    
    /**
     * 
     * @param roadName    road name from new post form
     * @param roadNumber  road number from new post form, may be null
     * @param zip         zip from new post form, may be null
     * @param locality    locality from new post form, may be null
     * @return            returns a unique road if determined, otherwise null
     */
    public Road determineUniqueRoad(String roadName, String roadNumber, String zip, String locality);
    
    /**
     * Get all posts in database
     * 
     * @return            list of all posts found in database
     */
    public List<Post> getAllPosts();
    
    public String postsToJSON(List<Post> posts);
    
    /**
     * 
     * @param id        id from a post
     * @return          returns a unique post if determined, otherwise null
     */
    public Post getPostById(int id);
}
