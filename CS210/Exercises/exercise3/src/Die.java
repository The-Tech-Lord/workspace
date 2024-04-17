import stdlib.StdOut;
import stdlib.StdRandom;

public class Die implements Comparable<Die> {
    private int value; // the face value

    // Constructs a die.
    public Die() {
        this.value = 0;
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

		Die temp_other = (Die)other;
		return this.value == temp_other.value;
    }

    // Returns a comparison of this die with other, by their face values.
    public int compareTo(Die that) {
		// If for some reason the face value is greater than or less than a 6-sided die
		if (this.value() <= 0 || this.value() > 6 || that.value() <= 0 || that.value() > 6) {
			return Integer.MIN_VALUE;
		}

		// If the result is negative, the value of this.value was smaller than that.value
		// If the result is positive, the value of this.value was larger than that.value
        return this.value - that.value;
    }

    // Returns a string representation of this die.
    public String toString() {
		String output;
		
        switch (value) {
		case 1:
			output = "     \n  *  \n     ";
			break;
		case 2:
			output = "*    \n     \n    *";
			break;
		case 3:
			output = "*    \n  *  \n    *";
			break;
		case 4:
			output = "*   *\n     \n*   *";
			break;
		case 5:
			output = "*   *\n  *  \n*   *";
			break;
		case 6:
			output = "* * *\n\n* * *";
			break;
		default:
			output = "Not rolled yet";
		}

		return output;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);
        Die a = new Die();
        a.roll();
        while (a.value() != x) {
            a.roll();
        }
        Die b = new Die();        
        b.roll();
        while (b.value() != y) {
            b.roll();
        }
        Die c = new Die();        
        c.roll();
        while (c.value() != z) {
            c.roll();
        }
        StdOut.println("Dice a, b, and c:");
        StdOut.println(a);
        StdOut.println(b);
        StdOut.println(c);
        StdOut.println("a.equals(b)    = " + a.equals(b));
        StdOut.println("b.equals(c)    = " + b.equals(c));
        StdOut.println("a.compareTo(b) = " + a.compareTo(b));
        StdOut.println("b.compareTo(c) = " + b.compareTo(c));
    }
}
