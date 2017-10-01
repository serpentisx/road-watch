package app.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Team 20 HBV501G - Fall 2017
 *
 * An instance of Location stores information about a specific road's location
 * Note: Some roads might not have any location info
 */

@Embeddable
public class Location {
    
    private final int zip;
    private final String locality;
    private final String municipality;
    
    @Column(name="region_is")
    private final String regionIS;
    @Column(name="region_en")
    private final String regionEN;
    
    /**
     * Constructor
     * @param zip
     * @param locality
     * @param municipality
     * @param regionIS
     * @param regionEN 
     */
    public Location(int zip, String locality, String municipality, String regionIS, String regionEN) {
      this.zip = zip;
      this.locality = locality;
      this.municipality = municipality;
      this.regionIS = regionIS;
      this.regionEN = regionEN;
    }

    /**
     * @return the zip
     */
    public int getZip() {
      return zip;
    }

    /**
     * @return the locality
     */
    public String getLocality() {
      return locality;
    }
    
    /**
     * @return the municipality
     */
    public String getMunicipality() {
      return municipality;
    }

    /**
     * @return the regionIS
     */
    public String getRegionIS() {
      return regionIS;
    }

    /**
     * @return the regionEN
     */
    public String getRegionEN() {
      return regionEN;
    }
}
