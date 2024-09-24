import stdlib.StdIn;
import stdlib.StdOut;

public class Distance {
    // Entry point.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
		double[] x = new double[n], y = new double[n];
		double euclidean_distance = 0.0;

		for (int i = 0; i < n; i++) {
			x[i] = StdIn.readDouble();
		}
		for (int i = 0; i < n; i++) {
			y[i] = StdIn.readDouble();
		}

		for (int i = 0; i < n; i++) {
			euclidean_distance += Math.pow(x[i] - y[i], 2);
		}
		euclidean_distance = Math.sqrt(euclidean_distance);

		StdOut.println(euclidean_distance);
    }
}
