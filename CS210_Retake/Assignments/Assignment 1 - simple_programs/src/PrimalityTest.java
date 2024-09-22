import stdlib.StdOut;

public class PrimalityTest {
    // Entry point.
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
		boolean prime = true;

		// Tests a number's primality by dividing with `i`
		for (int i = 2; i <= number / i; i++) {
			if (number % i == 0) {
				prime = false;
				break;
			}
		}
		StdOut.println(prime);
    }
}
