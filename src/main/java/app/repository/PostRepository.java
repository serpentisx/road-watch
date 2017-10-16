/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Repository class for Post. Responsible for fetching and saving data in the database
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    /**
     * Find all posts in database
     * @return list of all post found in database
     */
    @Override
    public List<Post> findAll();

    /**
     * Save a post to the database
     * @param post : the post to be saved
     * @return Post : If success the saved post is returned, else null
     */
    @Override
    public Post save(Post post);
    
    /**
     * Finds post by postId
     * 
     * @param postId        the postId to search by
     * @return            the matching post instance
     */
    public Post findByPostId(int postId);
    
}
