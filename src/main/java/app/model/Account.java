package app.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * Model for the user account. 
 * Stores login information and user's username
 */

@Entity
@Table (name="Account")
public class Account {
 
    @Id
    private String email;
    
    @Column (name="name")
    private String username;
    
    private String password;
    
    // Collection of all posts referring to the user's account
    @OneToMany(mappedBy="account", cascade=CascadeType.ALL)
    private Set<Post> posts = new HashSet<Post>();

    
    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public Account () {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
    public void setPassword(String password) {
      this.password = password;
    }
    
    public void setUsername(String name) {
      this.username = name;
    }

    public Set<Post> getPosts() {
      return posts;
    }
    
    public void setPosts(Set<Post> posts) {
      this.posts = posts;
    }      
}
