import stdlib.StdOut;

public class Fibonacci {
    // Entry point.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

		// The Fibonacci calculations work by adding `prev_num` and `next_num`, storing them in `fib_number`.
		// `next_num` then stores `fib_number` and `prev_num` becomes what was `next_num`, essentially moving
		// down the Fibonacci sequence
		int prev_num = 0;
		int next_num = 1;
		int fib_number = 0;
		
		for (int i = 0; i <= n - 2; i++) {
			fib_number = prev_num + next_num;
			
			int temp = next_num;
			next_num = fib_number;
			prev_num = temp;
		}

		StdOut.println(fib_number);
    }
}
