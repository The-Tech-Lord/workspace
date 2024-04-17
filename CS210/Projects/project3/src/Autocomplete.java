import java.util.Arrays;
import java.util.Comparator;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class Autocomplete {
    Term[] terms;

    // Constructs an autocomplete data structure from an array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
			throw new NullPointerException("terms is null");
		}
		if (terms.length == 0) {
			this.terms = new Term[0];
			return;
		}

		// Store elements of `terms[]` into `this.terms[]`
		this.terms = new Term[terms.length];
		for (int i = 0; i < terms.length; i++) {
			this.terms[i] = terms[i];
		}

		// Sort `this.terms` lexicographically
		for (int i = 0; i < this.terms.length; i++) {
			for (int j = i + 1; j <= this.terms.length - 1; j++) {
				int lexicographic_compare = this.terms[i].compareTo(this.terms[j]);
				// If `this.terms[i]` is lexicographically greater than `this.terms[j]`, swap values at indices
				if (lexicographic_compare > 0) {
					Term temp = this.terms[i];
					this.terms[i] = this.terms[j];
					this.terms[j] = temp;
					break;
				}
				continue;
			}
		}
    }

    // Returns all terms that start with prefix, in descending order of their weights.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
			throw new NullPointerException("prefix is null");
		}
		if (this.terms == null) {
			throw new NullPointerException("terms is null");
		}
		if (this.terms.length == 0) {
			return new Term[0];
		}
		
		// Number of terms that share the given prefix
		int prefix_count = 0;
		
		// Create a Comparator object so that we can use the `compare` method and pass into function arguments
		Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());

		// Find the first and last indices of `this.terms` so that we can focus on a subset of the array
		Term prefix_term = new Term(prefix);
		int f_index = BinarySearchDeluxe.firstIndexOf(this.terms, prefix_term, prefixOrder);
		int l_index = BinarySearchDeluxe.lastIndexOf(this.terms, prefix_term, prefixOrder);

		// Returns an empty array if no matches are found
		if (f_index == -1 && l_index == -1) {
			return new Term[0];
		}

		// If there is only 1 match in the entire array
		if (prefix_count == 1) {
			Term[] term = new Term[1];
			term[0] = this.terms[f_index];  // Chose `f_index` arbitrarily
			return term;
		}
		// Loop through `this.terms[]` and check if a term has prefix
		for (int i = f_index; i < this.terms.length && i <= l_index; i++) {
			int lexicographic_compare = prefixOrder.compare(this.terms[i], prefix_term);
			// If `this.terms[i]` is lexicographically equal to `prefix_term`, increment `prefix_count`
			if (lexicographic_compare == 0) {
				prefix_count++;
			}
			continue;
		}
	
		// Put matching terms in `matches`
		Term[] matches = new Term[prefix_count];
		for (int i = f_index, j = 0; j < matches.length && i <= l_index; i++) {
			int lexicographic_compare = prefixOrder.compare(this.terms[i], prefix_term);
			// If `this.terms[i]` is lexicographically equal to `prefix_term`, insert value into `matches[j]`
			if (lexicographic_compare == 0) {
				matches[j] = this.terms[i];
				j++;
			}
		}

		// Sort `matches` in reverse weight order
		Comparator<Term> reverseWeightOrder = Term.byReverseWeightOrder();
		for (int i = 0; i < matches.length; i++) {
			for (int j = i + 1; j <= matches.length - 1; j++) {
				int lexicographic_compare = reverseWeightOrder.compare(matches[i], matches[j]);
				// If `matches[i]` is lexicographically less than `matches[j]`, swap the values
				if (lexicographic_compare == 1) {
					Term temp = matches[i];
					matches[i] = matches[j];
					matches[j] = temp;
					break;
				}
				continue;
			}
		}

		return matches;
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

		// Number of terms that share the given prefix
		int prefix_count = 0;

		// Create a Comparator object to pass into function arguments
		Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());

		// Find first and last indices
		Term prefix_term = new Term(prefix);
		int f_index = BinarySearchDeluxe.firstIndexOf(this.terms, prefix_term, prefixOrder);
		int l_index = BinarySearchDeluxe.lastIndexOf(this.terms, prefix_term, prefixOrder);

		// Loop through `this.terms[]` and check if a term has prefix
		for (int i = f_index; i < this.terms.length && i <= l_index; i++) {
			int lexicographic_compare = prefixOrder.compare(this.terms[i], prefix_term);
			// If `this.terms[i]` is lexicographically equal to `prefix_term`, increment `prefix_count`
			if (lexicographic_compare == 0) {
				prefix_count++;
			}
			continue;
		}

		return prefix_count;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
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
