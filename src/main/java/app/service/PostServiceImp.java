
package app.service;

import app.exceptions.FileUploadException;
import app.exceptions.RoadNotFoundException;
import app.model.Account;
import app.model.Post;
import app.model.Road;
import app.repository.AccountRepository;
import app.repository.PostRepository;
import app.repository.RoadRepository;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.io.IOException;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 13 November 2017
 * 
 */
@Service
public class PostServiceImp implements PostService {
  
    public static final String CLOUDINARY_KEY = // 
            "cloudinary://881482785141911:XQOJQQ11mhNgiQVMC1W5LDEwOlc@vegavaktin";
    
    @Autowired
    PostRepository postRep;
    
    @Autowired
    AccountRepository accountRep;
    
    @Autowired
    RoadRepository roadRep;
    
    @Override
    public void createNewPost(Map<String, String> postInfo, 
      MultipartFile imgFile, String userEmail) throws RoadNotFoundException, FileUploadException {
      
        String roadName = postInfo.get("road");
        String roadNumber = postInfo.get("road_number");
        String zip = postInfo.get("zip");
        String locality = postInfo.get("locality");

        Road road = this.determineUniqueRoad(roadName, roadNumber, zip, locality);
         
        String title = postInfo.get("title");
        String description = postInfo.get("description");
        Double lat = (Double) Double.parseDouble(postInfo.get("latitude"));
        Double lng = (Double) Double.parseDouble(postInfo.get("longitude"));

        Account account = accountRep.findByEmail(userEmail);

        String imgURL = this.uploadImage(imgFile); 
        Post newPost = new Post(imgURL, title, description, lat, lng, road, account);
        postRep.save(newPost);
    }
    
    /**
     * Uploads image file to Cloudinary image server
     * 
     * @param file  The image file
     * @return      The url to the image stored on the server
     * @throws IOException
     */
    private String uploadImage(MultipartFile file) throws FileUploadException {
        try {
            byte[] imgBytes = null;
            if (!file.isEmpty()) {
                imgBytes = file.getBytes();
            }

            Uploader uploader = (new Cloudinary(CLOUDINARY_KEY)).uploader();
            Map imageResultMap = uploader.upload(imgBytes, ObjectUtils.emptyMap());

            return imageResultMap.get("secure_url").toString();

        } catch (IOException e) {
            throw new FileUploadException(e);
        }
    }
    
    /**
     * Determines if there exists a unique road in the database
     * which corresponds to the given road information
     * 
     * @param roadName    road name from new post form
     * @param roadNumber  road number from new post form, may be null
     * @param zip         zip from new post form, may be null
     * @param locality    locality from new post form, may be null
     * @return            returns a unique road
     */
    private Road determineUniqueRoad(String roadName, String roadNumber, 
      String zip, String locality) throws RoadNotFoundException {        
        List<Road> roads = roadRep.findByName(roadName);
        
        // 1. If one road found, return that one
        // 2. Else if none found, throw exception
        // 3. Else, if more than one found, attempt to eliminate
        //    a. If exactly one match, return that one
        //    b. Otherwise throw exception
        if (roads.size() == 1) {
            // EXACTLY ONE ROAD FOUND
            return roads.get(0);
          
        } else if (roads.isEmpty()) {
            // NO ROADS FOUND
            throw new RoadNotFoundException(roadName, "No road found");
          
        } else {
            // MORE THAN ONE ROAD FOUND
            Road road = null;
            if (roadNumber != null && !roadNumber.isEmpty()) {
                road = roadRep.findByRoadNumberAndName(roadNumber, roadName);

            } else if (zip != null && !zip.isEmpty()) {
                road = roadRep.findByZipAndName((Integer) Integer.parseInt(zip), roadName);

            } else if (locality != null && !locality.isEmpty()) {
                roads = roadRep.findByLocalityAndName(locality, roadName);
                
                if (roads.size() == 1) {
                    road = roads.get(0);
                }
            }
            if (road != null) { 
                // ELIMINATION SUCCESSFUL
                return road;
                
            } else { 
                // ELIMINATION UNSUCCESSFUL
                throw new RoadNotFoundException(roadName, "Multiple roads found, unique determination failed.");
            }
        }
    }
    
    @Override
    public List<Post> getAllPosts() {
        return postRep.findAll();
    }
    
    @Override
    public Set<Post> getAllSupportedPosts(String email) {
        return accountRep.findByEmail(email).getSupported();
    }
    
    @Override
    public Set<Post> getAllUserPosts(String email) {
        return accountRep.findByEmail(email).getPosts();
    }
    
    @Override
    public String getAllPostsJSON(String user) {
        List<Post> posts = postRep.findAll();
        List<HashMap<String, Object>> displayPosts = new ArrayList();
        
        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);
            HashMap<String, Object> post = new HashMap();
            
            post.put("id", p.getId());
            post.put("title", p.getTitle());
            post.put("description", p.getDescription());
            post.put("photo", p.getPhotoURL());
            post.put("author", p.getAccount().getUsername());
            post.put("date", p.getDating());
            post.put("support", Integer.toString(p.getSupport()));
            post.put("isSupporting", p.getSupporters().contains(user));
            post.put("road", p.getRoad());
            post.put("roadName", p.getRoad().toString());
            post.put("longitude", p.getLongitude());
            post.put("latitude", p.getLatitude());
            
            displayPosts.add(post);
        }
        
        return this.postsToJSON(displayPosts);
    }
    
    /**
     * Generates a JSON string from a java List
     * 
     * @param posts   the list containing objects to be converted to JSON
     * @return        JSON string representing posts
     */
    private String postsToJSON(List<?> posts) {
      return new Gson().toJson(posts);
    }

    @Override
    @Transactional
    public void supportPost(Post post, String userEmail) {
        post.increaseSupport();
        post.getSupporters().add(userEmail);
    }

    @Override
    @Transactional
    public void unsupportPost(Post post, String userEmail) {
        post.decreaseSupport();
        post.getSupporters().remove(userEmail);
    }
    
    @Override
    public Post getPostById(int id) {
        return postRep.findByPostId(id);
    }
    
    @Override
    @Transactional
    public boolean deletePost(int postId) {
        Post post = postRep.findByPostId(postId);
        if(post != null) {
          postRep.delete(post);
          return true;
        }
        return false;
    }
}
