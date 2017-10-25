package app.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Model for the user account. 
 * Stores login information and user's username
 */

@Entity
@Table (name="Account")
public class Account {
 
    @Id
    private String email;     // User's email address
    
    @Column (name="name")
    private String username;  // User's username
    
    private String password;  // User's password
    
    // Collection of all posts referring to the user's account
    @OneToMany(mappedBy="account", cascade=CascadeType.ALL)
    private transient Set<Post> posts = new HashSet<Post>();
    
    // Collection of all login events
    @OneToMany(mappedBy="account", cascade=CascadeType.ALL)
    private transient Set<LoginEvent> logins = new HashSet<LoginEvent>();
    
    // Collection of all posts user has supported
    @ManyToMany(mappedBy = "supporters", cascade=CascadeType.ALL)
    private Set<Post> supported = new HashSet<Post>();
    
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
    
    public Set<Post> getSupported() {
        return supported;
    }
    
    public void addPostSupport(Post post) {
        supported.add(post);
    }
    
    public Set<LoginEvent> getLogins() {
        return logins;
    }
}
