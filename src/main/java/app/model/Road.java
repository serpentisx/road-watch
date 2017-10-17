
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
import org.hibernate.annotations.Immutable;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * An instance of Road stores diverse information about a specific road
 */

@Entity
@Immutable
@Table (name="Road")
public class Road {
    
    @Id
    private Integer roadId;       // Road's id
    
    @Column(name="is_highway")
    private boolean isHighway;    // true if road is a highway
    @Column(name="road_number")
    private String roadNumber;    // road number (may be null)
    
    private String name;          // road name    
    private Integer zip;          // location's zip-code (may be null)
    private String locality;      // location's locality (may be null)
    private String municipality;  // location's municipality (may be null)
    
    @Column(name="region_is")
    private String regionIS;      // location's region (Icelandic)
    @Column(name="region_en")
    private String regionEN;      // location's region (English)
    
    
    // Collection of all posts referring to the road
    @OneToMany(mappedBy="road", cascade=CascadeType.ALL)
    private transient Set<Post> posts = new HashSet<Post>();
    
    /**
     * Constructor
     * 
     * @param roadId
     * @param isHighway
     * @param roadNumber
     * @param name
     * @param zip
     * @param locality
     * @param municipality
     * @param regionIS
     * @param regionEN 
     */
    public Road(Integer roadId, boolean isHighway, String roadNumber, String name, Integer zip, String locality, String municipality, String regionIS, String regionEN) {
        this.roadId = roadId;
        this.isHighway = isHighway;
        this.roadNumber = roadNumber;
        this.name = name;
        this.zip = zip;
        this.locality = locality;
        this.municipality = municipality;
        this.regionIS = regionIS;
        this.regionEN = regionEN;
    }
    
    public Road() {}

    public Integer getId() {
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
    
    public Integer getZip() {
      return zip;
    }
    
    public String getLocality() {
      return locality;
    }
    
    public String getMunicipality() {
      return municipality;
    }
    
    public String getRegionIS() {
      return regionIS;
    }

    public String getRegionEN() {
      return regionEN;
    }

    public Set<Post> getPosts() {
      return posts;
    }
    
    @Override
    public String toString() {
      String road = name;
      if (zip != null)          road += (", " + zip);
      if (locality != null)     road += (" " + locality);
      if (municipality != null) road += (", " + municipality + ", " + regionIS);
      return road;
    }
}
