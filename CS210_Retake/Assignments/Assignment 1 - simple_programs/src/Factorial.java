import stdlib.StdOut;

public class Factorial {
    // Entry point.
    public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);

		// Initial value for factorial
		long factorial = 1;

		// Calculates the factorial of `n`
        for (int i = 0; i < n; i++) {
			if (n <= 0) break;
			factorial = factorial * (i + 1);
		}

		StdOut.println(factorial);
    }
}
