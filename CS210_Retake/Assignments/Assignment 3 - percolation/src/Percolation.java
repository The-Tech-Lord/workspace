import dsa.WeightedQuickUnionUF;
import stdlib.In;
import stdlib.StdOut;

public class Percolation {
	WeightedQuickUnionUF uf_percolation_system;
	WeightedQuickUnionUF uf_percolation_system_nobackwash;
    boolean[][] percolation_system;
	int         percolation_system_size;
	int         openSites;

    // Constructs an n x n percolation system, with all sites blocked.
    public Percolation(int n) {
		// Bounds checking
        if (n <= 0)
			throw new IllegalArgumentException("Illegal n");

		// Instantiate the percolation system and set sites to a "blocked" state (i.e. false)
		this.percolation_system_size = n;
		this.percolation_system = new boolean[this.percolation_system_size][this.percolation_system_size];
		this.openSites = 0;

		// Create two percolation systems to address the backwash problem
		this.uf_percolation_system = new WeightedQuickUnionUF(this.percolation_system_size * this.percolation_system_size + 2);
		this.uf_percolation_system_nobackwash = new WeightedQuickUnionUF(this.percolation_system_size * this.percolation_system_size + 1);
    }

    // Opens site (i, j) if it is not already open.
    public void open(int i, int j) {
		if (!this.isOpen(i, j)) {
			this.percolation_system[i][j] = true;
			this.openSites++;

			// Connect any sites that are open in the first row of the system to the source
			if (i == 0) {
				this.uf_percolation_system.union(0, this.encode(i, j));
				this.uf_percolation_system_nobackwash.union(0, this.encode(i, j));
			}

			// Connect any sites that are open in the last row of the system to the sink
			if (i == this.percolation_system_size - 1)
				this.uf_percolation_system.union(this.percolation_system_size * this.percolation_system_size + 1, this.encode(i, j));

			/* Check for open, adjacent (north, east, south, west) sites and connect
			 * them in `uf_percolation_system` and `uf_percolation_system_nobackwash`
			 */

			   // North
			if (i - 1 >= 0) {
				if (this.isOpen(i - 1, j)) {
					this.uf_percolation_system.union(this.encode(i, j), this.encode(i - 1, j));
					this.uf_percolation_system_nobackwash.union(this.encode(i, j), this.encode(i - 1, j));
				}
			}

			   // East
			if (j + 1 <= this.percolation_system_size - 1) {
				if (this.isOpen(i, j + 1)) {
					this.uf_percolation_system.union(this.encode(i, j), this.encode(i, j + 1));
					this.uf_percolation_system_nobackwash.union(this.encode(i, j), this.encode(i, j + 1));
				}
			}

			   // South
			if (i + 1 <= this.percolation_system_size - 1) {
				if (this.isOpen(i + 1, j)) {
					this.uf_percolation_system.union(this.encode(i, j), this.encode(i + 1, j));
					this.uf_percolation_system_nobackwash.union(this.encode(i, j), this.encode(i + 1, j));
				}
			}

			   // West
			if (j - 1 >= 0) {
				if (this.isOpen(i, j - 1)) {
					this.uf_percolation_system.union(this.encode(i, j), this.encode(i, j - 1));
					this.uf_percolation_system_nobackwash.union(this.encode(i, j), this.encode(i, j - 1));
				}
			}
		}
    }

    // Returns true if site (i, j) is open, and false otherwise.
    public boolean isOpen(int i, int j) {
		// Checks if `i` and `j` are within bounds of `percolation_system[][]`
        if (i < 0 || i > this.percolation_system_size - 1)
			throw new IndexOutOfBoundsException("Illegal i or j");
		if (j < 0 || j > this.percolation_system_size - 1)
			throw new IndexOutOfBoundsException("Illegal i or j");

		// Checks if the site is open
		return this.percolation_system[i][j] == true;
    }

    // Returns true if site (i, j) is full, and false otherwise.
    public boolean isFull(int i, int j) {
        // Checks if `i` and `j` are within bounds of `percolation_system[][]`
        if (i < 0 || i > this.percolation_system_size - 1)
			throw new IndexOutOfBoundsException("Illegal i or j");
		if (j < 0 || j > this.percolation_system_size - 1)
			throw new IndexOutOfBoundsException("Illegal i or j");

		// Check if site is open and connected to the source
		return this.isOpen(i, j) && this.uf_percolation_system.connected(0, this.encode(i, j)) && this.uf_percolation_system_nobackwash.connected(0, this.encode(i, j));
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // Returns true if this system percolates, and false otherwise.
    public boolean percolates() {
		// Checks if a system percolates by checking the connection of the source and sink
        return this.uf_percolation_system.connected(0, this.percolation_system_size * this.percolation_system_size + 1);
    }

    // Returns an integer ID (1...n) for site (i, j).
    private int encode(int i, int j) {
		// Converts an 2D array index into a union 1D index
        return 1 + (i * this.percolation_system_size + j);
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.printf("%d x %d system:\n", n, n);
        StdOut.printf("  Open sites = %d\n", perc.numberOfOpenSites());
        StdOut.printf("  Percolates = %b\n", perc.percolates());
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.printf("  isFull(%d, %d) = %b\n", i, j, perc.isFull(i, j));
        }
    }
}
