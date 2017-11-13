
package app.repository;

import app.model.Road;
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
 * Repository class for Road. Responsible for fetching data from the database.
 */
public interface RoadRepository extends JpaRepository <Road, Integer> {

    @Override
    public List<Road> findAll();

    /**
     * Finds road by road number and name
     * 
     * @param roadNumber  the road number to search by
     * @param name        the road name to search by
     * @return            the matching road
     */
    public Road findByRoadNumberAndName(String roadNumber, String name);
    
    /**
     * Finds road by zip code and road name
     * 
     * @param zip         the zip code to search by
     * @param name        the road name to search by
     * @return            the matching road
     */
    public Road findByZipAndName(Integer zip, String name);
    
    /**
     * Finds road by locality and road name
     * 
     * @param locality    the locality to search by
     * @param name        the road name to search by
     * @return            all matching road instances
     */
    public List<Road> findByLocalityAndName(String locality, String name);
    
    /**
     * Finds road by road name
     * 
     * @param name        the road name to search by
     * @return            all matching road instances
     */
    public List<Road> findByName(String name);
}
