// Jordan Quan
// Planet Object Class
// Used to create planets with randomized attributes

import java.util.ArrayList;
import java.util.Random;

public class OldPlanet {

	// ATTRIBUTES
	String name;
	int zone;
	int size;
	int climate;
	int moons;
	ArrayList<OldPlanet> satellites = new ArrayList<OldPlanet>();
	ArrayList<Integer> resources = new ArrayList<Integer>();

	// LISTS
	static String[] listZone = { "inner", "belt", "outer" };
	static String[] listSize = { "tiny", "small", "medium", "large", "huge" };
	static String[] listClimate = { "lava", "desert", "arid", "terran",
			"jungle", "tundra", "ocean", "arctic", "ice", "barren" };
	static String[] listResource = { "none", "power", "minerals", "food",
			"tech" };
	static String[] listExotic = { "" };

	// DISTRUBTIONS
	static int[] distZone = { 0, 1, 2 };
	static int[][] distResource = { { 1, 1, 1, 2, 2, 0, 0, 0, 0, 0 },
			{ 1, 2, 2, 2, 4, 0, 0, 0, 0, 0 }, { 1, 1, 2, 2, 3, 4, 0, 0, 0, 0 },
			{ 1, 1, 2, 2, 3, 3, 4, 0, 0, 0 }, { 1, 2, 3, 3, 4, 4, 0, 0, 0, 0 },
			{ 1, 1, 3, 3, 4, 4, 0, 0, 0, 0 }, { 1, 2, 3, 3, 3, 4, 0, 0, 0, 0 },
			{ 1, 2, 2, 3, 4, 4, 0, 0, 0, 0 }, { 2, 3, 4, 4, 4, 0, 0, 0, 0, 0 },
			{ 4, 4, 0, 0, 0, 0, 0, 0, 0, 0 } };
	static int[][] distClimate = { { 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 4, 9 },
			{ 0, 1, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9 },
			{ 3, 5, 6, 6, 7, 7, 7, 8, 8, 8, 8, 8, 9 } };
	static int[] distCost = { 4, 2, 1, 1, 1, 2, 1, 2, 4, 1 };

	// RANDOM INTEGER
	private static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	// CONSTRUCTOR general
	public OldPlanet(String planetName) {
		name = planetName;
		zone = randInt(0, 2);
		size = randInt(0, 4);
		climate = distClimate[zone][randInt(0, 9)];
		for (int i = 0; i < size + 3; i++) {
			resources.add(distResource[climate][randInt(0, 9)]);
		}
	}

	// PRINT
	public void print() {
		System.out.println("Name: " + name + ", Zone: " + listZone[zone]
				+ ", Size: " + listSize[size] + ", Climate: "
				+ listClimate[climate]);
		for (int i = 0; i < resources.size(); i++) {
			// int j = resources.get(i);
			System.out.print(listResource[resources.get(i)] + ",");
		}
	}

	// MAIN METHOD TESTER
	public static void main(String[] args) {
		OldPlanet Xaros = new OldPlanet("Xaros");
		Xaros.print();
	}
}