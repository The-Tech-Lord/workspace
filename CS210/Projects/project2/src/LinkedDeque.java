import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<Item> implements Iterable<Item> {
    private int n;       // Deque Size
	private Node first;  // Pointer to first node
	private Node last;   // Pointer to last node

    // Constructs an empty deque.
    public LinkedDeque() {
        this.n = 0;
		this.first = null;
		this.last = null;
    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        if (this.first != null && this.last != null) {
			return false;
		}

		return true;
    }

    // Returns the number of items in this deque.
    public int size() {
        return this.n;
    }

    // Adds item to the front of this deque.
    public void addFirst(Item item) {
		// Corner case
		if (item == null) {
			throw new NullPointerException("item is null");
		}
		
		// Early return and add a Node if Deque is empty
        if (this.first == null) {
			this.first = new Node();

			// Initialize Node
			this.first.item = item;
			this.first.next = null;
			this.first.prev = null;

			// Assuming this.last is also null
			this.last = this.first;
			
			this.n++;
			return;
		}

		// Initialize Node
		Node tempFirst = this.first;
		this.first = new Node();
		this.first.next = tempFirst;
		this.first.prev = null;
		this.first.item = item;
		tempFirst.prev = this.first;

		this.n++;
    }

    // Adds item to the back of this deque.
    public void addLast(Item item) {
		// Corner case
		if (item == null) {
			throw new NullPointerException("item is null");
		}
		
        // Early return and add a Node if Deque is empty
		if (this.last == null) {
			this.last = new Node();

			// Initialize Node
			this.last.item = item;
			this.last.next = null;
			this.last.prev = null;

			// Assuming this.first is also null
			this.first = this.last;

			this.n++;
			return;
		}

		// Initialize Node
		Node tempLast = this.last;
		this.last = new Node();
		tempLast.next = this.last;
		this.last.prev = tempLast;
		this.last.next = null;
		this.last.item = item;
		
		this.n++;
    }

    // Returns the item at the front of this deque.
    public Item peekFirst() {
        if (this.first == null) {
			throw new NoSuchElementException("Deque is empty");
		}

		return this.first.item;
    }

    // Removes and returns the item at the front of this deque.
    public Item removeFirst() {
		// Both values should be null if empty
        if (this.first == null && this.last == null) {
			throw new NoSuchElementException("Deque is empty");
		}
		
		Item item = this.first.item;

		// Move this.first
		Node tempFirst = this.first;
		this.first = this.first.next;

		if (this.first == null) {
			this.n--;
			this.last = null;
			return item;
		}
		
		this.first.prev = null;

		// Prevent Node from straggling
		tempFirst.next = null;
		
		this.n--;
		return item;
    }

    // Returns the item at the back of this deque.
    public Item peekLast() {
        if (this.last == null) {
			throw new NoSuchElementException("Deque is empty");
		}

		return this.last.item;
    }

    // Removes and returns the item at the back of this deque.
    public Item removeLast() {
		// Both values should be null if empty
        if (this.last == null && this.first == null) {
			throw new NoSuchElementException("Deque is empty");
		}

		Item item = this.last.item;

		// Move this.last
		Node tempLast = this.last;
		this.last = this.last.prev;

		if (this.last == null) {
			this.n--;
			this.first = null;
			return item;
		}
		
		this.last.next = null;

		// Prevent Node from straggling
		tempLast.prev = null;

		this.n--;
		return item;
    }

    // Returns an iterator to iterate over the items in this deque from front to back.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // Returns a string representation of this deque.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // A deque iterator.
    private class DequeIterator implements Iterator<Item> {
        private Node current;

        // Constructs an iterator.
        public DequeIterator() {
            this.current = LinkedDeque.this.first;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
			// Null Pointer Checking
			if (this.current == null) {
				return false;
			}

			return true;
        }

        // Returns the next item.
        public Item next() {
            if (this.current == null) {
				throw new NoSuchElementException("Iterator is empty");
			}

			// Return item and move the current pointer
			Item item = this.current.item;
			this.current = this.current.next;
			return item;
        }
    }

    // A data type to represent a doubly-linked list. Each node in the list stores a generic item
    // and references to the next and previous nodes in the list.
    private class Node {
        private Item item;  // the item
        private Node next;  // the next node
        private Node prev;  // the previous node
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its several powers, having " +
                "been originally breathed into a few forms or into one; and that, whilst this " +
                "planet has gone cycling on according to the fixed law of gravity, from so simple" +
                " a beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        StdOut.println("Filling the deque...");
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.printf("The deque (%d characters): ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        StdOut.println("Emptying the deque...");
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println("deque.isEmpty()? " + deque.isEmpty());
    }
}
