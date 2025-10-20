
/**
 * Represents a place with lat and lon coords and extends Place.
 */
public class LocatedPlace extends Place {

    /**
     * The latitude coordinate of the place.
     */    
    protected double latitude;

    /**
     * The longitude coordinate of the place.
     */
    protected double longitude;

    /**
     * Constructs a LocatedPlace object.
     * 
     * @param zip the postal code of the place
     * @param t the town name
     * @param s the state abbreviation
     * @param lat the latitude coordinate
     * @param lon the longitude coordinate
     */
    public LocatedPlace(String zip, String t, 
            String s, double lat, double lon) {
        super(zip, t, s);
        latitude = lat;
        longitude = lon;
    }
    
    /**
     * Gets the latitude coordinate.
     *
     * @return the latitude coordinate
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude coordinate.
     *
     * @return the longitude coordinate
     */    
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns a string representation of the LocatedPlace.
     *
     * @return the string representation showing place details and coordinates
     */    
    @Override
    public String toString() {
        String base = super.toString();
        String formatted = String.format("%.2f%.2f", latitude, longitude);

        if (formatted.endsWith("0")) {
            formatted = formatted.substring(0, formatted.length() - 1);
        }

        return base + formatted;
    }
}
