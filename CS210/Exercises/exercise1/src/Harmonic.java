import stdlib.StdOut;

public class Harmonic {
    // Entry point.
    public static void main(String[] args) {
        // Accept n (int) as command-line argument.
		long n = Integer.parseInt(args[0]);

        // Set total to the rational number 0.
		Rational total = new Rational(0, 0);

        // For each 1 <= i <= n, add the rational term 1 / i to total.
		for (int i = 1; i <= n; i++) {
			// Starting rational in Harmonic equation
			if (i == 1) {
				total.setNumerator(1);
				total.setDenominator(1);
				//StdOut.printf("%s: %d\n", total, i);
				continue;
			}
			
			Rational intermediate_rational = new Rational(1, i);
			total = total.add(intermediate_rational);
			//StdOut.printf("%s: %d\n", total, i);
		}

        // Write total to standard output.
		StdOut.printf("%s\n", total);
    }
}
