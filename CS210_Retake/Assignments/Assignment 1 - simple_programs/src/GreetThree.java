import stdlib.StdOut;

public class GreetThree {
    // Entry Point.
    public static void main(String[] args) {
		// Input names
        String name1 = args[0];
		String name2 = args[1];
		String name3 = args[2];

		// Output sentence
		StdOut.println("Hi " + name3 + ", " + name2 + ", and " + name1 + ".");
    }
}
