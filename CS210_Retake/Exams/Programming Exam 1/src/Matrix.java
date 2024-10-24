import stdlib.StdArrayIO;

public class Matrix {
	private static double[][] transpose(double[][] a) {
		// Get lengths and create a new transposed array
		int n = a[0].length;
		int m = a.length;
		double[][] tranposed_array = new double[n][m];

		// Transpose `a` and insert values into `transposed_array`
		for (int j = 0; j < m; j++)
			for (int i = 0; i < n; i++)
				tranposed_array[i][j] = a[j][i];
		return tranposed_array;
	}

	public static void main(String[] args) {
		double[][] a = {{1, 2}, {3, 4}};
		StdArrayIO.print(transpose(a));
	}
}
