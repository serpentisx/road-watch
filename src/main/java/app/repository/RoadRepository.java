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
 *
 * @author Bjarki
 */
public interface RoadRepository extends JpaRepository <Road, Integer> {
    
    public List<Road> findAll();
    
    public Road findByRoadNumber(String road_number);
}
