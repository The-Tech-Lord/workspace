import stdlib.StdOut;
import stdlib.StdRandom;

public class Die implements Comparable<Die> {
    private int value; // the face value

    // Constructs a die.
    public Die() {
        this.value = -1;
    }

    // Rolls this die.
    public void roll() {
        this.value = StdRandom.uniform(1, 7);
    }

    // Returns the face value of this die.
    public int value() {
        return this.value;
    }

    // Returns true if this die is the same as other, and false otherwise.
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Die a = this, b = (Die) other;

		if (a.value == b.value)
			return true;

		return false;
    }

    // Returns a string representation of this die.
    public String toString() {
        switch (this.value) {
		case 1:
			return "     \n  *  \n     ";
		case 2:
			return "*    \n     \n    *";
		case 3:
			return "*    \n  *  \n    *";
		case 4:
			return "*   *\n     \n*   *";
		case 5:
			return "*   *\n  *  \n*   *";
		case 6:
			return "* * *\n     \n* * *";
		}
		return "Error";
    }

    // Returns a comparison of this die with other, based on their face values.
    public int compareTo(Die other) {
		if (this.value < other.value)
			return -1;
		else if (this.value > other.value)
			return 1;

		return 0;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        Die a = new Die();
        while (a.value() != 3) {
            a.roll();
        }
        Die b = new Die();
        while (b.value() != 5) {
            b.roll();
        }
        Die c = new Die();
        while (c.value() != 3) {
            c.roll();
        }
        StdOut.println("Dice a, b, and c:");
        StdOut.println(a);
        StdOut.println(b);
        StdOut.println(c);
        StdOut.println("a.equals(b)    = " + a.equals(b));
        StdOut.println("a.equals(c)    = " + a.equals(c));
        StdOut.println("a.compareTo(b) = " + a.compareTo(b));
        StdOut.println("a.compareTo(c) = " + a.compareTo(c));
    }
}
