
package app.repository;

import app.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 12 November 2017
 *
 * Repository class for Post. Responsible for fetching and saving data.
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Override
    public List<Post> findAll();

    @Override
    public Post save(Post post);
    
    /**
     * Finds post by postId
     * 
     * @param postId  the postId to search by
     * @return        the matching post instance
     */
    public Post findByPostId(int postId);
    
}
