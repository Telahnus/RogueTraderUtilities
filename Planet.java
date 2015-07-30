import java.util.ArrayList;

// Jordan Quan
// Planet.java
// generate planets in all their detail

public class Planet extends Element {

	// DATA
	public static String[] listZone = { "inner", "belt", "outer" };
	public static String[] listSize = { "tiny", "small", "medium", "large",
			"huge" };
	public static String[] listClimate = { "lava", "desert", "arid", "terran",
		"jungle", "ocean", "tundra", "arctic", "ice", "barren" };
	public static String[] listResource = { "none", "minerals", "organic",
			"tech" };
	public static String[] listExotic = { "" };

	// probability distributions
	public static int[][] distClimate = {
		{ 9, 9, 9, 9, 0, 0, 0, 0, 1, 1, 1, 2, 2 }, //inner
		{ 0, 1, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9 }, //belt
		{ 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9 } //outer
	};
	public static int[][] distResource = { { 0, 0, 0, 0, 1, 1, 2, 3, 3, 3 }, //L
		{ 0, 0, 0, 0, 0, 0, 1, 1, 2, 3 }, //D
		{ 0, 0, 0, 0, 1, 1, 1, 2, 2, 3 }, //A
		{ 0, 0, 1, 1, 1, 2, 2, 2, 3, 3 }, //T
		{ 0, 0, 0, 0, 1, 2, 2, 2, 3, 3 }, //J
		{ 0, 0, 0, 0, 1, 2, 2, 2, 3, 3 }, //O
		{ 0, 0, 0, 0, 0, 1, 1, 2, 2, 3 }, //T
		{ 0, 0, 0, 0, 0, 1, 1, 2, 2, 3 }, //A
		{ 0, 0, 0, 0, 1, 1, 2, 3, 3, 3 }, //I
		{ 0, 0, 0, 0, 0, 0, 0, 1, 2, 3 } //B
	};

	// ATTRIBUTES
	public String name;
	public String zone;
	public String size;
	public String climate;
	public int dayLength;
	public ArrayList<String> resources = new ArrayList<String>();
	public ArrayList<String> satellites = new ArrayList<String>();
	public ArrayList<String> anomalies = new ArrayList<String>();

	// CONSTRUCTOR
	public Planet(String givenName, String starType) {
		name = givenName;
		superclass = "planet";
		int numZone = randInt(0, listZone.length - 1); // gen zone number
		zone = listZone[numZone]; // then zone string
		int numClimate = genClimate(starType, numZone); // gen climate num
		climate = listClimate[numClimate]; // then get climate string
		subclass = climate;
		size = listSize[randInt(0, listSize.length - 1)];
		genResources(numClimate); // gen resources by climate
		genSatellites();
		dayLength = 0; // day length is 3d10 hrs
		for (int i = 0; i < 4; i++) {
			dayLength += randInt(1, 10);
		}
		dayLength += satellites.size(); // +1hr per satellite
		genAnomalies();
	}

	// CONSTRUCTOR SPECIFIC

	// METHODS
	public void rename(String newName) {
		name = newName;
	}

	// use anomalygenerator to generate planetary anomalies
	private void genAnomalies() {
		AnomalyGenerator ag = new AnomalyGenerator();
		//System.out.println(ag.listAnomaly.length);
		for (int i = 0; i < randInt(1, 5); i++) {
			if (randInt(0, 1) == 1) {
				anomalies.add(ag.genAnom());
			}
		}
	}

	// gen climate number modified by star type and zone
	private int genClimate(String starType, int numZone) {
		int max = distClimate[numZone].length - 1; // determine max length of climate dist
		int num = randInt(0, max); // randomly pick an index from dist climate
		// modify by star type
		switch (starType) {
		case "blue":
			num -= 2;
			break;
		case "white":
			num--;
			break;
		case "giant":
			num++;
			break;
		case "dwarf":
			num += 2;
			break;
		default: // bug resolved
			break;
		}
		// if star type takes num beyond min/max, set to error
		if (num < 0 || num > max) {
			num = 9;
		} else {
			num = distClimate[numZone][num];
		}
		return num;
	}

	private void genResources(int numClimate) {
		for (int i = 0; i < randInt(1, 5); i++) { // d5 resources
			int a = randInt(0, 9); // d10 resource number
			int b = distResource[numClimate][a]; // modified by climate
			String c = listResource[b];
			if (c != "none") {
				resources.add(c);
			}
		}
	}

	public void printDetails() {//name, zone, size, climate, day, resources, sats, anoms
		System.out
				.print("\nPlanet " + name + " - zone: " + zone + ", size: "
						+ size + ", climate: " + climate + ", day:" + dayLength
						+ "hrs");
		if (resources.size() > 0) {
			System.out.print("\n\tResources: " + resources.toString());
		}
		if (satellites.size() > 0) {
			System.out.print("\n\tSatellites: " + satellites.toString());
		}
		if (anomalies.size() > 0) {
			System.out.print("\n\tAnomalies: " + anomalies.toString());
		}
	}

	public void addSatellite(String sat) {
		satellites.add(sat);
	}

	public void genSatellites() {
		int num = randInt(1, 5);
		for (int i = 0; i < num; i++) {
			int bool = randInt(0, 1);
			if (bool == 1) {
				satellites.add(listSatellite[randInt(0, 4)]);
			}
		}
	}

}
