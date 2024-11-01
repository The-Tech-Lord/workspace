import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<T> implements Iterable<T> {
	private T[] q;  // Queue
    private int n;  // Element count

    // Constructs an empty random queue.
    @SuppressWarnings("unchecked")
    public ResizingArrayRandomQueue() {
        this.q = (T[]) new Object[2];
		this.n = 0;
    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        return this.n == 0 || this.q == null;
    }

    // Returns the number of items in this queue.
    public int size() {
        return this.n;
    }

    // Adds item to the end of this queue.
    public void enqueue(T item) {
        if (item == null)
			throw new NullPointerException("item is null");

		// Double the size of the queue if numbers of items is greater than
		// or equal to the queue size
		if (this.n >= this.q.length)
			this.resize(this.q.length * 2);
		
		this.q[this.n] = item;
		this.n++;
    }

    // Returns a random item from this queue.
    public T sample() {
		if (this.isEmpty())
			throw new NoSuchElementException("Random queue is empty");
		
        return this.q[StdRandom.uniform(this.size())];
    }

    // Removes and returns a random item from this queue.
    public T dequeue() {
        if (this.isEmpty())
			throw new NoSuchElementException("Random queue is empty");
		
		int r_index = StdRandom.uniform(this.size());
		T item = this.q[r_index];

		// Prevent empty holes within queue by replacing the item that was at `r_index`
		// with the last item that's represented in the array, that is not null
		this.q[r_index] = this.q[this.size() - 1];
		this.q[this.size() - 1] = null;
		this.n--;

		// Reduce the memory usage of the array when there are few enough elements
		if (this.size() <= this.q.length / 4)
			this.resize(this.q.length / 2);

		return item;
    }

    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<T> iterator() {
        return new RandomQueueIterator();
    }

    // Returns a string representation of this queue.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return this.n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<T> {
		private T[] items;
		private int current;

        // Constructs an iterator.
        @SuppressWarnings("unchecked")
        public RandomQueueIterator() {
			// Create a copy array
			this.items = (T[]) new Object[ResizingArrayRandomQueue.this.size()];
			this.current = 0;

			// Copy copy contents of outer class's `this.q` into this class's `items`
			for (int i = 0; i < ResizingArrayRandomQueue.this.size(); i++)
				this.items[i] = ResizingArrayRandomQueue.this.q[i];

			StdRandom.shuffle(items);
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
			// Check if `this.current` is over the array length or if the current value is `null`
            if (this.current >= this.items.length || this.items[this.current] == null)
				return false;

			return true;
        }

        // Returns the next item.
        public T next() {
            if (!this.hasNext())
				throw new NoSuchElementException("Iterator is empty");

			T item = this.items[this.current];
			this.current++;
			return item;
        }
    }

    // Resizes the underlying array.
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < this.n; i++) {
            if (this.q[i] != null) {
                temp[i] = this.q[i];
            }
        }
        this.q = temp;
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
