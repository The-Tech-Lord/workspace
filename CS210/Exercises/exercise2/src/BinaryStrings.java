import java.util.Iterator;

import stdlib.StdOut;

// An immutable data type to systematically iterate over binary strings of length n.
public class BinaryStrings implements Iterable<String> {
    private int n; // need all binary strings of length n

    // Constructs a BinaryStrings object given the length of binary strings needed.
    public BinaryStrings(int n) {
        this.n = n;
    }

    // Returns an iterator to iterate over binary strings of length n.
    public Iterator<String> iterator() {
        return new BinaryStringsIterator();
    }

    // Binary strings iterator.
    private class BinaryStringsIterator implements Iterator<String> {
        private int count; // number of binary strings returned so far
        private int p;     // current number in decimal

        // Constructs an iterator.
        public BinaryStringsIterator() {
			this.count = 0;
			this.p = 0;
        }

        // Returns true if there are anymore binary strings to be iterated, and false otherwise.
        public boolean hasNext() {
            if (binary(p).length() > n) {
				return false;
			}
			return true;
        }

        // Returns the next binary string.
        public String next() {
            String str = binary(p);
			p++;
			count++;
			return str;
        }

        // Returns the n-bit binary representation of x.
        private String binary(int x) {
            String str = Integer.toBinaryString(x);
            int padding = n - str.length();
            for (int i = 1; i <= padding; i++) {
                str = "0" + str;
            }
            return str;
        }
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (String s : new BinaryStrings(n)) {
            StdOut.println(s);
        }
    }
}
