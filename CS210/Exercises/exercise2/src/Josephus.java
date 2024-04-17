import dsa.LinkedQueue;
import stdlib.StdOut;

public class Josephus {
    // Entry point.
    public static void main(String[] args) {
        // Accept n (int) and m (int) as command-line arguments.
        int PeopleNum = Integer.parseInt(args[0]); // n
		int KillSteps = Integer.parseInt(args[1]); // m

        // Create a queue q and enqueue integers 1, 2, ..., n.
        LinkedQueue<Integer> numbers = new LinkedQueue<Integer>();
		for (Integer i = 1; i <= PeopleNum; i++) {
			numbers.enqueue(i);
		}

        // Set i to 0. As long as q is not empty: increment i; dequeue an element (say pos); if m
        // divides i, write pos to standard output, otherwise enqueue pos to q.
        int i = 0;
		while (!numbers.isEmpty()) {
			i++;
			int pos = numbers.dequeue();
			if (i % KillSteps == 0) {
				StdOut.println(pos);
			} else {
				numbers.enqueue(pos);
			}
		}
    }
}
