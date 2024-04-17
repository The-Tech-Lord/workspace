import stdlib.StdOut;

public class Sample {
    // Entry point.
    public static void main(String[] args) {
		// Input
        int lo = Integer.parseInt(args[0]);        // Low bound
		int hi = Integer.parseInt(args[1]);        // High bound
		int k = Integer.parseInt(args[2]);         // Sample number
		String mode = args[3]; // Mode (Replacement or Non-replacement)

		// Fill queue with integers from [lo, hi]
		ResizingArrayRandomQueue<Integer> queue = new ResizingArrayRandomQueue<Integer>();
		for (int i = lo; i <= hi; i++) {
			queue.enqueue(i);
		}

		// Mode Check and Output
		if (mode.equals("+")) {
			// Replacement mode sampling
			for (int i = 0; i < k; i++) {
				StdOut.println(queue.sample());
			}
		} else if (mode.equals("-")) {
			// Non-replacement mode sampling
			for (int i = 0; i < k; i++) {
				StdOut.println(queue.dequeue());
			}
		} else {
			throw new IllegalArgumentException("Illegal mode");
		}
    }
}
