package app.model;

import java.util.List;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * Class for basic information for Regions and their municipalities
 */

public class Region {
    // Region name
    private String name;
    
    // All municipalities that belongs to this region
    private List<Municipality> municipalities;
    
    public Region (String name) {
        this.name = name;
        this.municipalities = generateMunicipalities(name);
    }
    
    //Input: string name representing a valid region name in the database
    //returns: List of municipality objects that are located in said region
    public List<Municipality> generateMunicipalities(String name){
        return null;
    }

    //return the name
    public String getName() {
        return name;
    }

    //return the municipalities
    public List<Municipality> getMunicipalities() {
        return municipalities;
    }
}
