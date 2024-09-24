import stdlib.StdIn;
import stdlib.StdOut;

public class Reverse {
    // Entry point.
    public static void main(String[] args) {
        String[] input = StdIn.readAllStrings();
		for (int i = 0; i < input.length / 2; i++) {
			String temp = input[i];
			input[i] = input[input.length - i - 1];
			input[input.length - i - 1] = temp;
		}
		for (int i = 0; i < input.length; i++) {
			StdOut.print(input[i] + " ");
		}
		StdOut.println();
    }
}
