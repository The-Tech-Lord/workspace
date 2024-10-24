import stdlib.StdOut;
import stdlib.StdRandom;
import stdlib.StdStats;

public class MinMax {
    // Returns the minimum value in the given linked list.
    public static int min(Node first) {
        Node traverser = first;
		int min = Integer.MAX_VALUE;

		// Traverse through the linked list and compare each element
		// to `min` and assign the value that is smaller than it
		while (traverser != null) {
			if (traverser.item < min)
				min = traverser.item;
			traverser = traverser.next;
		}
		return min;
    }

    // Returns the maximum value in the given linked list.
    public static int max(Node first) {
        Node traverser = first;
		int max = Integer.MIN_VALUE;

		// Traverse through the linked list and compare each element
		// to `max` and assign the value that is smaller than it
		while (traverser != null) {
			if (traverser.item > max)
				max = traverser.item;
			traverser = traverser.next;
		}
		return max;
    }

    // A data type to represent a linked list. Each node in the list stores an integer item and a
    // reference to the next node in the list.
    protected static class Node {
        protected int item;  // the item
        protected Node next; // the next node
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        int[] items = new int[1000];
        for (int i = 0; i < 1000; i++) {
            items[i] = StdRandom.uniform(-10000, 10000);
        }
        Node first = null;
        for (int item : items) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }
        StdOut.println("min(first) == StdStats.min(items)? " + (min(first) == StdStats.min(items)));
        StdOut.println("max(first) == StdStats.max(items)? " + (max(first) == StdStats.max(items)));
    }
}
