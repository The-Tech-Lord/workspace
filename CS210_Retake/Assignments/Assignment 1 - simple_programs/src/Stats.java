import stdlib.StdOut;
import stdlib.StdRandom;

public class Stats {
    // Entry Point.
    public static void main(String[] args) {
		// Command line argument input
        int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);

		// Randomly generate values
		double x1 = StdRandom.uniform((double)a, (double)b);
		double x2 = StdRandom.uniform((double)a, (double)b);
		double x3 = StdRandom.uniform((double)a, (double)b);

		// Calculate statistic variables
		double mean = (x1 + x2 + x3) / 3;
		double variance = (Math.pow(x1 - mean, 2.0) + Math.pow(x2 - mean, 2.0) + Math.pow(x3 - mean, 2.0)) / 3;
		double std_deviation = Math.sqrt(variance);

		StdOut.println(mean + " " + variance + " " + std_deviation);
    }
}
