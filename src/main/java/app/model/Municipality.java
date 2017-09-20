package app.model;
import java.util.List;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * Class for basic information for Muncipality
 */

public class Municipality {
    // Name of this municipality
    private String name;
    
    // Posts that belongs to this municipality
    private List<Post> posts;
    
    public Municipality (String name){
        this.name = name;
        this.posts = generatePosts(name);
    }
    
    //Input: string name representing a valid municipality name in the database
    //returns: List of Post objects that have been made for said municipality
    private List<Post>  generatePosts (String name){
        return null;
    }

    //return the name
    public String getName() {
        return name;
    }

    //return the posts
    public List<Post> getPosts() {
        return posts;
    }
}
