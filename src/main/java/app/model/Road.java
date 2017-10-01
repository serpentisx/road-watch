
package app.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private int id;
    
    @Column(name="is_highway")
    private boolean isHighway;    // true if road is a highway
    @Column(name="road_number")
    private String roadNumber;    // road number (may be null)

    private String name;          // road name
    
    @Embedded
    private Location location;    // road's location (may be null)
    
    // Collection of all posts referring to the road
    @OneToMany(mappedBy="road", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
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
      this.id = id;
      this.isHighway = isHighway;
      this.roadNumber = roadNumber;
      this.name = name;
      this.location = location;
    }
    
    /**
     * Default constructor
     */
    public Road () {}

    /**
     * @return the id
     */
    public int getId() {
      return id;
    }

    /**
     * @return the isHighway
     */
    public boolean isIsHighway() {
      return isHighway;
    }

    /**
     * @return the roadNumber
     */
    public String getRoadNumber() {
      return roadNumber;
    }

    /**
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
      return location;
    }

    /**
     * @return the posts
     */
    public Set<Post> getPosts() {
      return posts;
    }

    /**
     * @param posts the posts to set
     */
    public void setPosts(Set<Post> posts) {
      this.posts = posts;
    }      
}
