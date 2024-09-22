import dsa.Set;
import java.util.Iterator;
import stdlib.StdOut;

public class IntSet implements Iterable<Integer> {
	private Set<Integer> set;  // The underlying set

	public IntSet() {
		this.set = new Set<Integer>();
	}
	
	public IntSet(int[] keys) {
		this.set = new Set<Integer>();

		for (int key : keys) {
			this.add(key);
		}
	}

	// Return a boolean to whether the set is empty
	public boolean isEmpty() {
		return this.set.size() == 0;
	}

	// Return the size of the set
	public int size() {
		return this.set.size();
	}

	// Add an element to the set
	public void add(int key) {
		this.set.add(key);
	}

	public boolean contains(int key) {
		return this.set.contains(key);
	}

	public IntSet union(IntSet other) {
		IntSet union = new IntSet();

		// Iterate through both `this` and `other` and add the elements to `union`
		for (int key : this) {
			union.add(key);
		}
		for (int key : other) {
			union.add(key);
		}
		
		return union;
	}

	public IntSet intersection(IntSet other) {
		IntSet intersection = new IntSet();

		// Iterate through both `this` and `other` and add elements they BOTH share to `intersection`
		for (int key : other) {
			if (this.contains(key)) {
				intersection.add(key);
			}
		}

		return intersection;
	}

	// Returns an iterator for the underlying set
	public Iterator<Integer> iterator() {
		return set.iterator();
	}

	// Returns a string representation of the `IntSet` object
	public String toString() {
		return set.toString();
	}

	public static void main(String[] args) {
		IntSet a = new IntSet(new int[]{1, 5, 0, 8, 8, 5, 7, 6, 5, 3});
		IntSet b = new IntSet(new int[]{0, 9, 8, 4, 5, 9, 7, 6, 2, 5});
		StdOut.println(a);
		StdOut.println(b);
		StdOut.println(a.union(b));
		StdOut.println(a.intersection(b));
	}
}
