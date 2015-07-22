import java.util.Random;

public class AnomalyGenerator {

	// DATA
	public static String[] listAnomaly = { "seismic", "caverns",
		"huge landmark", "crystallized terrain", "floating terrain",
		"strong magnetic field", "mountainous", "corrosive soil",
		"fertile soil", "poor soil", "gems", "rare mineral veins",
		"fungoids", "luminescent flora", "reefs", "gaseous flora",
		"strange fossils", "microbes", "luminescent fauna",
		"deep sea fauna", "archeotech ruins/artifacts",
		"xenos ruins/artifacts", "imperial cache", "beacon",
		"research station", "low gravity", "high gravity", "short day",
		"long day", "complete cloud cover", "does not rotate",
		"always faces sun", "45 tilt", "90 tilt", "0 tilt",
		"rich atmosphere", "thin atmosphere", "dense atmosphere",
		"toxic atmosphere", "polluted atmosphere", "no atmosphere",
		"psychic null zone", "astropathic amplifier", "cursed",
		"long seasons", "short seasons", "shifting weather", "perma fog",
		"polar tempests", "no water", "trapped water", "corrosive water",
		"toxic water", "quarantined", "meteor strikes", "psychoactive air",
		"auroras", "post apocalypse", "pirates", "koronus primitive xenos",
		"koronus primitive xenos", "koronus primitive xenos",
		"koronus primitive xenos", "koronus primitive xenos",
		"koronus flora", "koronus flora", "koronus flora", "koronus flora",
		"koronus flora", "koronus fauna", "koronus fauna", "koronus fauna",
		"koronus fauna", "koronus fauna", "inequity fauna",
		"inequity fauna", "inequity fauna", "inequity fauna",
		"inequity fauna", "inequity ppl", "inequity ppl", "inequity ppl",
		"inequity ppl", "inequity ppl", "inequity encounter site",
		"inequity encounter site", "inequity encounter site",
		"inequity encounter site", "inequity encounter site",
		"inequity encounter site", "inequity encounter site",
		"inequity encounter site", "inequity encounter site",
		"inequity encounter site", "inequity planetside complication",
		"inequity planetside complication",
		"inequity planetside complication",
		"inequity planetside complication",
		"inequity planetside complication",
		"inequity planetside complication",
		"inequity planetside complication",
		"inequity planetside complication",
		"inequity planetside complication",
		"inequity planetside complication", "inequity local phenomena",
		"inequity local phenomena", "inequity local phenomena",
		"inequity local phenomena", "inequity local phenomena",
		"inequity local phenomena", "inequity local phenomena",
		"inequity local phenomena", "inequity local phenomena",
		"inequity local phenomena", "inequity local phenomena",
		"inequity local phenomena", "inequity local phenomena",
		"inequity local phenomena", "inequity local phenomena" };

	public AnomalyGenerator() {
	}

	// RANDOM INTEGER (inclusive)
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public String genAnom() {
		return listAnomaly[randInt(0, listAnomaly.length - 1)];
	}
}
