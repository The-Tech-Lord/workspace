import dsa.LinkedStack;
import stdlib.StdOut;

// A data type to represent a text editor buffer.
public class Buffer {
    protected LinkedStack<Character> left;  // chars left of cursor
    protected LinkedStack<Character> right; // chars right of cursor

    // Creates an empty buffer.
    public Buffer() {
        this.left = new LinkedStack<Character>();
		this.right = new LinkedStack<Character>();
    }

    // Inserts c at the cursor position.
    public void insert(char c) {
        this.left.push(c);
    }

    // Deletes and returns the character immediately ahead of the cursor.
    public char delete() {
		if (this.right.isEmpty()) {
			return 'A';
		}
		
		return this.right.pop();
    }

    // Moves the cursor k positions to the left.
    public void left(int k) {
        for (int i = 0; i < k; i++) {
			// Place items from the left stack onto the right stack
			this.right.push(left.pop());
		}
    }

    // Moves the cursor k positions to the right.
    public void right(int k) {
        for (int i = 0; i < k; i++) {
			// Place items from the right stack onto the left stack
			this.left.push(right.pop());
		}
    }

    // Returns the number of characters in this buffer.
    public int size() {
        return this.left.size() + this.right.size();
    }

    // Returns a string representation of the buffer with the "|" character (not part of the buffer)
    // at the cursor position.
    public String toString() {
        // A buffer to store the string representation.
        StringBuilder sb = new StringBuilder();

        // Push chars from left into a temporary stack.
        LinkedStack<Character> tempStack = new LinkedStack<Character>();
		for (Character c : this.left) {
			tempStack.push(this.left.pop());
		}

        // Append chars from temporary stack to sb.
        for (Character c : tempStack) {
			sb.append(tempStack.pop());
		}

        // Append "|" to sb.
        sb.append("|");

        // Append chars from right to sb.
        for (Character c : this.right) {
			sb.append(this.right.pop());
		}

        // Return the string from sb.
		return sb.toString();
    }

    // Unit tests the data type (DO NOT EDIT).
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        String s = "There is grandeur in this view of life, with its several powers, having been " +
                "originally breathed into a few forms or into one; and that, whilst this planet " +
                "has gone cycling on according to the fixed law of gravity, from so simple a " +
                "beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.left(buf.size());
        buf.right(97);
        s = "by the Creator ";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.right(228);
        buf.delete();
        buf.insert('-');
        buf.insert('-');
        buf.left(342);
        StdOut.println(buf);
    }
}
