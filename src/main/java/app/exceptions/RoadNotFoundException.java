
package app.exceptions;

/**
 *
 * @author Valentin Oliver
 * @date Last updated on 12 November 2017
 * 
 * Road not found exception
 */
public class RoadNotFoundException extends Exception {
    
    private final String name;
    
    public RoadNotFoundException(String name, String cause) {
        super("Unique database correspondence of road " + name + " not found.", new Throwable(cause));
        this.name = name;
    }
    
    public String getRoadName() {
        return name;
    }
}
