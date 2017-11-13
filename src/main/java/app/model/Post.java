
package app.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 12 November 2017
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
    
    @ElementCollection
    private Set<String> supporters = new HashSet<String>();   // list of all supporting users
    
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("is"));
    
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
    
    public Post () { }
    
    public int getId() {
        return postId;
    }
    
    public String getDating() {
        if (this.dating != null) {
            String text = this.dating.format(FORMATTER);
            return text;
        }
        return null;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getSupport() {
        return support;
    }

    @Transactional
    public void increaseSupport() {
        this.support += 1;
    }
    
    @Transactional
    public void decreaseSupport() {
        this.support -= 1;
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
    
    public double getLongitude() {
        return longitude;
    }

    public Road getRoad() {
        return road;
    }
    
    public Account getAccount() {
        return account;
    }

    public Set<String> getSupporters() {
        return supporters;
    }
}