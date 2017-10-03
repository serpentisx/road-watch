/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Bjarki
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
  
    public List<Post> findAll();
    
    public Post save(Post post);
    
}
