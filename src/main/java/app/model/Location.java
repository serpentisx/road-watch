package app.model;

// Class for requred location data
public class Location {
    //Coordinates should have two values [0] for x axis and [1] for y axis

    private double[] coordinates;
    private String address;
    private int zip;
    private String municipality;
    private String region;

    public Location(double[] coordinates, String address, int zip, String municipality, String region) {
        if (coordinates.length == 2) {
            this.coordinates[0] = coordinates[0];
            this.coordinates[1] = coordinates[1];
        } else {
            throw new IllegalArgumentException("Coordinate array should haver two values");
        }
        this.address = address;
        this.zip = zip;
        this.municipality = municipality;
        this.region = region;
    }

    //return the coordinates
    public double[] getCoordinates() {
        return coordinates;
    }

    //return the address
    public String getAddress() {
        return address;
    }

    //return the zip
    public int getZip() {
        return zip;
    }

    //return the municipality
    public String getMunicipality() {
        return municipality;
    }

    //return the region
    public String getRegion() {
        return region;
    }
}
