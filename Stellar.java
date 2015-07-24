// Jordan Quan
// Stellar.java
// creates stellar anomalies

public class Stellar extends Element {

	// DATA
	public static String[] listStellar = { "flare", "radiation", "gravity",
			"dust" };

	// ATTRIBUTES

	// CONSTRUCTOR SPECIFIC
	public Stellar(String s) {
		if (s != null) {
			subclass = s;
		}
		superclass = "stellar";
	}

	// CONSTRUCTOR
	public Stellar() {
		subclass = listStellar[randInt(0, listStellar.length - 1)];
		superclass = "stellar";
	}

}
