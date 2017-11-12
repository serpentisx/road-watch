
package app.repository;

import app.model.LoginEvent;
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
 * Stores all user login events. Responsible for fetching and saving data.
 */
public interface LoginEventRepository extends JpaRepository<LoginEvent, Integer>{
    
    @Override
    public List<LoginEvent> findAll();
    
    @Override
    public LoginEvent save(LoginEvent loginEvent);
}
