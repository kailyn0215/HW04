
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Handles reading zip code data from files.
 * Also provides methods to look up Place objects by zip code.
 */
public class LookupZip {

    /**
     * Reads zip code and location data from two files and returns ExpandableArray of Place objects.
     *
     * @param filename1 the first CSV file
     * @param filename2 the second CSV file
     * @return an ExpandableArray containing Place + PopulatedPlace objects
     * @throws FileNotFoundException if either input file is not found
     */
    public static ExpandableArray<Place> readZipCodes(String filename1, String filename2) 
            throws FileNotFoundException {

        ExpandableArray<Place> places = new ExpandableArray<>();

        Scanner s1 = new Scanner(new File(filename1));
        while (s1.hasNextLine()) {
            String line = s1.nextLine().trim();
            if (line.isEmpty() || line.toLowerCase().contains("zipcode")) {
                continue;
            }

            String[] parts = line.split(",");
            String zipcode = parts[0].replace("\"", "");
            String town = parts.length > 1 ? parts[1].replace("\"", "") : "";
            String state = parts.length > 2 ? parts[2].replace("\"", "") : "";
            String popStr = parts.length > 3 ? parts[3].replace("\"", "") : "";

            int population = -1;
            try {
                population = Integer.parseInt(popStr);
            } catch (NumberFormatException e) {
                population = -1;
            }

            Place p;
            if (population > 0) {
                p = new PopulatedPlace(zipcode, town, state, 
                    0.0, 0.0, population);
            } else {
                p = new Place(zipcode, town, state);
            }
            places.insert(p);
        }
        s1.close();

        Scanner s2 = new Scanner(new File(filename2));
        while (s2.hasNextLine()) {
            String line = s2.nextLine().trim();
            if (line.isEmpty() || line.toLowerCase().contains("zipcode")) {
                continue;
            }

            String[] parts = line.split(",");
            String zipcode = parts[0].replace("\"", "");

            double lat = Double.NaN;
            double lon = Double.NaN;

            if (parts.length > 5) {
                try {
                    lat = Double.parseDouble(parts[5].trim());
                }
                catch (NumberFormatException ignored) {
                    //ignore
                }
            }
            if (parts.length > 6) {
                try {
                    lon = Double.parseDouble(parts[6].trim());
                }
                catch (NumberFormatException ignored) {
                    //ignore
                }
            }

            int idx = binarySearch(places, zipcode);
            if (idx >= 0) {
                Place old = places.get(idx);
                if (!Double.isNaN(lat) && !Double.isNaN(lon)) {
                    if (old instanceof PopulatedPlace) {
                        PopulatedPlace pp = (PopulatedPlace) old;
                        places.set(new PopulatedPlace(pp.getZip(), pp.getTown(), 
                            pp.getState(), lat, lon, pp.getPopulation()), idx);
                    } else {
                        places.set(new LocatedPlace(old.getZip(), old.getTown(), 
                            old.getState(), lat, lon), idx);
                    }
                }
            }
        }
        s2.close();

        places.sort();
        return places;
    }

    /**
     * Performs a binary search on ExpandableArray of Places for a given zip.
     *
     * @param places ExpandableArray of Place objects, sorted by zip code
     * @param zip the zip code to search for
     * @return the index of the Place if found, otherwise -1
     */
    public static int binarySearch(ExpandableArray<Place> places, String zip) {
        int low = 0;
        int high = places.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Place midPlace = places.get(mid);
            int cmp = midPlace.getZip().compareTo(zip);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Looks up a Place by zip code using binary search.
     *
     * @param places ExpandableArray of Place objects, sorted by zip code
     * @param zip the zip code to look up
     * @return the Place object if found, otherwise null
     */
    public static Place lookupZip(ExpandableArray<Place> places, String zip) {
        int idx = binarySearch(places, zip);
        return idx >= 0 ? places.get(idx) : null;
    }
}
