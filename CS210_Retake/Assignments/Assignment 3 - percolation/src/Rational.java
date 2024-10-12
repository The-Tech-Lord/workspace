import stdlib.StdOut;

public class Rational {
    private long x;  // Numerator
	private long y;  // Denominator

    // Constructs a rational number whose numerator is x and denominator is 1.
    public Rational(long x) {
        this.x = x;
		this.y = 1;
    }

    // Constructs a rational number given its numerator x and denominator y.
    public Rational(long x, long y) {
		long gcd = gcd(x, y);
		
		this.y = y / gcd;
		this.x = x / gcd;
    }

    // Returns the sum of this rational number and other.
    public Rational add(Rational other) {
		long numerator = this.x * other.y + other.x * this.y;
		long denominator = this.y * other.y;

		return new Rational(numerator, denominator);
    }

    // Returns the product of this rational number and other.
    public Rational multiply(Rational other) {
		return new Rational(this.x * other.x, this.y * other.y);
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

        // Rationals a/b and c/d are equal iff a == c and b == d.
        Rational a = this, b = (Rational) other;

		if (a.x == b.x && a.y == b.y)
			return true;

		return false;
    }

    // Returns a string representation of this rational number.
    public String toString() {
        long a = this.x, b = this.y;
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
        StdOut.printf("1 + 1/2 + 1/4 + ... + 1/2^%d = %s\n", n, total);
    }
}
