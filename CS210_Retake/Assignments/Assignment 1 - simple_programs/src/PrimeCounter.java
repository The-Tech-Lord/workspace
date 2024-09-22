import stdlib.StdOut;

public class PrimeCounter {
    // Entry point.
    public static void main(String[] args) {
        int domain = Integer.parseInt(args[0]);

		int primes = 0; // Stores the total number of prime numbers within [2, `domain`]
		for (int number = 2; number < domain; number++) {
			boolean is_prime = true;

			// Tests if `number` is a prime number
			for (int divisor = 2; divisor <= number / divisor; divisor++) {
				if (number % divisor == 0) {
					is_prime = false;
					break;
				}
			}
			
			if (is_prime == true) {
				primes++;
			}
		}

		StdOut.println(primes);
    }
}
