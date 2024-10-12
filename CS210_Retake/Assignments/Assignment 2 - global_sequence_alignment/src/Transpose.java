import stdlib.StdIn;
import stdlib.StdOut;

public class Transpose {
    // Entry point.
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);
		double[][] transpose_array = new double[n][m];
		double[] inputs = StdIn.readAllDoubles();

		if (inputs.length < m * n || inputs.length > m * n) {
			StdOut.println(inputs.length);
			return;
		}

		int index = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				transpose_array[j][i] = inputs[index];
				index++;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				StdOut.printf("%.6f", transpose_array[i][j]);

				if (j == m - 1) {
					break;
				}

				StdOut.print(" ");
			}
			StdOut.println();
		}
    }
}
