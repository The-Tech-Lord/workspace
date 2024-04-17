import dsa.LinkedStack;

import stdlib.StdIn;
import stdlib.StdOut;

public class Sort {
    // Entry point.
    public static void main(String[] args) {
        LinkedDeque<String> queue = new LinkedDeque<String>();

		while (!StdIn.isEmpty()) {
			String input = StdIn.readString();

			// If queue is empty
			if (queue.isEmpty()) {
				queue.addFirst(input);
				continue;
			}

			// Checks if the current word/character is less than another character in the current queue
			if (less(input, queue.peekFirst())) {
				queue.addFirst(input);
			} else if (!less(input, queue.peekLast())) {
				queue.addLast(input);
			} else {
				LinkedStack<String> temp = new LinkedStack<String>();

				// Remove values up to the value that is greater than itself, or the value that is also the same as itself
				while (!less(input, queue.peekFirst())) {
					temp.push(queue.removeFirst());
				}

				// Finally add it to the beginning of the queue as everything is now ordered in queue
				// (as well as the contents of the temp LinkedStack object)
				queue.addFirst(input);

				// Start popping values from the temp LinkedStack object into queue
				for (String s : temp) {
					queue.addFirst(temp.pop());
				}
			}
		}

		// Print queue
		for (String s : queue) {
			StdOut.println(s);
		}
    }

    // Returns true if v is less than w according to their lexicographic order, and false otherwise.
    private static boolean less(String v, String w) {
        return v.compareTo(w) < 0;
    }
}
