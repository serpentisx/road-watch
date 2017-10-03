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
    private Integer postId;
    
    private LocalDate dating;   // post date
    
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
    private Road road;          // road on which road system defect was detected
    
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

    
    public int getId() {
      return postId;
    }

    public void setId(int id) {
      this.postId = id;
    }
    
    public LocalDate getDating() {
      return dating;
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
}
