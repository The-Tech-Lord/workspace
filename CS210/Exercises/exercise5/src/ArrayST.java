import dsa.BasicST;
import dsa.LinkedQueue;
import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Iterator;

public class ArrayST<Key, Value> implements BasicST<Key, Value> {
    private Key[] keys;     // keys in the symbol table
    private Value[] values; // the corresponding values
    private int n;          // number of key-value pairs

    // Constructs an empty symbol table.
    public ArrayST() {
        this.keys = (Key[]) new Object[2];
		this.values = (Value[]) new Object[2];
		this.n = 0;
    }

    // Returns true if this symbol table is empty, and false otherwise.
    public boolean isEmpty() {
        if (this.size() == 0) {
			return true;
		}
		return false;
    }

    // Returns the number of key-value pairs in this symbol table.
    public int size() {
		return this.n;
    }

    // Inserts the key and value pair into this symbol table.
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value is null");
        }

		// Double the size of the array if running out of room
		if (this.keys.length == this.size()) {
			this.resize(this.size() * 2);
		}

		// First check if `key` is already present in the current `keys` array
		if (this.contains(key)) {
			for (int i = 0; i < this.size(); i++) {
				if (key.equals(this.keys[i])) {
					this.values[i] = value;
					return;
				}
			}
		}

		// Because of how I implemented `this.n`, I can use `this.size()` as an array index
		this.keys[this.size()] = key;
		this.values[this.size()] = value;
		this.n++;
    }

    // Returns the value associated with key in this symbol table, or null.
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
		if (this.isEmpty()) {
			return null;
		}

		// Traverse through `keys` array
		for (int i = 0; i < this.size(); i++) {
			if (key.equals(this.keys[i])) {
				return this.values[i];
			}
		}
		return null;
    }

    // Returns true if this symbol table contains key, and false otherwise.
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

		// Traverse through `keys` array
		for (int i = 0; i < this.size(); i++) {
			if (key.equals(this.keys[i])) {
				return true;
			}
		}
		return false;
    }

    // Deletes key and the associated value from this symbol table.
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

		// Traverse through `keys`
		for (int i = 0; i < this.size(); i++) {
			if (this.keys[i] == key) {
				for (int j = i; j < this.size() - 1; j++) {
					// Shift indices of `keys` and `values`
					this.keys[j] = this.keys[j + 1];
					this.values[j] = this.values[j + 1];
				}
				this.keys[this.size() - 1] = null;
				this.values[this.size() - 1] = null;
			}
		}
		this.n--;
    }

    // Returns all the keys in this symbol table.
    public Iterable<Key> keys() {
		// Since I wasn't able to directly return `this.keys` directly, this
		// was a solution that I found that worked. Makes sense too, guess it's like an anonymous class??
        return new Iterable<Key>() {
			public Iterator<Key> iterator() {
				return new Iterator<Key>() {
					private int i;

					public boolean hasNext() {
						if (this.i < ArrayST.this.size()) {
							return true;
						}
						return false;
					}

					public Key next() {
						return ArrayST.this.keys[i++];
					}
				};
			}
		};
    }

    // Resizes the underlying arrays to capacity.
    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Object[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
