import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class PercolationStats {
	private int experiments;
	private double[] thresholds;

    // Performs m independent experiments on an n x n percolation system.
    public PercolationStats(int n, int m) {
		if (n <= 0 || m <= 0)
			throw new IllegalArgumentException("Illegal n or m");

		// Initialize
		this.experiments = m;
        this.thresholds = new double[this.experiments];

		// Check if the system percolates for each cycle of experiments
		for (int i = 0; i < m; i++) {
			Percolation subject = new Percolation(n);

			// Opening a random site and storing the percolation threshold in the index
			// associated with the experiment number
			while (!subject.percolates())
				subject.open(StdRandom.uniform(n), StdRandom.uniform(n));

			this.thresholds[i] = (double)subject.numberOfOpenSites() / (double)(n * n);
		}
    }

    // Returns sample mean of percolation threshold.
    public double mean() {
		return StdStats.mean(this.thresholds);
    }

    // Returns sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(this.thresholds);
    }

    // Returns low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return (this.mean() - ((1.96 * this.stddev()) / Math.sqrt(this.experiments)));
    }

    // Returns high endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return (this.mean() + ((1.96 * this.stddev()) / Math.sqrt(this.experiments)));
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, m);
        StdOut.printf("Percolation threshold for a %d x %d system:\n", n, n);
        StdOut.printf("  Mean                = %.3f\n", stats.mean());
        StdOut.printf("  Standard deviation  = %.3f\n", stats.stddev());
        StdOut.printf("  Confidence interval = [%.3f, %.3f]\n", stats.confidenceLow(), stats.confidenceHigh());
    }
}
