import stdlib.StdOut;

public class GCD {
    // Entry point.
    public static void main(String[] args) {
        int p = Integer.parseInt(args[0]);
		int q = Integer.parseInt(args[1]);

		// Calculates the greatest common divisor of `p` and `q`
		while (p % q != 0) {
			int temp = p;
			p = q;
			q = temp % q;
		}

		StdOut.println(q);
    }
}
