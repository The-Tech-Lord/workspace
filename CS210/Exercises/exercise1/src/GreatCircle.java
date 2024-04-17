import stdlib.StdOut;

public class GreatCircle {
    // Entry point.
    public static void main(String[] args) {
        // Accept x1 (double), y1 (double), x2 (double), and y2 (double) as command-line arguments.
		double x1 = Math.toRadians(Double.parseDouble(args[0]));
		double y1 = Math.toRadians(Double.parseDouble(args[1]));
		double x2 = Math.toRadians(Double.parseDouble(args[2]));	
		double y2 = Math.toRadians(Double.parseDouble(args[3]));

        // Convert the angles to radians.
		

        // Calculate great-circle distance d.
		double distance = 6359.83 * Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1- y2));

        // Write d to standard output.
        System.out.println(distance);
    }
}
