import stdlib.StdOut;

public class PerfectNumbers {
    // Entry point.
    public static void main(String[] args) {
		int bounds = Integer.parseInt(args[0]);
		
		for (int i = 1; i <= bounds; i++) {
			// The divisors of `i` will be added up in this variable
			int proper_divisors_buffer = 0;

			// We can save computation time by dividing `i` in half since that's the lowest number we can possibly
			// go when dealing with integers and such
			for (int j = 1; j <= i / 2; j++) {
				if (i % j == 0) {
					// Add the divisor to `proper_divisors_buffer`
					proper_divisors_buffer += j;
				}
			}

			// Does a final check if `i` is a perfect number
			if (proper_divisors_buffer < i || proper_divisors_buffer > i) {
				continue;
			}
			StdOut.println(i);
		}
    }
}
