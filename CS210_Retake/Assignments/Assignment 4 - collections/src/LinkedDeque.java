import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<T> implements Iterable<T> {
	private Node front;
	private Node last;
    private int count;

    // Constructs an empty deque.
    public LinkedDeque() {
        this.count = 0;
		this.front = null;
		this.last = null;
    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        return this.front == null && this.last == null;
    }

    // Returns the number of items in this deque.
    public int size() {
        return this.count;
    }

    // Adds item to the front of this deque.
    public void addFirst(T item) {
        if (item == null)
			throw new NullPointerException("item is null");

		Node node = new Node();
		node.item = item;

		if (this.isEmpty()) {
			node.next = node.prev = null;
			this.front = this.last = node;
			this.count++;
		} else {
			node.next = this.front;
			this.front.prev = node;
			this.front = node;
			this.count++;
		}
    }

    // Adds item to the back of this deque.
    public void addLast(T item) {
        if (item == null)
			throw new NullPointerException("item is null");

		// Initialize new node
		Node node = new Node();
		node.item = item;

		// Point `this.front` and `this.last` to node if queue is empty
		// else, add `node` to the end of the queue
		if (this.isEmpty()) {
			node.next = node.prev = null;
			this.front = this.last = node;
			this.count++;
		} else {
			node.next = null;
			node.prev = this.last;
			this.last.next = node;
			this.last = node;
			this.count++;
		}
    }

    // Returns the item at the front of this deque.
    public T peekFirst() {
		if (this.isEmpty())
			throw new NoSuchElementException("Deque is empty");
        return this.front.item;
    }

    // Removes and returns the item at the front of this deque.
    public T removeFirst() {
        if (this.isEmpty())
			throw new NoSuchElementException("Deque is empty");

		// Initialize a temporary front node pointer
		Node tempFront = this.front;
		T value = this.front.item;

		StdOut.println("FIRST Prev " + this.front.prev);
		StdOut.println("FIRST Curr " + this.front.next);
		this.front = this.front.next;
		StdOut.println("FIRST Next " + this.front.next);
		StdOut.println("FIRST Last " + this.last);
		StdOut.println("FIRST Last Prev " + this.last.prev);
		this.front.prev = tempFront.next = tempFront.prev = null;
		StdOut.println("FIRST Front New " + this.front.prev);

		--this.count;
		StdOut.println(count);
		return value;
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
		T value = this.last.item;

		StdOut.println("LAST Prev " + this.last.prev);
		StdOut.println("LAST Curr " + this.last);
		this.last = this.last.prev;
		StdOut.println("LAST Last " + this.last);
		StdOut.println("LAST Last Prev " + this.last.prev);
		this.last.next = tempLast.prev = tempLast.next = null;
		StdOut.println("LAST Last New  " + this.last.next);
		
		--this.count;
		StdOut.println(count);
		return value;
    }

    // Returns an iterator to iterate over the items in this deque from front to back.
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
        private Node pointer;

        // Constructs an iterator.
        public DequeIterator() {
            this.pointer = LinkedDeque.this.front;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return pointer.next == null;
        }

        // Returns the next item.
        public T next() {
            if (!this.hasNext())
				throw new NoSuchElementException("Iterator is empty");
			T item = this.pointer.item;
			this.pointer = this.pointer.next;
			StdOut.println(item);
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
