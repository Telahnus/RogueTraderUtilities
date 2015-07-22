// Jordan Quan
// Star Object Class
// Used to create star systems with random attributes

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OldStar {

	//TYPE LISTS

	static String[] listStar = { "blue", "white", "yellow", "red supergiant",
		"red dwarf", "protostar", "white dwarf", "binary", "neutron",
		"nebula", "anomaly" };

	static String[] listSpecial = { "none", "bountiful", "haven", "cursed",
		"pirates", "ruins", "derelict", "starfarers", "stasis",
			"turbulence" };
	static String[] listElements = { "none", "asteroids", "dust", "gravity",
			"radiation", "flares" };
	static String[] listGas = { "ammonia", "water", "cloudless", "alkali",
			"silicate" };

	static String[] listClimate = { "lava", "desert", "arid", "terran",
		"jungle", "ocean", "tundra", "arctic", "ice", "barren" };

	//PROB LISTS
	static int[] probType = { 0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 7, 8, 9,
			10 };

	//ZONE LISTS
	List<List<String>> zone = new ArrayList<List<String>>(3);
	ArrayList<String> inner = new ArrayList<String>();
	ArrayList<String> middle = new ArrayList<String>();
	ArrayList<String> outer = new ArrayList<String>();

	//ATTRIBUTES
	String type;
	String theme;
	String name;
	String binary;
	int[] sizes = new int[3];

	//RANDOM INTEGER (inclusive)
	private static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	//CONSTRUCTOR
	public OldStar(String starName) {
		name = starName;
		type = listStar[randInt(0, listStar.length - 1)];
		sizes = this.zoneSize();
		this.populate();
		theme = listSpecial[randInt(0, listSpecial.length - 1)];
		this.themeChange();
	}

	//DETERMINE SIZE OF EACH ZONE
	public int[] zoneSize() {

		int in = randInt(1, 5); //zones are typically 1d5
		int mid = randInt(1, 5);
		int out = randInt(1, 5);

		if (type.equals("mighty")) {
			in += 2;
		} //first 3 types add +2 to relevant zones
		if (type.equals("luminous")) {
			mid += 2;
		}
		if (type.equals("dull")) {
			out += 2;
		}

		if (type.equals("anomalous")) { //anomalous adds to one, and subtracts from another
			int first = randInt(1, 3); //pick firstb random zone
			int second;
			do {
				second = randInt(1, 3); //pick a second random zone
			} while (first == second); //that is not the same as the first
			switch (first) { //add to the first zone
			case 1:
				in += 2;
				break;
			case 2:
				mid += 2;
				break;
			case 3:
				out += 2;
				break;
			default:
				break;
			}
			switch (second) { //subtract from the second
			case 1:
				in -= 2;
				if (in < 1) {
					in = 1;
				}
				break;
			case 2:
				mid -= 2;
				if (mid < 1) {
					mid = 1;
				}
				break;
			case 3:
				out -= 2;
				if (out < 1) {
					out = 1;
				}
				break;
			default:
				break;
			}
		}

		if (type.equals("binary")) { //binary has two stars
			int first = randInt(0, 3);
			int second = randInt(0, 3);
			if (second < first) { //swap random stars so stronger one is dominant
				int temp = first;
				first = second;
				second = temp;
			}
			type = listStar[first]; //reset star type to dominant
			this.binary = listStar[second]; //and make a note of lesser binary star
			sizes = this.zoneSize(); //recall this same method
			return sizes; //and return new sizes (this took forever to debug!!)
		}

		int[] sizes = { in, mid, out }; //finally, return array of zone sizes
		return sizes;
	}

	//POPULATE EACH ZONE
	//according to sizes array
	public void populate() {
		int max = listElements.length - 1;
		for (int i = 1; i <= sizes[0]; i++) {
			int temp = randInt(0, max);
			while (temp == 8 || temp == 9) {
				temp = randInt(0, max); //reroll for improper elements
			}
			inner.add(listElements[temp]);
		}
		for (int i = 1; i <= sizes[1]; i++) {
			int temp = randInt(0, max);
			while (temp == 3 || temp == 7) {
				temp = randInt(0, max);
			}
			middle.add(listElements[temp]);
		}
		for (int i = 1; i <= sizes[2]; i++) {
			int temp = randInt(0, max);
			while (temp == 6 || temp == 7) {
				temp = randInt(0, max);
			}
			outer.add(listElements[temp]);
		}
		zone.add(inner);
		zone.add(middle);
		zone.add(outer);
	}

	//THEME ALTERATIONS
	//each theme adds or remove specific elements
	public void themeChange() {
		if (theme.equals("bountiful")) { //add mineral asteroids
			zone.get(randInt(0, 2)).add(listElements[1]);
		}
		if (theme.equals("gravity")) { //add riptides
			int temp = randInt(1, 5);
			for (int i = 0; i < temp; i++) {
				zone.get(randInt(0, 2)).add(listElements[4]);
			}
		}
		if (theme.equals("haven")) { //add planets
			for (int i = 0; i < 3; i++) {
				zone.get(i).add(listElements[5]);
			}
		}
		if (theme.equals("ruins")) { //add xenos ruins or spaceship graveyards or derelict stations
			//count planets
			int planets = 0;
			int tech = Math.max(randInt(1, 5) - 1, randInt(1, 5) - 1);
			for (int i = 0; i < 3; i++) {
				for (String x : this.zone.get(i)) {
					if (x.equals("planet")) {
						planets++;
					}
				}
			}
			if (planets < tech) {
				for (int i = 0; i < tech - planets; i++) {
					zone.get(randInt(1, 2)).add(listElements[randInt(8, 9)]);
				}
			}
		}
		if (theme.equals("starfarers")) { //add inhabited planets
			//count planets
			int planets = 0;
			for (int i = 0; i < 3; i++) {
				for (String x : this.zone.get(i)) {
					if (x.equals("planet")) {
						planets++;
					}
				}
			}
			if (planets < 4) {
				for (int i = 0; i < 4 - planets; i++) {
					zone.get(randInt(0, 2)).add(listElements[5]);
				}
			}
		}
		if (theme.equals("anomaly")) { //+1 and -1 planets
			int count = 2;
			int tries = 0;
			while (count > 0 && tries < 10) {
				int section = randInt(0, 2);
				int temp = zone.get(section).indexOf("planet");
				if (temp != -1) {
					zone.get(section).remove(temp);
					count--;
				}
				tries++;
			}
		}
	}

	public static void main(String args[]) {
		OldStar Xaros = new OldStar("Xaros");
		System.out.println("System Name: " + Xaros.name);
		System.out.println("Primary star type: " + Xaros.type);
		if (Xaros.binary != null) {
			System.out.println("Secondary star type: " + Xaros.binary);
		}
		System.out.println("System feature: " + Xaros.theme);
		System.out.println("System elements: ");
		System.out.println(Xaros.zone);
	}

}