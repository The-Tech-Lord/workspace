import stdlib.StdOut;

import java.util.Comparator;
import java.util.Iterator;

public class Genome implements Comparable<Genome>, Iterable<Character> {
	private String a; // the genome sequence

	public Genome(String s) {
		if (s == null) {
			throw new NullPointerException("s is null");
		}
		this.a = s;
	}

	public double gcContent() {
		if (this.a.length() == 0) {
			throw new IllegalArgumentException("a is empty");
		}

		int gc_count = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == 'C' || a.charAt(i) == 'G') {
				gc_count++;
			}
		}

		return ((double)gc_count / (double)a.length()) * 100;
	}

	public String toString() {
		if (this.a == null) {
			throw new NullPointerException("s is null");
		}

		return a.length() + ":" + a;
	}

	public int compareTo(Genome other) {
		if (this.a == null) {
			throw new NullPointerException("s is null");
		}

		if (this.a.length() < other.a.length()) {
			return -1;
		} else if (this.a.length() > other.a.length()) {
			return 1;
		}

		return 0;
	}

	public static Comparator<Genome> gcOrder() {
		return new GCOrder();
	}

	public Iterator<Character> iterator() {
		return new ReverseIterator();
	}

	private static class GCOrder implements Comparator<Genome> {
		public int compare(Genome g1, Genome g2) {
			double g1_content = g1.gcContent();
			double g2_content = g2.gcContent();

			if (g1_content < g2_content) {
				return -1;
			} else if (g1_content > g2_content) {
				return 1;
			}

			return 0;
		}
	}

	private class ReverseIterator implements Iterator<Character> {
		private int i; // index of current letter

		public ReverseIterator() {
			if (Genome.this.a == null) {
				throw new NullPointerException("a is null");
			}
			
			this.i = a.length() - 1;
		}

		public boolean hasNext() {
			if (i < 0) {
				return false;
			}

			return true;
		}

		public Character next() {
			Character character = Genome.this.a.charAt(i);
			i--;
			return character;
		}
	}
	
	// Unit tests the data type.
	public static void main(String[] args) {
		Genome g1 = new Genome(args[0]);
		Genome g2 = new Genome(args[1]);
		StdOut.println(g1);
		StdOut.println(g2);
		StdOut.println(g1.gcContent());
		StdOut.println(g2.gcContent());
		StdOut.println(g1.compareTo(g2));
		StdOut.println(Genome.gcOrder().compare(g1, g2));
		for (char c : g1) {
			StdOut.print(c);
		}
		StdOut.println();
	}
}
