/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.Post;
import java.util.List;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Manages all process work regarding posts.
 */
public interface PostService {

    /**
     * Create a new post
     * @param title
     * @param description
     * @param latitude
     * @param longitude
     * @param road
     * @param file
     * @param road_number
     * @param zip
     * @param locality
     * @return true if success, else false
     */
    public boolean createNewPost(String title, String description, String latitude, String longitude, String road, String file, String road_number, String zip, String locality);

    /**
     * Get all posts in database
     * @return list of all posts found in database
     */
    public List<Post> getAllPosts();
}
