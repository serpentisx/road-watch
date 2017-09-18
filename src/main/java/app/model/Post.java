package app.model;

//Class for storing data requred for a submission
public class Post {
    private String photo;
    private Location location;
    private String description;
    //amount of support(likes) on a post
    private int support;
    private boolean archived;
    private String type;
    
    public Post (String photo, Location location, String description, int support, boolean archived, String type) {
        this.photo = photo;
        this.location = location;
        this.description = description;
        this.support = support;
        this.archived = archived;
        this.type = type;
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
