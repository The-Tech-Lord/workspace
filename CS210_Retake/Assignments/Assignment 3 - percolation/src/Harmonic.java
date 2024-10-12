import stdlib.StdOut;

public class Harmonic {
    // Entry point.
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
		Rational term = new Rational(1);

		// Computes the nth Harmonic number
		for (int n = 2; n <= number; n++) {
			term = term.add(new Rational(1, n));
		}

		StdOut.println(term);
    }
}
