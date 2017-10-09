/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.model.Road;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Repository class for Road. Responsible for fetching and saving data in the database
 */
public interface RoadRepository extends JpaRepository <Road, Integer> {

    /**
     * Find all road in database
     * @return list of all roads found in database
     */
    @Override
    public List<Road> findAll();

    /**
     * Find road by road number
     * @param road_number : the road number to search for
     * @return Road : the corresponding road with road_number
     */
    public Road findByRoadNumber(String road_number);
}
