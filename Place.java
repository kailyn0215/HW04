public class Place implements Comparable<Place> {
    protected String zipcode;
    protected String town;
    protected String state;

    public Place(String zipcode, String town, String state) {
        this.zipcode = (zipcode == null ? "" : zipcode);
        this.town = (town == null ? "" : town);
        this.state = (state == null ? "" : state);
    }

    public String getZip() {
        return zipcode;
    }

    public String getTown() {
        return town;
    }

    public String getState() {
        return state;
    }

    public String toString() {
        return town + ", " + state;
    }

    // Compare by zipcode for sorting and searching
    public int compareTo(Place other) {
        return this.zipcode.compareTo(other.zipcode);
    }
}
