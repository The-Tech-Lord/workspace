import dsa.MinPQ;
import stdlib.StdOut;

public class Ramanujan2 {
    // Entry point.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
		MinPQ<Pair> pq = new MinPQ<Pair>();

		// Insert a bunch of pairs into `pq`
		for (int i = 1; Math.pow(i, 3) < n; i++) {
			pq.insert(new Pair(i, i + 1));
		}

		// Declare `prev` and `curr`
		Pair prev = new Pair(0, 0);
		Pair curr = new Pair(0, 0);
		
		while (!pq.isEmpty()) {
			prev = curr;
			curr = pq.delMin();

			// Create variables holding the left and right side to make code easier to read
			int left_result = prev.i * prev.i * prev.i + prev.j * prev.j * prev.j;
			int right_result = curr.i * curr.i * curr.i + curr.j * curr.j * curr.j;

			// If true, print out expression of Ramanujan number
			if (left_result == right_result && left_result <= n && right_result <= n) {
				StdOut.println(left_result + " = " + prev.i + "^3 + " + prev.j + "^3 = " + curr.i + "^3 + " + curr.j + "^3");
			}

			// If `curr.j` is less than n^1/3, insert a new pair
			// I have this set so that it was if both sides were multiplied by the power of 3,
			// canceling out the cube root
			if (Math.pow(curr.j, 3) < n) {
				pq.insert(new Pair(curr.i, curr.j + 1));
			}
		}
    }

    // A data type that encapsulates a pair of numbers (i, j) and the sum of their cubes.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first number in the pair
        private int j;          // second number in the pair
        private int sumOfCubes; // i^3 + j^3

        // Constructs a pair (i, j).
        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Returns a comparison of pairs this and other based on their sum-of-cubes values.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }
    }
}
