import dsa.Quick;

import java.util.Comparator;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class Autocomplete {
    Term[] terms;

    // Constructs an Autocomplete data structure from an array of terms.
    public Autocomplete(Term[] terms) {
		if (terms == null)
			throw new NullPointerException("terms is null");

		// Copy `terms` into `this.terms`
		this.terms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++)
			this.terms[i] = terms[i];

		// Sort `this.terms` lexicographically
		for (int i = 0; i < this.terms.length; i++) {
			for (int j = i + 1; j < this.terms.length - 1; j++) {
				int lexicographic_compare = this.terms[i].compareTo(this.terms[j]);

				// Swaps terms
				if (lexicographic_compare > 0) {
					Term temp = this.terms[i];
					this.terms[i] = this.terms[j];
					this.terms[j] = temp;
				}
			}
		}
    }

    // Returns all terms that start with prefix, in descending order of their weights.
    public Term[] allMatches(String prefix) {
		int matches = this.numberOfMatches(prefix);
		Term[] matched = new Term[matches];
		Comparator<Term> ReverseWeightOrderCompare = Term.reverseWeightOrder();
		Quick.sort(matched, ReverseWeightOrderCompare);
		return matched;
    }

    // Returns the number of terms that start with prefix.
    public int numberOfMatches(String prefix) {
		if (prefix == null) {
			throw new NullPointerException("prefix is null");
		}
		if (this.terms == null) {
			throw new NullPointerException("terms is null");
		}
		if (this.terms.length == 0) {
			return 0;
		}

		// Number of terms that match `prefix`
		int matches = 0;

		// Create a prefix object
		Term rprefix = new Term(prefix);
		
        // Create a Comparator object to get Term objects' indices and range
		Comparator<Term> PrefixOrder = Term.prefixOrder(prefix.length());
		int firstPrefixIndex = BinarySearchDeluxe.firstIndexOf(this.terms, rprefix, PrefixOrder);
		int lastPrefixIndex = BinarySearchDeluxe.lastIndexOf(this.terms, rprefix, PrefixOrder);

		// Check if the found indices are different, the same, or non-existent
		if (firstPrefixIndex != -1 && lastPrefixIndex != -1 && firstPrefixIndex != lastPrefixIndex) {
			matches += 2;
		} else if (firstPrefixIndex == 0 && lastPrefixIndex == 0) {
			return 1;
		} else {
			return 0;
		}

		for (int i = 0; i < lastPrefixIndex && i > firstPrefixIndex; i++)
			if (PrefixOrder.compare(this.terms[i], rprefix) == 0)
				matches++;

		return matches;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Autocomplete autocomplete = new Autocomplete(terms);
        StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            String msg = " matches for \"" + prefix + "\", in descending order by weight:";
            if (results.length == 0) {
                msg = "No matches";
            } else if (results.length > k) {
                msg = "First " + k + msg;
            } else {
                msg = "All" + msg;
            }
            StdOut.printf("%s\n", msg);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println("  " + results[i]);
            }
            StdOut.print("Enter a prefix (or ctrl-d to quit): ");
        }
    }
}
