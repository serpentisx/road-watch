package app.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * An instance of Post stores information about a road system defect entry
 */

@Entity
@Table (name="Post")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    
    private LocalDate dating;   // post date
    
    @Column(name="photo")
    private String photoURL;    // road system defect image url
    
    private String title;       // post title
    private String description; // short post description
    private Integer support;    // number of post supports
    private Boolean archived;   // true if post is archived
    private Double latitude;    // latitude for location of road system defect
    private Double longitude;   // longitude for location of road system defect
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "roadId")    
    private Road road;          // road on which road system defect was detected
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "email")
    private Account account;    // user account associated with the post
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
        name = "PostSupport", 
        joinColumns = { @JoinColumn(name = "postId") }, 
        inverseJoinColumns = { @JoinColumn(name = "email") }
    )
    Set<Account> supporters = null;   // list of all supporting users
    
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("is"));
    
    public Post (String photo, String title, String description, double latitude, double longitude, Road road, Account account) {
        this.supporters = new HashSet<Account>();
        this.dating = LocalDate.now();
        this.photoURL = photo;
        this.title = title;
        this.description = description;
        this.support = 0;
        this.archived = false;
        this.latitude = latitude;
        this.longitude = longitude;
        this.road = road;
        this.account = account;
    }
    
    public Post () {this.supporters = new HashSet<Account>();
}
    
    public int getId() {
      return postId;
    }

    public void setId(int id) {
      this.postId = id;
    }
    
    public String getDating() {
      if (dating != null) {
        String text = this.dating.format(FORMATTER);
        return text;
      }
      return null;
    }
    
    public void setDating(LocalDate dating) {
      this.dating = dating;
    }

    public String getPhotoURL() {
      return photoURL;
    }

    public void setPhotoURL(String photoURL) {
      this.photoURL = photoURL;
    }

    public String getTitle() {
      return title;
    }
    
    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public int getSupport() {
      return support;
    }

    public void setSupport(int support) {
      this.support = support;
    }

    public boolean isArchived() {
      return archived;
    }
    
    public void setArchived(boolean archived) {
      this.archived = archived;
    }

    public double getLatitude() {
      return latitude;
    }

    public void setLatitude(double latitude) {
      this.latitude = latitude;
    }
    
    public double getLongitude() {
      return longitude;
    }
    
    public void setLongitude(double longitude) {
      this.longitude = longitude;
    }

    public Road getRoad() {
      return road;
    }

    public void setRoad(Road road) {
      this.road = road;
    }
    
    public Account getAccount() {
      return account;
    }

    public void setAccount(Account account) {
      this.account = account;
    }
    
    public Set<Account> getSupporters() {
      return supporters;
    }
}