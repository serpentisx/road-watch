
package app.service;

import app.exceptions.FileUploadException;
import app.exceptions.RoadNotFoundException;
import app.model.Post;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 12 November 2017
 *
 * Manages all process work regarding posts.
 */
public interface PostService {

    /**
     * Creates a new post and associates it with a Road and an Account;
     * if a unique Road is not found, the post is not created.
     * 
     * @param postInfo    parameters from user input
     * @param imgFile     uploaded image file
     * @param userEmail   user's email
     * @throws app.exceptions.RoadNotFoundException
     * @throws app.exceptions.FileUploadException
     */
    public void createNewPost(Map<String, String> postInfo, MultipartFile imgFile, 
            String userEmail) throws RoadNotFoundException, FileUploadException;
    
    /**
     * Returns all posts as a java List
     * 
     * @return          list of all posts found in database
     */
    public List<Post> getAllPosts();
    
    /**
     * Returns all posts as a JSON string, for front-end processing purposes
     * 
     * @param user      user's email, helps to tell if a particular post is supported by user
     * @return          JSON string representing posts
     */
    public String getAllPostsJSON(String user);
    
    /**
     * Get post by id
     * 
     * @param id        id from a post
     * @return          returns a unique post if determined, otherwise null
     */
    public Post getPostById(int id);

    /**
     * Support a post
     * Precondition: post and userEmail are not null
     * 
     * @param post      the post to support
     * @param userEmail the user's email who is supporting
     */
    public void supportPost(Post post, String userEmail);

    /**
     * Unsupport a post
     * Precondition: post and userEmail are not null
     * 
     * @param post      the post to unsupport
     * @param userEmail the user's email who is unsupporting
     */
    public void unsupportPost(Post post, String userEmail);
}
