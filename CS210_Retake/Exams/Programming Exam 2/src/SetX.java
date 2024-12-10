import dsa.Set;
import java.util.Iterator;
import stdlib.StdOut;

public class SetX<Key extends Comparable<Key>> implements Iterable<Key> {
	private Set<Key> set;

	// Constructs an empty set
	public SetX() {
		this.set = new Set<Key>();
	}

	// Constructs a set given an array of keys
	public SetX(Key[] keys) {
		this.set = new Set<Key>();
		for (int i = 0; i < keys.length; i++)
			this.add(keys[i]);
	}

	// Check if set is empty
	public boolean isEmpty() {
		return this.set.isEmpty();
	}

	// Return size of set
	public int size() {
		return this.set.size();
	}

	// Add a key to the set
	public void add(Key key) {
		this.set.add(key);
	}

	// Check if the key is in the set
	public boolean contains(Key key) {
		return this.set.contains(key);
	}

	// Iterate through the set in sorted order
	public Iterator<Key> iterator() {
		return this.set.iterator();
	}

	// Print set
	public String toString() {
		return this.set.toString();
	}

	// Return a set that doesn't contain keys that are common in both sets
	public SetX<Key> symmetricDifference(SetX<Key> other) {
		SetX<Key> symdiff_set = new SetX<Key>();

		// Iterate through both sets to compare all keys
		for (Key key : other)
			if (!this.set.contains(key))
				symdiff_set.add(key);

		for (Key key : this.set)
			if (!other.contains(key))
				symdiff_set.add(key);

		return symdiff_set;
	}

	public static void main(String[] args) {
		SetX<Character> a = new SetX<Character>(new Character[]{'e', 'i', 'n', 's', 't', 'e', 'i', 'n'});
		SetX<Character> b = new SetX<Character>(new Character[]{'d', 'a', 'r', 'w', 'i', 'n'});
		StdOut.println(a);
		StdOut.println(b);
		StdOut.println(a.symmetricDifference(b));
	}
}
