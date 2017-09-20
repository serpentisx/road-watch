package app.model;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * Class for storing data (posts/entries) required for a submission
 */

public class Post {
    // Post title
    private String title;

    // Post image
    private String photo;

    // Post location
    private Location location;

    // Description for this post
    private String description;

    // Amount of support(likes) on a post
    private int support;

    // Indicates whether this post has been processed or not
    private boolean archived;

    // Post catagory, e.g. roads, bus stops...
    private String type;

    public Post (String title, String photo, Location location, String description, String type) {
        this.title = title;
        this.photo = photo;
        this.location = location;
        this.description = description;
        this.support = 0;
        this.archived = false;
        this.type = type;
    }

    // return the photo
    public String getTitle() {
        return title;
    }

    // return the photo
    public String getPhoto() {
        return photo;
    }

    //return the location
    public Location getLocation() {
        return location;
    }

    //return the description
    public String getDescription() {
        return description;
    }

    //param description the description to set
    public void setDescription(String description) {
        this.description = description;
    }

    //return the support
    public int getSupport() {
        return support;
    }

    //Set support status
    public void setSupport(int support) {
        this.support = support;
    }

    //return the archived
    public boolean isArchived() {
        return archived;
    }

    //Set archived status
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    //return the type
    public String getType() {
        return type;
    }
}
