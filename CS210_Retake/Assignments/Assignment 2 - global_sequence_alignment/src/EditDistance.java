import stdlib.StdIn;
import stdlib.StdOut;

public class EditDistance {
	public static int minimum_distance(char nucleic_a, char nucleic_b, int diagonal_score, int bottom_score, int right_score) {
		// The if-statement checks if two nucleic acids are already aligned
		if (nucleic_a != nucleic_b)
			diagonal_score++;
		bottom_score +=2;
		right_score += 2;

		// Comparing `right_score`, `bottom-score`, and `diagonal_score` with each other
		// to find the smallest value
		if (right_score <= bottom_score && right_score <= diagonal_score)
			return right_score;
		
		if (diagonal_score <= bottom_score && diagonal_score <= right_score)
			return diagonal_score;

		// If the other comparisons fail, then it has to be `bottom_score`
		return bottom_score;
	}
	
    // Entry point.
    public static void main(String[] args) {
        String sequence_x = StdIn.readString();  // First DNA sequence
		String sequence_y = StdIn.readString();  // Second DNA sequence

		// Setting up `opt[][]` dimensions
		int opt_height = sequence_x.length() + 1;
		int opt_width = sequence_y.length() + 1;
		int[][] opt = new int[opt_height][opt_width];

		// Prepare `opt[i][j]` last column and row
		for (int i = opt_height - 1; i >= 0; i--) {
			opt[i][opt_width - 1] = 2 * (opt_height - (i + 1));
		}
		
		for (int j = opt_width - 1; j >= 0; j--) {
			opt[opt_height - 1][j] = 2 * (opt_width - (j + 1));
		}

		// Calculate `opt[i][j]` edit distances
		for (int i = opt_height - 2; i >= 0; i--) {
			for (int j = opt_width - 2; j >= 0; j--) {
				opt[i][j] = minimum_distance(sequence_x.charAt(i), sequence_y.charAt(j), opt[i + 1][j + 1], opt[i + 1][j], opt[i][j + 1]);
			}
		}

		// Print the genomes and `opt[][]` dimensions
		StdOut.println(sequence_x);
		StdOut.println(sequence_y);
		StdOut.println(opt_height + " " + opt_width);
		
		// Print out the contents of `opt[][]`
		for (int i = 0; i < opt_height; i++) {
			for (int j = 0; j < opt_width - 1; j++) {
				StdOut.printf("%3d ", opt[i][j]);
			}
			StdOut.printf("%3d\n", opt[i][opt_width - 1]);
		}
    }
}
