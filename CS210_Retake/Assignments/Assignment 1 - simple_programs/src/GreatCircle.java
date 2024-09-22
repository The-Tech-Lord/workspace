import stdlib.StdOut;

public class GreatCircle {
    // Entry Point.
    public static void main(String[] args) {
		// Input the coordinates of your two points that you want to calculate the great circle distance of
        double x1 = Double.parseDouble(args[0]);
		double y1 = Double.parseDouble(args[1]);
		double x2 = Double.parseDouble(args[2]);
		double y2 = Double.parseDouble(args[3]);

		// Calculates the Great Circle distance
		double distance = 6359.83 * Math.acos(Math.sin(Math.toRadians(x1)) * Math.sin(Math.toRadians(x2)) + Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2)) * Math.cos(Math.toRadians(y1 - y2)));

		StdOut.println(distance);
    }
}
