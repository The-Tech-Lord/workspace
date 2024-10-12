import stdlib.StdOut;

public class Pascal {
    // Entry point.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
		int[][] pascal_triangle = new int[n + 1][];

		for (int i = 0; i <= n; i++)
			pascal_triangle[i] = new int[i + 1];

		for (int i = 0; i <= n; i++) {
			pascal_triangle[i][0] = 1;
			pascal_triangle[i][i] = 1;
			for (int j = 1; j < i; j++) {
				pascal_triangle[i][j] = pascal_triangle[i - 1][j - 1] + pascal_triangle[i - 1][j];
			}
		}

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < pascal_triangle[i].length; j++) {
				StdOut.print(pascal_triangle[i][j]);

				if (j == pascal_triangle[i].length - 1) {
					break;
				}

				StdOut.print(" ");
			}
			StdOut.println();
		}
    }
}
