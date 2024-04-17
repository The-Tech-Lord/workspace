import stdlib.StdOut;

public class Rational {
    private long numerator;
    private long denominator;

    // Constructs a rational number whose numerator is x and denominator is 1.
    public Rational(long numerator) {
        // Set this.x to x and this.y to 1.
		this.numerator = numerator;
		this.denominator = 1;
    }

    // Constructs a rational number given its numerator x and denominator y.
    public Rational(long numerator, long denominator) {
		if (numerator == 0 || denominator == 0) {
			this.numerator = numerator;
			this.denominator = denominator;
			return;
		}
		
        // Set this.x to x / gcd(x, y) and this.y to y / gcd(x, y).
		this.numerator = numerator / gcd(numerator, denominator);
		this.denominator = denominator / gcd(numerator, denominator);
    }

	public long getNumerator() {
		return this.numerator;
	}

	public void setNumerator(long numerator) {
		this.numerator = numerator;
	}

	public long getDenominator() {
		return this.denominator;
	}

	public void setDenominator(long denominator) {
		this.denominator = denominator;
	}

    // Returns the sum of this rational number and other.
	public Rational add(Rational other) {
		// Sum of rationals a/b and c/d is the rational (ad + bc) / bd.
		this.numerator = (this.numerator * other.denominator) + (this.denominator * other.numerator);
		this.denominator = this.denominator * other.denominator;
		
		long total_num_temp = this.numerator;
		this.numerator = this.numerator / gcd(this.numerator, this.denominator);
		this.denominator = this.denominator / gcd(total_num_temp, this.denominator);
		
		return this;
	}

	// Returns the product of this rational number and other.
	public Rational multiply(Rational other) {
		// Product of rationals a/b and c/d is the rational ac / bd.
		this.numerator = this.numerator * other.numerator;
		this.denominator = this.denominator * other.denominator;
		return this;
	}

	// Returns true if this rational number is equal to other, and false otherwise.
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

		// Since the type of the other object is unknown, we typecast it to class Rational and store it in another variable
		Rational rational = (Rational)other;
		
        // Rationals a/b and c/d are equal iff a == c and b == d.
        if (this.numerator == rational.numerator && this.denominator == rational.denominator) {
			return true;
		} else {
			return false;
		}
	}

	// Returns a string representation of this rational number.
	public String toString() {
		long a = numerator, b = denominator;
		if (a == 0 || b == 1) {
			return a + "";
		}
		if (b < 0) {
			a *= -1;
			b *= -1;
		}
		return a + "/" + b;
	}

	// Returns gcd(p, q), computed using Euclid's algorithm.
	private static long gcd(long p, long q) {
		return q == 0 ? p : gcd(q, p % q);
	}

	// Unit tests the data type. [DO NOT EDIT]
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Rational total = new Rational(0);
		Rational term = new Rational(1);
		for (int i = 1; i <= n; i++) {
			total = total.add(term);
			term = term.multiply(new Rational(1, 2));
        }
        Rational expected = new Rational((long) Math.pow(2, n) - 1, (long) Math.pow(2, n - 1));
        StdOut.printf("a           = 1 + 1/2 + 1/4 + ... + 1/2^%d = %s\n", n, total);
        StdOut.printf("b           = (2^%d - 1) / 2^(%d - 1) = %s\n", n, n, expected);
        StdOut.printf("a.equals(b) = %b\n", total.equals(expected));
    }
}
