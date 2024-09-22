import stdlib.StdOut;

public class Quadratic {
    // Entry point.
    public static void main(String[] args) {
        double a = Double.parseDouble(args[0]);
		double b = Double.parseDouble(args[1]);
		double c = Double.parseDouble(args[2]);

		// Denominator of the equation cannot be 0
		if (a == 0) {
			StdOut.println("Value of a must not be 0");
			return;
		}

		// b^2 - 4ac
		double square_root_numerator = Math.pow(b, 2.0) - 4 * a * c;
		
		if (square_root_numerator < 0) {
			StdOut.println("Value of discriminant must not be negative");
			return;
		}

		// Calculates the negative and positive roots with the root finding quadratic equation
		double negative_root = (-b - Math.sqrt(square_root_numerator)) / (2 * a);
		double positive_root = (-b + Math.sqrt(square_root_numerator)) / (2 * a);

		StdOut.println(positive_root + " " + negative_root);
    }
}
