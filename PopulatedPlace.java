public class PopulatedPlace extends LocatedPlace {
    protected int population;

    public PopulatedPlace(String zipcode, String town, String state, double latitude, double longitude, int population) {
        super(zipcode, town, state, latitude, longitude);
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        if (population > 0) {
            String base = super.toString();
            return base + " Population: " + population;
        }
        return super.toString();
    }
}
