import dsa.WeightedQuickUnionUF;
import stdlib.In;
import stdlib.StdOut;

// An implementation of the Percolation API using the UF data structure.
public class UFPercolation implements Percolation {
	private int percolation_system_size;
	private boolean[][] percolation_system;
	private int openSites;
	

    // Constructs an n x n percolation system, with all sites blocked.
    public UFPercolation(int size) {
		// Size check
		if (size <= 0) {
			throw new IllegalArgumentException("Illegal n");
		}
    }

    // Opens site (i, j) if it is not already open.
    public void open(int i, int j) {
		// Out of Bounds checking
		if (i < 0 || i > percolation_system.length - 1) {
			throw new IndexOutOfBoundsException("Illegal i or j");
		} else if (j < 0 || j > percolation_system.length - 1) {
			throw new IndexOutOfBoundsException("Illegal i or j");
		}
    }

    // Returns true if site (i, j) is open, and false otherwise.
    public boolean isOpen(int i, int j) {
		// Out of Bounds checking
		if (i < 0 || i > percolation_system.length - 1) {
			throw new IndexOutOfBoundsException("Illegal i or j");
		} else if (j < 0 || j > percolation_system.length - 1) {
			throw new IndexOutOfBoundsException("Illegal i or j");
		}
	}

	// Returns true if site (i, j) is full, and false otherwise.
	public boolean isFull(int i, int j) {
		// Out of Bounds checking
        if (i < 0 || i > percolation_system.length - 1) {
			throw new IndexOutOfBoundsException("Illegal i or j");
		} else if (j < 0 || j > percolation_system.length - 1) {
			throw new IndexOutOfBoundsException("Illegal i or j");
		}
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // Returns true if this system percolates, and false otherwise.
    public boolean percolates() {
        ...
    }

    // Returns an integer ID (1...n) for site (i, j).
    private int encode(int i, int j) {
        ...
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        UFPercolation perc = new UFPercolation(n);
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
