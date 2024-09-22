import stdlib.StdOut;

public class Triangle {
    // Entry Point.
    public static void main(String[] args) {
		// I believe these represent the sides of a triangle given the program's context
        int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int z = Integer.parseInt(args[2]);

		// Checks if the if each of the numbers add up to be less than or equal to the other 2 numbers
		boolean inequality_check_flag = false;
		if (x <= y + z && y <= x + z && z <= x + y) {
			inequality_check_flag = true;
		}
		StdOut.println(inequality_check_flag);
    }
}
