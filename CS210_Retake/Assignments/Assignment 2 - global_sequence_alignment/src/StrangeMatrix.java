import stdlib.StdOut;

public class StrangeMatrix {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);
		int[][] strange_matrix = new int[m][n];

		for (int i = 0; i < m; i++) {
			strange_matrix[i][n - 1] = m - i - 1;
		}
		for (int ii = 0; ii < n; ii++) {
			strange_matrix[m - 1][ii] = n - ii - 1;
		}

		for (int i = m - 2; i >= 0; i--) {
			for (int ii = n - 2; ii >= 0; ii--) {
				strange_matrix[i][ii] = strange_matrix[i][ii + 1] + strange_matrix[i + 1][ii + 1] + strange_matrix[i + 1][ii];
			}
		}

		for (int i = 0; i < m; i++) {
			for (int ii = 0; ii < n - 1; ii++) {
				StdOut.printf("%5d ", strange_matrix[i][ii]);
			}
			StdOut.printf("%5d\n", strange_matrix[i][strange_matrix[0].length - 1]);
		}
    }
}
