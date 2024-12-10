import stdlib.In;
import stdlib.StdOut;

public class WordStats {
	public static void main(String[] args) {
		// Setup input streams
		In a = new In(args[0]);
		In b = new In(args[1]);

		// Read input streams as string arrays and input those to the `SetX` constructors to create new sets
		SetX<String> set_a = new SetX<String>(a.readAllStrings());
		SetX<String> set_b = new SetX<String>(b.readAllStrings());

		// Output
		StdOut.println(set_a.size());
		StdOut.println(set_b.size());
		StdOut.println(set_a.symmetricDifference(set_b).size());
	}
}
