import java.util.ArrayList;

// Jordan Quan
// Gas.java
// creates gas giants, details, and satellites

public class Gas extends Element {

	// DATA
	public static String[] listGas = { "silicate", "alkali", "cloudless",
			"water", "ammonia" };
	public static String[] listSize = { "dwarf", "giant", "massive" };
	public static String[] listGravity = { "weak", "strong", "powerful",
	"titanic" };

	// ATTRIBUTES
	public String name;
	public String size;
	public String gravity;
	public ArrayList<String> satellites = new ArrayList<String>();

	// CONSTRUCTOR
	public Gas(String givenName) {
		name = givenName;
		superclass = "gas";
		subclass = listGas[randInt(0, listGas.length - 1)];
		size = listSize[randInt(0, listSize.length - 1)];
		gravity = listGravity[randInt(0, listGravity.length - 1)];
		genSatellites();
	}

	// CONSTRUCTOR SPECIFIC
	public Gas(String givenType, String givenSize, String givenGravity) {
		superclass = "gas";
		subclass = givenType;
		size = givenSize;
		gravity = givenGravity;
	}

	// METHODS
	public void rename(String newName) {
		name = newName;
	}

	// i suppose this could be one of those methods declared in Elements and detailed here?
	public void printDetails() {
		System.out.print("\nGas Giant " + name + " - composition: " + subclass);
		System.out.print(", size: " + size + ", gravity: " + gravity);
		if (satellites.size() > 0) {
			System.out.print("\n\tSatellites: " + satellites.toString());
		}
	}

	public void addSatellite(String sat) {
		satellites.add(sat);
	}

	// generate satellites for gas giant
	public void genSatellites() {
		int num = randInt(1, 10); // determine number of satellites
		// alter number by size
		if (size == "dwarf") {
			num -= 2;
		} else if (size == "massive") {
			num += 2;
		} // constrain to min/max
		if (num < 1) {
			num = 1;
		} else if (num > 10) {
			num = 10;
		}
		for (int i = 0; i < num; i++) {
			int bool = randInt(0, 1);
			if (bool == 1) { // add another coin flip before creating satellite
				int type = randInt(0, 4); // determine type of satellite
				// alter type by gravity strength
				if (gravity == "weak") {
					type--;
				} else if (gravity == "powerful") {
					type++;
				} else if (gravity == "titanic") {
					type += 2;
				}
				if (type < 0 || type > 4) {
					continue; // just skip if beyond type constraints
				} else { // finally, add it
					String sat = listSatellite[type];
					satellites.add(sat);
				}
			}
		}
	}

}
