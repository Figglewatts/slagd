package figglewatts.slagd.util;

public class RandomUtil {
	private RandomUtil() { }
	
	public static int generateRandomNumber(int min, int max) {
		return (int)(min + (Math.random() * ((max - min) + 1)));
	}
}
