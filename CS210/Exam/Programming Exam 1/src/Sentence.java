import java.util.Comparator;
import java.util.Iterator;

import stdlib.StdOut;

public class Sentence implements Comparable<Sentence>, Iterable<String> {
	private String s; // the sentence
	private String[] words; // words in the sentence

	public Sentence(String s) {
		if (s == null) {
			throw new NullPointerException("s is null");
		}

		// Construct
		this.s = s;
		this.words = this.s.split("\\s+");
	}

	public int charCount() {
		// Return length of `s` (sentence)
		return this.s.length();
	}

	public int wordCount() {
		// Return length of `words`
		return this.words.length;
	}

	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (other.getClass() != this.getClass()) {
			return false;
		}

		// Create another object and point to type casted `other`
		Sentence other_str = (Sentence)other;

		// If one string has more or less characters, then they aren't equal also
		if (other_str.s.length() > this.s.length() || other_str.s.length() < this.s.length()) {
			return false;
		}

		// Compare each index value and check if the values are equal to each other
		for (int i = 0; i < this.s.length(); i++) {
			if (this.s.charAt(i) != other_str.s.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return this.charCount() + ":" + this.s;
	}

	public int compareTo(Sentence other) {
		if (this.words.length > other.words.length) {
			// Return a positive integer (representing how much greater it is) if `this.words.length` is greater than `other.words.length`
			return this.words.length - other.words.length;
		} else if (this.words.length < other.words.length) {
			// Return a negative integer (representing how much smaller it is) if `this.words.length` is less than `other.words.length`
			return other.words.length - this.words.length;
		}
		// Return 0 if they are both equal lengths
		return 0;
	}

	public static Comparator<Sentence> charCountOrder() {
		return new CharCountOrder();
	}

	public Iterator<String> iterator() {
		return new WordIterator();
	}

	private static class CharCountOrder implements Comparator<Sentence> {
		public int compare(Sentence s1, Sentence s2) {
			if (s1.s.length() > s2.s.length()) {
				// Return a positive integer (representing how much greater it is) if `s1.s.length` is greater than `s2.s.length`
				return s2.s.length() - s1.s.length();
			} else if (s1.s.length() < s2.s.length()) {
				// Return a negative integer (representing how much smaller it is) if `s1.s.length` is less than `s2.s.length`
				return s1.s.length() - s2.s.length();
			}
			// Return 0 if they are both equal lengths
			return 0;
		}
	}

	private class WordIterator implements Iterator<String> {
		private int i; // index of current letter

		public WordIterator() {
			this.i = 0;
		}

		public boolean hasNext() {
			// Returns true if `i` is less than word length
			return (i < Sentence.this.words.length) ? true : false;
		}

		public String next() {
			// return value of `i` to access value then increment `i`
			return Sentence.this.words[i++];
		}
	}

	public static void main(String[] args) {
		Sentence s1 = new Sentence("abc def ghi jkl mno");
		Sentence s2 = new Sentence("abcdefg hijklmn opqrst");
		Sentence s3 = new Sentence("abc def ghi jkl mno");
		StdOut.println(s1);
		StdOut.println(s2);
		StdOut.println(s3);
		StdOut.println(s1.wordCount());
		StdOut.println(s1.equals(s3));
		StdOut.println(s1.compareTo(s2));
		StdOut.println(Sentence.charCountOrder().compare(s1, s3));
		for (String word : s3) {
			StdOut.print(word + " ");
		}
		StdOut.println();
	}
}
