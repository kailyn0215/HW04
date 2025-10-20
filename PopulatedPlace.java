
/**
 * Represents a geographic place that has a population count.
 * Extends LocatedPlace by adding population information.
 */
public class PopulatedPlace extends LocatedPlace {

    /**
     * The population of the place.
     */
    protected int population;

    /**
     * Constructs a PopulatedPlace object with given location and population.
     *
     * @param zipcode the postal code of the place
     * @param town the town name
     * @param state the state abbreviation
     * @param latitude the latitude coordinate
     * @param longitude the longitude coordinate
     * @param population the population count
     */
    public PopulatedPlace(String zip, String t, String s, double lat, double lon, int pop) {
        super(zip, t, s, lat, lon);
        population = pop;
    }

    /**
     * Gets the population count.
     *
     * @return the population of this place
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Returns a string representation of the populated place.
     *
     * @return string showing place details and population if greater than zero
     */
    @Override
    public String toString() {
        if (population > 0) {
            String base = super.toString();
            return base + " " + population;
        }
        return super.toString();
    }
}
