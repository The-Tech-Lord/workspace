import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item[] q;  // Queue array
	private int n;     // Size of queue array

    // Constructs an empty random queue.
    public ResizingArrayRandomQueue() {
		this.n = 0;
        this.q = (Item[]) new Object[2];
    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        if (this.q == null || this.size() == 0) {
			return true;
		}

		return false;
    }

    // Returns the number of items in this queue.
    public int size() {
        return this.n;
    }

    // Adds item to the end of this queue.
    public void enqueue(Item item) {
		// Corner Case
		if (item == null) {
			throw new NullPointerException("item is null");
		}
		
		// Full array check
        if (this.n == this.q.length) {
			this.resize(this.q.length * 2);
		}

		// Assign value
		this.q[n] = item;

		// Increment array size
		this.n++;
    }

    // Returns a random item from this queue.
    public Item sample() {
		// Corner Case
		if (this.n == 0) {
			throw new NoSuchElementException("Random queue is empty");
		}
		
		// Return a random index value from q
        int r = StdRandom.uniform(this.n);
		return q[r];
    }

    // Removes and returns a random item from this queue.
    public Item dequeue() {
		// Corner Case
		if (this.n == 0) {
			throw new NoSuchElementException("Random queue is empty");
		}
		
		// Save Item
		int r = StdRandom.uniform(this.n);
        Item item = this.q[r];

		// Manipulate and move around values
		this.q[r] = this.q[n - 1];
		this.q[n - 1] = null;
		this.n--;

		// Check size of array and reduce if needed
		if (this.n <= this.q.length / 4) {
			this.resize(this.q.length / 2);
		}

		return item;
    }

    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // Returns a string representation of this queue.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] items;  // Copy, soon shuffled, of q
		private int current;   // Index of current item

        // Constructs an iterator.
        public RandomQueueIterator() {
            this.current = 0;
			this.items = (Item[]) new Object[ResizingArrayRandomQueue.this.n];

			// Copy values from q into items
			for (int i = 0; i < ResizingArrayRandomQueue.this.n; i++) {
				this.items[i] = ResizingArrayRandomQueue.this.q[i];
			}

			// Shuffle items
			StdRandom.shuffle(this.items);
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            if (this.current < ResizingArrayRandomQueue.this.n) {
				return true;
			}

			return false;
        }

        // Returns the next item.
        public Item next() {
			// Corner Case
			if (this.current >= this.items.length) {
				throw new NoSuchElementException("Iterator is empty");
			}
			
            Item item = this.items[current];
			this.current++;
			return item;
        }
    }

    // Resizes the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int r = StdRandom.uniform(10000);
            q.enqueue(r);
            sum += r;
        }
        int iterSumQ = 0;
        for (int x : q) {
            iterSumQ += x;
        }
        int dequeSumQ = 0;
        while (q.size() > 0) {
            dequeSumQ += q.dequeue();
        }
        StdOut.println("sum       = " + sum);
        StdOut.println("iterSumQ  = " + iterSumQ);
        StdOut.println("dequeSumQ = " + dequeSumQ);
        StdOut.println("iterSumQ + dequeSumQ == 2 * sum? " + (iterSumQ + dequeSumQ == 2 * sum));
    }
}
