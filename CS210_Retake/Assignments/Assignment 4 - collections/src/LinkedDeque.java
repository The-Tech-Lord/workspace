import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<T> implements Iterable<T> {
	private Node first;
	private Node last;
    private int n;  // The number of double-linked elements

    // Constructs an empty deque.
    public LinkedDeque() {
        this.n = 0;
		this.first = null;
		this.last = null;
    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        return (this.first == null && this.last == null);
    }

    // Returns the number of items in this deque.
    public int size() {
        return this.n;
    }

    // Adds item to the first of this deque.
    public void addFirst(T item) {
        if (item == null)
			throw new NullPointerException("item is null");

		Node node = new Node();
		node.item = item;

		if (this.isEmpty()) {
			this.first = this.last = node;
			node.next = node.prev = null;
			this.n++;
			return;
		}
		
		node.next = this.first;
		this.first.prev = node;
		this.first = node;
		this.first.prev = null;
		this.n++;
    }

    // Adds item to the back of this deque.
    public void addLast(T item) {
        if (item == null)
			throw new NullPointerException("item is null");

		// Initialize new node
		Node node = new Node();
		node.item = item;

		// Point `this.first` and `this.last` to node if queue is empty
		// else, add `node` to the end of the queue
		if (this.isEmpty()) {
			this.first = this.last = node;
			node.next = node.prev = null;
			this.n++;
			return;
		}
		
		node.next = null;
		node.prev = this.last;
		this.last.next = node;
		this.last = node;
		this.n++;
    }

    // Returns the item at the first of this deque.
    public T peekFirst() {
		if (this.isEmpty())
			throw new NoSuchElementException("Deque is empty");
        return this.first.item;
    }

    // Removes and returns the item at the first of this deque.
    public T removeFirst() {
        if (this.isEmpty())
			throw new NoSuchElementException("Deque is empty");

		// Initialize a temporary first node pointer
		Node tempFirst = this.first;
		T popped_value = this.peekFirst();
		this.first = this.first.next;

		// Check if `this.first` is now `null` to resolve errors resulting in
		// actions done on `null` value pointers
		if (this.first == null) {
			this.last = null;
			this.n--;
			return popped_value;
		}
		
		this.first.prev = tempFirst.next = null;
		this.n--;
		
		return popped_value;
    }

    // Returns the item at the back of this deque.
    public T peekLast() {
        if (this.isEmpty())
			throw new NoSuchElementException("Deque is empty");
		return this.last.item;
    }

    // Removes and returns the item at the back of this deque.
    public T removeLast() {
        if (this.isEmpty())
			throw new NoSuchElementException("Deque is empty");

		Node tempLast = this.last;
		T popped_value = this.peekLast();
		this.last = this.last.prev;

		// Check if `this.first` is now `null` to resolve errors resulting in
		// actions done on `null` value pointers
		if (this.last == null) {
			this.first = null;
			this.n--;
			return popped_value;
		}
		
		this.last.next = tempLast.prev = null;
		this.n--;
		
		return popped_value;
    }

    // Returns an iterator to iterate over the items in this deque from first to back.
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    // Returns a string representation of this deque.
    public String toString() {
        String s = "";
        for (T item : this) {
            s += item + ", ";
        }
        return this.isEmpty() ? s + "[]" : "[" + s.substring(0, s.length() - 2) + "]";
    }

    // A deque iterator.
    private class DequeIterator implements Iterator<T> {
        private Node current;

        // Constructs an iterator.
        public DequeIterator() {
            this.current = LinkedDeque.this.first;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
			if (this.current == null)
				return false;

            return true;
        }

        // Returns the next item.
        public T next() {
            if (this.current == null)
				throw new NoSuchElementException("Iterator is empty");
			
			T item = this.current.item;
			this.current = this.current.next;
			
			return item;
        }
    }

    // A data type to represent a doubly-linked list. Each node in the list stores a generic item
    // and references to the next and previous nodes in the list.
    private class Node {
        private T item;  // the item
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
