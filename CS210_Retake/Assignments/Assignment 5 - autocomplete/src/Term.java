import dsa.Quick;

import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class Term implements Comparable<Term> {
    private String query;
	private long weight;
	
    // Constructs a term given the associated query string, having weight 0.
    public Term(String query) {
        if (query == null)
			throw new NullPointerException("query is null");

		this.query = query;
		this.weight = 0;
    }

    // Constructs a Term given the associated query string and weight.
    public Term(String query, long weight) {
        if (query == null)
			throw new NullPointerException("query is null");
		if (weight < 0)
			throw new IllegalArgumentException("Illegal weight");

		this.query = query;
		this.weight = weight;
    }

	// Returns the object's `query`
	public String getQuery() {
		return this.query;
	}

	// Returns the object's `weight`
	public long getWeight() {
		return this.weight;
	}

    // Returns a string representation of this query.
    public String toString() {
		if (this.query == null)
			throw new NullPointerException("query is null");
		
        return this.weight + "\t" + this.query;
    }

    // Returns a comparison of this query and other by query.
    public int compareTo(Term other) {
        return this.getQuery().compareTo(other.getQuery());
    }

    // Returns a comparator for comparing two terms in reverse order of their weights.
    public static Comparator<Term> reverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // Returns a comparator for comparing two terms by their prefixes of length r.
    public static Comparator<Term> prefixOrder(int r) {
		if (r < 0)
			throw new IllegalArgumentException("Illegal r");
		
        return new PrefixOrder(r);
    }

    // Reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        // Returns a comparison of querys v and w by their weights in reverse order.
        public int compare(Term v, Term w) {
			// Because we're comparing with reverse-order in mind, we flip the greater than/less than signs
            if (v.getWeight() < w.getWeight())
				return 1;
			else if (v.getWeight() > w.getWeight())
				return -1;
			return 0;
        }
    }

    // Prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private int prefix_length;

        // Constructs a PrefixOrder given the prefix length.
        PrefixOrder(int r) {
            this.prefix_length = r;
        }

        // Returns a comparison of querys v and w by their prefixes of length r.
        public int compare(Term v, Term w) {
			// Store the prefixes of both querys
            String subV = v.getQuery().substring(0, Math.min(this.prefix_length, v.getQuery().length()));
			String subW = w.getQuery().substring(0, Math.min(this.prefix_length, w.getQuery().length()));

			// Compares prefixes lexicographically
			if (subV.compareTo(subW) > 0)
				return 1;
			else if (subV.compareTo(subW) < 0)
				return -1;
			return 0;
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
