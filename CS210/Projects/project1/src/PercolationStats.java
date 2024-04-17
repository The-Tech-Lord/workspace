import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class PercolationStats {
    private int independent_experiments;
	private double[][] percolation_thresholds;

	private double perc_mean;
	private double std_dev;
	private int perc_thres_size;

    // Performs m independent experiments on an n x n percolation system.
    public PercolationStats(int n, int m) {
   		int perc_thres_size = percolation_thresholds.length;
    }

    // Returns sample mean of percolation threshold.
    public double mean() {
		double result = 0.0;

		// sigma(xm)
        for (int i = 0; i < this.perc_thres_size; i++) {
			result += percolation_thresholds[i] / this.perc_thres_size;
		}

		this.perc_mean = result;
		return result;
    }

    // Returns sample standard deviation of percolation threshold.
    public double stddev() {
        double result = 0.0;

		// sigma((xm - mu)^2 / (N - 1))
		for (int i = 0; i < this.perc_thres_size; i++) {
			result += Math.pow(this.percolation_thresholds[i] - this.perc_mean, 2) / (perc_thres_size - 1);
		}

		this.std_dev = result;
		return Math.sqrt(result);
    }

    // Returns low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return this.perc_mean - ((1.96 * this.std_dev) / Math.sqrt(this.perc_thres_size));
    }

    // Returns high endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return this.perc_mean + ((1.96 * this.std_dev) / Math.sqrt(this.perc_thres_size));
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, m);
        StdOut.printf("Percolation threshold for a %d x %d system:\n", n, n);
        StdOut.printf("  Mean                = %.3f\n", stats.mean());
        StdOut.printf("  Standard deviation  = %.3f\n", stats.stddev());
        StdOut.printf("  Confidence interval = [%.3f, %.3f]\n", stats.confidenceLow(),
                stats.confidenceHigh());
    }
}
