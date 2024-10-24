import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<T> implements Iterable<T> {
    ...

    // Constructs an empty random queue.
    @SuppressWarnings("unchecked")
    public ResizingArrayRandomQueue() {
        ...
    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        ...
    }

    // Returns the number of items in this queue.
    public int size() {
        ...
    }

    // Adds item to the end of this queue.
    public void enqueue(T item) {
        ...
    }

    // Returns a random item from this queue.
    public T sample() {
        ...
    }

    // Removes and returns a random item from this queue.
    public T dequeue() {
        ...
    }

    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<T> iterator() {
        ...
    }

    // Returns a string representation of this queue.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<T> {
        ...

        // Constructs an iterator.
        @SuppressWarnings("unchecked")
        public RandomQueueIterator() {
            ...
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            ...
        }

        // Returns the next item.
        public T next() {
            ...
        }
    }

    // Resizes the underlying array.
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
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
