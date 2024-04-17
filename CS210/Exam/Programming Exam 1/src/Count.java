import stdlib.StdIn;
import stdlib.StdOut;

public class Count {
	// Entry point
	public static void main(String[] args) {
		String s = args[0];
		int l = Integer.parseInt(args[1]);
		String[] a = StdIn.readAllStrings();
		StdOut.println(stringsOfLength(a, l));
		StdOut.println(frequencyOf(a, s));
	}

	private static int stringsOfLength(String[] a, int l) {
		int count = 0;

		// Iterate through `a[]` and increment `count`
		for (String str : a) {
			if (str.length() >= l) {
				count++;
			}
		}

		return count;
	}

	private static int frequencyOf(String[] a, String s) {
		int frequency = 0;

		// Iterate through `a[]` and increment `frequency`
		for (String str : a) {
			if (str.equals(s) == true) {
				frequency++;
			}
		}

		return frequency;
	}
}
