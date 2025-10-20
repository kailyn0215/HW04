
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads zipcode data and allows users to lookup places by zipcode.
 */
public class DriverHW04 {

    /**
     * Main method to run the zipcode lookup program.
     *
     * @param args array where args[0] is the first CSV file,
     *             args[1] is the second CSV file
     * @throws FileNotFoundException if either file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("java DriverHW04 uszipcodes.csv ziplocs.csv");
            return;
        }

        String file1 = args[0];
        String file2 = args[1];

        ExpandableArray<Place> places = LookupZip.readZipCodes(file1, file2);
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("zipcode: ");
            if (!in.hasNextLine()) {
                System.out.println("Good Bye!");
                break;
            }

            String z = in.nextLine().trim();
            if (z.length() == 0) {
                continue;
            }
            if (z.equals("00000")) {
                System.out.println("Good Bye!");
                break;
            }

            Place p = LookupZip.lookupZip(places, z);
            if (p == null) {
                System.out.println("No such zipcode");
            }
            else {
                System.out.println(p.toString());
            }
        }
        in.close();
    }
}
