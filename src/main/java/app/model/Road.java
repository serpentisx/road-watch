
package app.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * An instance of Road stores diverse information about a specific road
 */

@Entity
@Table (name="Road")
public class Road {
    
    @Id
    private Integer roadId;
    
    @Column(name="is_highway")
    private boolean isHighway;    // true if road is a highway
    @Column(name="road_number")
    private String roadNumber;    // road number (may be null)

    private String name;          // road name
    
    @Embedded
    private Location location;    // road's location (may be null)
    
    // Collection of all posts referring to the road
    @OneToMany(mappedBy="road", cascade=CascadeType.ALL)
    private Set<Post> posts = new HashSet<Post>();
    
    /**
     * Constructor
     * @param id
     * @param isHighway
     * @param roadNumber
     * @param name
     * @param location 
     */
    public Road(int id, boolean isHighway, String roadNumber, String name, Location location) {
      this.roadId = id;
      this.isHighway = isHighway;
      this.roadNumber = roadNumber;
      this.name = name;
      this.location = location;
    }
    
    
    public Road () {}

    public int getId() {
      return roadId;
    }

    public boolean isIsHighway() {
      return isHighway;
    }

    public String getRoadNumber() {
      return roadNumber;
    }

    public String getName() {
      return name;
    }

    public Location getLocation() {
      return location;
    }

    public Set<Post> getPosts() {
      return posts;
    }

    public void setPosts(Set<Post> posts) {
      this.posts = posts;
    }      
}
