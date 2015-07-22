import java.util.ArrayList;
import java.util.Random;

// Jordan Quan
// Star.java
// RT utility used to generate a star system

public class Star {

	// LISTS
	public static String[] listType = { "blue", "white", "yellow", "giant",
		"dwarf", "proto", "binary", "neutron", "nebula", "anomaly" };
	public static String[] listNumerals = { "I", "II", "III", "IV", "V", "VI",
			"VII", "VIII", "IX", "X" };
	public static String[] listSpecials = { "stasis", "turbulence", "minerals",
			"haven", "cursed", "pirates", "ruins" };

	// ATTRIBUTES
	public static String name;
	public static String starType;
	public static String companion;
	public static int planet_count;
	public static String special;
	public static String specialDesc;

	public static ArrayList<Element> myElements = new ArrayList<Element>();

	// RANDOM INTEGER (inclusive)
	private static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	// CONSTRUCTOR
	public Star(String starName, boolean random) {
		name = starName;
		planet_count = 0;
		if (random) {
			starType = listType[randInt(0, listType.length - 1)];
			if (starType == "binary") {
				starType = listType[randInt(0, 4)];
				companion = listType[randInt(0, 4)];
			}
			for (int i = 0; i < randInt(1, 10); i++) { // gen d10 elements
				Element e = new Element();
				myElements.add(e);
			}
			addStarElements(starType);
			specifyElements();
			if (randInt(1, 10) > 8) {
				addSpecial();
			}
		}
	}

	static void addSpecial() {
		special = listSpecials[randInt(0, listSpecials.length - 1)];
		switch (special) {
		case "stasis":
			specialDesc = "The Warp is calm, travel is easy, but psychic powers are hindered";
			break;
		case "turbulence":
			specialDesc = "The Warp is turbulent, travel is difficult, and psychic powers are more powerful";
			break;
		case "bountiful":
			specialDesc = "The system contains bountiful or exotic resources";
			break;
		case "haven":
			specialDesc = "The system contains several habitable planets (do this manually!)";
			break;
		case "cursed":
			specialDesc = "The system carries an ill-omen";
			break;
		case "pirates":
			specialDesc = "The system is plagued by pirates";
			break;
		case "ruins":
			specialDesc = "The system is home to a long lost civilization or ruins of unknown origins";
			break;
		default:
			break;
		}
	}

	static void specifyElements() {
		for (int i = 0; i < myElements.size(); i++) {
			Element e = myElements.get(i);
			switch (e.superclass) {
			case "stellar":
				if (e.subclass == null) {
					myElements.set(i, new Stellar());
				}
				break;
			case "asteroid":
				myElements.set(i, new Asteroid());
				break;
			case "gas":
				String gasName = name + " " + listNumerals[planet_count];
				planet_count++;
				myElements.set(i, new Gas(gasName));
				break;
			case "planet":
				String planetName = name + " " + listNumerals[planet_count];
				planet_count++;
				myElements.set(i, new Planet(planetName, starType));
				break;
			default:
				break;
			}
		}
	}

	static void addStarElements(String starType) {
		switch (starType) {
		case "proto":
			myElements.add(new Stellar("radiation"));
			break;
		case "neutron":
			myElements.add(new Stellar("gravity"));
			break;
		case "nebula":
			myElements.add(new Stellar("dust"));
			break;
		default:
			break;
		}
	}

	// MAIN
	public static void main(String[] args) {
		Star s1 = new Star("Imperialis", true);
		System.out.println("System name: " + s1.name);
		System.out.print("Star type: " + s1.starType);
		if (companion != null) {
			System.out.print(", Companion: " + s1.companion);
		}
		// print out elements
		String elemStr = "\nElements: ";
		for (int i = 0; i < myElements.size(); i++) {
			Element e = myElements.get(i);
			elemStr += e.subclass + ", ";
		}
		elemStr = elemStr.substring(0, elemStr.length() - 2);
		System.out.println(elemStr);
		if (special != null) {
			System.out.println("Special: " + specialDesc);
		}
		// print gas & planet details
		for (int i = 0; i < myElements.size(); i++) {
			Element e = myElements.get(i);
			if (e.superclass == "planet") {
				Planet p = (Planet) e;
				p.printDetails();
			} else if (e.superclass == "gas") {
				Gas g = (Gas) e;
				g.printDetails();
			}
		}
	}
}
