
package app.service;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 20 October 2017
 *
 * Records all login attempts by users
 */
public interface LoginEventService {
    
    /**
     * Creates a login event
     * 
     * @param user : the user to create a login event from
     */
    public void createNewLoginEvent(String user);
    
}
