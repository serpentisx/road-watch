
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 12 November 2017
 *
 * Model for the user account. 
 * Stores login information and user's username
 */
@Entity
@Table (name="Account")
public class Account {
 
    @Id
    @NotNull
    @Pattern(regexp="\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b")
    private String email;     // User's email address
    
    @Column (name="name")
    @NotNull
    @Size(min=1, max=100)
    private String username;  // User's username
    
    @NotNull
    @Size(min=7, max=100, message="Password must be between 7 to 100 characters long")
    private String password;  // User's password
    
    // Collection of all posts referring to the user's account
    @OneToMany(mappedBy="account", cascade=CascadeType.ALL)
    private Set<Post> posts = new HashSet<Post>();
    
    // Collection of all login events
    @OneToMany(mappedBy="account", cascade=CascadeType.ALL)
    private Set<LoginEvent> logins = new HashSet<LoginEvent>();
    
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

    public Set<LoginEvent> getLogins() {
        return logins;
    }
}
