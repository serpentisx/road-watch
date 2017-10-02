package app.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * An instance of Post stores information about a road system defect entry
 */

@Entity
@Table (name="Post")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDate dating;     // post date
    
    @Column(name="photo")
    private String photoURL;    // road system defect image url
    
    private String title;       // post title
    private String description; // short post description
    private int support;        // number of post supports
    private boolean archived;   // true if post is archived
    private double latitude;    // latitude for location of road system defect
    private double longitude;   // longitude for location of road system defect
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "roadId")    
    private Road road;      // road on which road system defect was detected
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "email")
    private Account account;    // user account associated with the post
    
    
    public Post (String photo, String title, String description, double latitude, double longitude, Road road, Account account) {
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
    
    public Post () {}

    /**
     * @return the id
     */
    public int getId() {
      return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
      this.id = id;
    }

    /**
     * @return the dating
     */
    public LocalDate getDating() {
      return dating;
    }

    /**
     * @param dating the dating to set
     */
    public void setDating(LocalDate dating) {
      this.dating = dating;
    }

    /**
     * @return the photoURL
     */
    public String getPhotoURL() {
      return photoURL;
    }

    /**
     * @param photoURL the photoURL to set
     */
    public void setPhotoURL(String photoURL) {
      this.photoURL = photoURL;
    }

    /**
     * @return the title
     */
    public String getTitle() {
      return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
      this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
      return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
      this.description = description;
    }

    /**
     * @return the support
     */
    public int getSupport() {
      return support;
    }

    /**
     * @param support the support to set
     */
    public void setSupport(int support) {
      this.support = support;
    }

    /**
     * @return the archived
     */
    public boolean isArchived() {
      return archived;
    }

    /**
     * @param archived the archived to set
     */
    public void setArchived(boolean archived) {
      this.archived = archived;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
      return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
      this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
      return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
      this.longitude = longitude;
    }

    /**
     * @return the road
     */
    public Road getRoad() {
      return road;
    }

    /**
     * @param road the road to set
     */
    public void setRoad(Road road) {
      this.road = road;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
      return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
      this.account = account;
    }
}
