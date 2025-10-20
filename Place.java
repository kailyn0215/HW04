
/**
 * Represents a geographic place with a zipcode, town, and state.
 */
public class Place implements Comparable<Place> {

    /**
     * The zipcode of the place.
     */
    protected String zipcode;

    /**
     * The town name of the place.
     */
    protected String town;

    /**
     * The state name of the place.
     */
    protected String state;

    /**
     * Constructs a Place object with the provided zip, town, and state.
     *
     * @param zipcode the zipcode of the place
     * @param town the town name
     * @param state the state
     */
    public Place(String zipcode, String town, String state) {
        this.zipcode = (zipcode == null ? "" : zipcode);
        this.town = (town == null ? "" : town);
        this.state = (state == null ? "" : state);
    }

    /**
     * Gets the zipcode.
     *
     * @return the zipcode of this place
     */
    public String getZip() {
        return zipcode;
    }

    /**
     * Gets the town name.
     *
     * @return the town name of this place
     */
    public String getTown() {
        return town;
    }


    /**
     * Gets the state name.
     *
     * @return the state of this place
     */
    public String getState() {
        return state;
    }


    /**
     * Returns a string representation of the place.
     *
     * @return a string with town and state
     */
    public String toString() {
        return town + ", " + state;
    }

    /**
     * Compares this place to another by zipcode for sorting.
     *
     * @param other the place to compare to
     * @return negative, zero, or positive integer as > = < comparatively
     */
    public int compareTo(Place other) {
        return this.zipcode.compareTo(other.zipcode);
    }
}
