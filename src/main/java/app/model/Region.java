package app.model;

//Class for basic information for Regions and their municipalities

import java.util.List;

public class Region {
    private String name;
    private List<Municipality> municipalities;
    
    public Region (String name) {
        this.name = name;
        //Kannski viljum við hafa call by need frekar en pre-load
        this.municipalities = generateMunicipalities(name);
    }
    
    //Input: string name representing a valid region name in the database
    //returns: List of municipality objects that are located in said region
    public List<Municipality> generateMunicipalities(String name){
        return null;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the municipalities
     */
    public List<Municipality> getMunicipalities() {
        return municipalities;
    }
}
