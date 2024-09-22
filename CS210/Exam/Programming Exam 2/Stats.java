import stdlib.In;
import stdlib.StdOut;

public class Stats {
	public static void main(String[] args) {
		IntSet a = new IntSet();
		IntSet b = new IntSet();

		// Read `args[0]` and input into `a`
		In in = new In(args[0]);
		while (!in.isEmpty()) {
			int key = in.readInt();
			a.add(key);
		}

		// Read `args[1]` and input into `b`
		in = new In(args[1]);
		while (!in.isEmpty()) {
			int key = in.readInt();
			b.add(key);
		}

		// Output
		StdOut.println(a.size());
		StdOut.println(b.size());
		StdOut.println(a.intersection(b).size());
	}
}
