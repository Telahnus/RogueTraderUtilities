// Jordan Quan
// Asteroid.java
// creates asteroid type elements

public class Asteroid extends Element {

	// DATA
	public static String[] listAsteroid = { "asteroid cluster",
		"asteroid ring", "large asteroid", "comet" };

	// ATTRIBUTES

	// CONSTRUCTOR SPECIFIC
	public Asteroid(String givenType) {
		if (givenType != null) {
			subclass = givenType;
		}
		superclass = "asteroid";
	}

	// CONSTRUCTOR
	public Asteroid() {
		subclass = listAsteroid[randInt(0, listAsteroid.length - 1)];
		superclass = "asteroid";
	}

}
