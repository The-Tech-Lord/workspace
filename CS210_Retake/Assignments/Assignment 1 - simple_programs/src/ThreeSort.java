import stdlib.StdOut;

public class ThreeSort {
    // Entry Point.
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int z = Integer.parseInt(args[2]);

		// Find and store the min and max of `x`, `y`, and `z`
		int min = Math.min(x, Math.min(y, z));
		int max = Math.max(x, Math.max(y, z));

		// Stores the middle value
		int middle = 0;

		// Sorting
		if (x > min && x < max) {
			middle = x;
		}		
		if (y > min && y < max) {
			middle = y;
		}
		if (z > min && z < max) {
			middle = z;
		}

		StdOut.println(min + " " + middle + " " + max);
    }
}
