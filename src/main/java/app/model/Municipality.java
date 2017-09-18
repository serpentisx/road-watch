package app.model;
import java.util.List;
//Class for basic information for Muncipality

public class Municipality {
    private String name;
    private List<Post> posts;
    
    public Municipality (String name){
        this.name = name;
        //Kannski viljum við hafa call by need frekar en pre-load
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
