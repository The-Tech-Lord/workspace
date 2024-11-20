import dsa.Quick;

import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class Term implements Comparable<Term> {
    private String term;
	private int weight;
	
    // Constructs a term given the associated query string, having weight 0.
    public Term(String query) {
        if (query == null)
			throw new NullPointerException("query is null");

		this.term = query;
		this.weight = 0;
    }

    // Constructs a Term given the associated query string and weight.
    public Term(String query, long weight) {
        if (query == null)
			throw new NullPointerException("query is null");
		if (weight < 0)
			throw new IllegalArgmentException("Illegal weight");

		this.term = query;
		this.weight = weight;
    }

    // Returns a string representation of this term.
    public String toString() {
        ...
    }

    // Returns a comparison of this term and other by query.
    public int compareTo(Term other) {
        ...
    }

    // Returns a comparator for comparing two terms in reverse order of their weights.
    public static Comparator<Term> reverseWeightOrder() {
        ...
    }

    // Returns a comparator for comparing two terms by their prefixes of length r.
    public static Comparator<Term> prefixOrder(int r) {
        ...
    }

    // Reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        // Returns a comparison of terms v and w by their weights in reverse order.
        public int compare(Term v, Term w) {
            ...
        }
    }

    // Prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        ...

        // Constructs a PrefixOrder given the prefix length.
        PrefixOrder(int r) {
            ...
        }

        // Returns a comparison of terms v and w by their prefixes of length r.
        public int compare(Term v, Term w) {
            ...
        }
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
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Quick.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Quick.sort(terms, Term.reverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
