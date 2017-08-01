package arekkuusu.grimoireofalice.common.core.helper;

public class MathUtil {

	public static final double EPSILON = 1E-5;

	public static boolean fuzzyEqual(float a, float b) {
		return Math.abs(a - b) <= EPSILON;
	}

	public static boolean fuzzyEqual(double a, double b) {
		return Math.abs(a - b) <= EPSILON;
	}

	public static int fuzzyCompare(float a, float b) {
		return Math.abs(a - b) <= EPSILON ? 0 : Float.compare(a, b);
	}

	public static int fuzzyCompare(double a, double b) {
		return Math.abs(a - b) <= EPSILON ? 0 : Double.compare(a, b);
	}
}
