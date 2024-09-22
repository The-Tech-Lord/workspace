import stdlib.StdOut;

public class RamanujanNumbers {
    // Entry point.
    public static void main(String[] args) {
        int bounds = Integer.parseInt(args[0]);

		// Finds numbers where the variable's cubed forms in the equation a^3 + b^3 = c^3 + d^3 equal each other, where a and b are different from c and d
		for (int a = 1; Math.pow(a, 3) <= bounds; a++) {
			for (int b = a + 1; Math.pow(b, 3) <= bounds - Math.pow(a, 3); b++) {
				for (int c = a + 1; Math.pow(c, 3) <= bounds; c++) {
					for (int d = c + 1; Math.pow(d, 3) <= bounds - Math.pow(c, 3); d++) {
						// Created these variables so that doing comparisons would be easier
						int left_hand = a * a * a + b * b * b;
						int right_hand = c * c * c + d * d * d;

						// Checks if both sides of the equation are equal to each other and are less than `bounds`
						if (left_hand == right_hand && left_hand <= bounds && right_hand <= bounds) {
							int ramanujan_number = left_hand;
							StdOut.println(ramanujan_number + " = " + a + "^3 + " + b + "^3 = " + c + "^3 + " + d + "^3");
						}
					}
				}
			}
		}
    }
}
