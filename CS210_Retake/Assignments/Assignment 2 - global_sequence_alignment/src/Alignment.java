import stdlib.StdArrayIO;
import stdlib.StdIn;
import stdlib.StdOut;

public class Alignment {
	public static int optimal_alignment(char nucleic_a, char nucleic_b, int base, int diagonal_score, int bottom_score, int right_score) {
		// Compares if the current index is equal to other scores, therefore
		// calculating the optimal alignment
		if (base == right_score + 2)
			return 0;

		if (base == bottom_score + 2)
			return 1;
		
		if (base == diagonal_score)
			return 2;

		if (base == diagonal_score + 1)
			return 3;
		
		return -1;
	}
	
    // Entry point.
    public static void main(String[] args) {
        String sequence_x = StdIn.readString();  // First DNA sequence
		String sequence_y = StdIn.readString();  // Second DNA sequence

		// Read standard output from previous program to set `opt[][]`
		int[][] opt = StdArrayIO.readInt2D();

		// Print alignment
		StdOut.println(opt[0][0]);
		for (int i = 0; i < sequence_x.length();) {
			for (int j = 0; j < sequence_y.length();) {
				int flag = optimal_alignment(sequence_x.charAt(i), sequence_y.charAt(j), opt[i][j], opt[i + 1][j + 1], opt[i + 1][j], opt[i][j + 1]);
				int penalty = 0;

				// Comparing the returned flag and giving an output that shows the alignment of nucleic acids
				switch (flag) {
				case 0:
					penalty = 2;
					StdOut.println("- " + sequence_y.charAt(j) + " " + penalty);
					j++;
					break;
				case 1:
					penalty = 2;
					StdOut.println(sequence_x.charAt(i) + " - " + penalty);
					i++;
					break;
				case 2:
					penalty = 0;
					StdOut.println(sequence_x.charAt(i) + " " + sequence_y.charAt(j) + " " + penalty);
					i++;
					j++;
					break;
				case 3:
					penalty = 1;
					StdOut.println(sequence_x.charAt(i) + " " + sequence_y.charAt(j) + " " + penalty);
					i++;
					j++;
					break;
				case -1:
					StdOut.println("how");
				}
			}
		}
    }
}
