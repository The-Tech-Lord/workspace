import java.util.Comparator;
import java.util.Iterator;
import stdlib.StdOut;

public class Word implements Comparable<Word>, Iterable<Character> {
	private String word;

	public Word(String word) {
		this.word = word;
	}

	public String getWord() {
		return this.word;
	}

	// Return the word
	public int length() {
		return this.getWord().length();
	}

	public boolean equals(Object other) {
		// Initial class checking
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (other.getClass() != this.getClass())
			return false;

		Word other_word = (Word)other;

		// Check if the words are the same length
		if (this.length() != other_word.length())
			return false;

		// Compare each character and return `false` if the characters
		// at an index are different
		for (int i = 0; i < this.length(); i++)
			if (this.getWord().charAt(i) != other_word.getWord().charAt(i))
				return false;
		return true;
	}

	public String toString() {
		return this.length() + ":" + this.word;
	}

	// Compare the lengths of two words
	// Return 1 if `this` is greater than `other`
	// Return 0 if `this` is equal to `other`
	// Return -1 if `this` is less than `other`
	public int compareTo(Word other) {
		if (this.length() > other.length())
			return 1;
		else if (this.length() < other.length())
			return -1;
		return 0;
	}

	// Returns a comparator for comparing words alphabetically
	public static Comparator<Word> alphaOrder() {
		return new AlphaOrder();
	}

	// Returns an iterator for reversing characters
	public Iterator<Character> iterator() {
		return new ReverseIterator();
	}

	private static class AlphaOrder implements Comparator<Word> {
		public int compare(Word w1, Word w2) {
			// Compare the lengths and use the smallest length
			int word_length = 0;
			if (w1.length() < w2.length())
				word_length = w1.length();
			else
				word_length = w2.length();
			
			for (int i = 0; i < word_length; i++) {
				StdOut.println("hello");
			}
			return 0;
		}
	}

	private class ReverseIterator implements Iterator<Character> {
		private char[] word_reversed;
		private String word;
		private int index;
		
		public ReverseIterator() {
			this.word = Word.this.getWord();
			this.index = 0;

			// Setup `word_reversed` as the reverse of the word
			for (int i = this.word.length(); i > 0; i--)
				word_reversed[i] = this.word.charAt(i);
		}

		// Check if there is another character to print
		public boolean hasNext() {
			return index != word_reversed.length;
		}

		// Return the character at `index`
		public Character next() {
			Character character = this.word_reversed[index];
			index++;
			return character;
		}
	}

	public static void main(String[] args) {
		Word w1 = new Word("abracadabra");
		Word w2 = new Word("alacazam");
		StdOut.println(w1);
		StdOut.println(w2);
		StdOut.println(w1.length());
		StdOut.println(w1.equals(w2));
		StdOut.println(w1.compareTo(w2));
		StdOut.println(Word.alphaOrder().compare(w1, w2));
		for (char c : w2) {
			StdOut.print(c + " ");
		}
		StdOut.println();
	}
}
