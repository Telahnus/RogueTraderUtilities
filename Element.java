import java.util.Random;

public class Element {

	// DATA
	public static String[] listElements = { "planet", "gas", "asteroid",
			"stellar" };
	public static int[] probElements = { 0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3 };
	public static String[] listSatellite = { "dust ring", "debris ring",
			"tiny moon", "small moon", "medium moon" };

	// ATTRIBUTES
	public String superclass;
	public String subclass;

	// RANDOM INTEGER (inclusive)
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	// CONSTRUCTOR
	public Element() {
		superclass = listElements[probElements[randInt(0,
				probElements.length - 1)]];
	}

	// CONSTRUCTOR SPECIFIC
	public Element(String s) {
		superclass = s;
	}

}
