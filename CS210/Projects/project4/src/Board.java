import dsa.Inversions;
import dsa.LinkedQueue;
import stdlib.In;
import stdlib.StdOut;

// A data type to represent a board in the 8-puzzle game or its generalizations.
public class Board {
    int[][] tiles;  // Board declaration
	int n;          // Board size
	int hamming;    // Hamming distance
	int manhattan;  // Manhattan distance
	int blankPos;   // Position of the blank tile

	int[][] tiles_goal_board; // Goal board

    // Constructs a board from an n x n array; tiles[i][j] is the tile at row i and column j, with 0
    // denoting the blank tile.
    public Board(int[][] tiles) {
		// Initialize `this.tiles`
		this.tiles = new int[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				this.tiles[i][j] = tiles[i][j];
			}
		}

		// Used to compare against `this.tiles`
		this.tiles_goal_board = new int[this.tiles.length][this.tiles[0].length];

		// Initialize `tiles_goal_board`
		for (int i = 0, goal = 1; i < this.tiles.length; i++) {
			for (int j = 0; j < this.tiles[0].length; j++) {
				this.tiles_goal_board[i][j] = goal;
				goal++;
			}
		}
		// Make the last index in `tiles_goal_board` an empty tile
		// I decided to make 0 represent an empty tile as it is a simple number
		this.tiles_goal_board[this.tiles.length - 1][this.tiles[0].length - 1] = 0;

		// Set `this.n` to the amount of rows in `this.tiles`
		this.n = this.tiles.length;

		// Find Hamming and Manhattan distances
		this.hamming = this.hamming();
		this.manhattan = this.manhattan();

		// Find and set the position of `blankPos`
		int pos = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				pos++;
				if (this.tiles[i][j] == 0) {
					this.blankPos = pos;
				}
			}
		}
    }

    // Returns the size of this board.
    public int size() {
        return this.n;
    }

    // Returns the tile at row i and column j of this board.
    public int tileAt(int i, int j) {
        return this.tiles[i][j];
    }

    // Returns Hamming distance between this board and the goal board.
    public int hamming() {
		int hamming_distance = 0;
		
		// Compare all indices of `this.tiles` to `tiles_goal_board` and increment `this.hamming`
		// when an index is different from another; does not increment if the values are the same
		for (int i = 0; i < this.tiles.length; i++) {
			for (int j = 0; j < this.tiles[0].length; j++) {
				if (this.tiles[i][j] == 0) {
					continue;
				}
				
				if (this.tiles[i][j] != this.tiles_goal_board[i][j]) {
					hamming_distance++;
				}
			}
		}

		return hamming_distance;
    }

    // Returns the Manhattan distance between this board and the goal board.
    public int manhattan() {
        int manhattan_distance_total = 0;

		// Iterate through `this.tiles_goal_board`
		// Why did I become a programmer again?
		for (int y = 0; y < this.tiles_goal_board.length; y++) {
			for (int x = 0; x < this.tiles_goal_board[0].length; x++) {
				// Stores the Manhattan distance of two points using the board arrays
				// `this.tiles` and `this.tiles_goal_board`
				int manhattan_distance = 0;

				// Iterate through `this.tiles`
				// Yeah I know, this is very inefficient
				// I hate this algorithm too
				for (int l = 0; l < this.tiles.length; l++) {
					for (int k = 0; k < this.tiles[0].length; k++) {
						// Continue to the next index if `this.tiles[x][y]
						if (this.tileAt(k, l) != this.tiles_goal_board[x][y]) {
							continue;
						}
						// Check if the tile is blank
						if (this.tileAt(k, l) == 0) {
							continue;
						}

						manhattan_distance = Math.abs(x - k) + Math.abs(y - l);
					}
				}
				
				manhattan_distance_total += manhattan_distance;
			}
		}

		return manhattan_distance_total;
    }

    // Returns true if this board is the goal board, and false otherwise.
    public boolean isGoal() {
		// If `this.hamming` and `this.manhattan` are both 0, then it shows
		// that there are no oddities within the board
		if (this.hamming == 0 || this.manhattan == 0) {
			return true;
		}

		return false;
    }

    // Returns true if this board is solvable, and false otherwise.
    public boolean isSolvable() {
		// Create a 1D array that takes the "area" of `this.tiles`
        int[] board_copy = new int[this.size() * this.tiles[0].length - 1];

		// Used for getting inversion sum if board is even
		int blank_row = 0;

		// Iterate over `this.tiles` and add elements to `board_copy`
		for (int i = 0, board = 0; i < this.tiles.length; i++) {
			for (int j = 0; j < this.tiles[0].length && board < board_copy.length; j++) {
				// Skip over the blank tiles
				if (this.tileAt(i, j) == 0) {
					blank_row = i;
					continue;
				}
				
				board_copy[board] = this.tiles[i][j];
				board++;
			}
		}

		// Get the number of inversions in `board_copy` depending on if the
		// board is even or odd
		if (this.n % 2 == 0) {
			long inversion_count = Inversions.count(board_copy) + blank_row;

			// Even board check
			if (inversion_count % 2 != 0) {
				return true;
			} else if (inversion_count % 2 == 0) {
				return false;
			}
		} else {
			long inversion_count = Inversions.count(board_copy);

			// Odd board check
			if (inversion_count % 2 == 0) {
				return true;
			} else if (inversion_count % 2 != 0) {
				return false;
			}
		}

		return true;
    }

    // Returns an iterable object containing the neighboring boards of this board.
    public Iterable<Board> neighbors() {
        LinkedQueue<Board> queue = new LinkedQueue<Board>();

		/* Mathematically find the horizontal and vertical indices in `this.tiles`
		 * It was pretty fun figuring this out :D
		 */
		int blank_horizontal_index;
		int blank_vertical_index;
		
		if (blankPos % this.tiles[0].length == 0) {
			blank_horizontal_index = this.tiles[0].length - 1;
		} else {
			blank_horizontal_index = (blankPos % this.tiles[0].length) - 1;
		}

		blank_vertical_index = (int)(Math.ceil((float)blankPos / (float)this.tiles.length)) - 1;

		// South
		if (blank_vertical_index + 1 > this.tiles.length - 1) {
			;
		} else {
			Board south = new Board(this.tiles);

			// Store the value below `blankPos` and swap
			int temp_tile = south.tileAt(blank_vertical_index + 1, blank_horizontal_index);
			south.tiles[blank_vertical_index + 1][blank_horizontal_index] = south.tiles[blank_vertical_index][blank_horizontal_index];
			south.tiles[blank_vertical_index][blank_horizontal_index] = temp_tile;

			queue.enqueue(south);
		}
		
		// North
		if (blank_vertical_index - 1 < 0) {
			;
		} else {
			Board north = new Board(this.tiles);

			// Store the value above `blankPos` and swap
			int temp_tile = north.tileAt(blank_vertical_index - 1, blank_horizontal_index);
			north.tiles[blank_vertical_index - 1][blank_horizontal_index] = north.tiles[blank_vertical_index][blank_horizontal_index];
			north.tiles[blank_vertical_index][blank_horizontal_index] = temp_tile;
			
			queue.enqueue(north);
		}

		// East
		if (blank_horizontal_index + 1 > this.tiles[0].length - 1) {
			;
		} else {
			Board east = new Board(this.tiles);

			// Store the value to the right of `blankPos` and swap
			int temp_tile = east.tileAt(blank_vertical_index, blank_horizontal_index + 1);
			east.tiles[blank_vertical_index][blank_horizontal_index + 1] = east.tiles[blank_vertical_index][blank_horizontal_index];
			east.tiles[blank_vertical_index][blank_horizontal_index] = temp_tile;
			
			queue.enqueue(east);
		}

		// West
		if (blank_horizontal_index - 1 < 0) {
			;
		} else {
			Board west = new Board(this.tiles);

			// Store the value to the left of `blankPos` and swap
			int temp_tile = west.tileAt(blank_vertical_index, blank_horizontal_index - 1);
			west.tiles[blank_vertical_index][blank_horizontal_index - 1] = west.tiles[blank_vertical_index][blank_horizontal_index];
			west.tiles[blank_vertical_index][blank_horizontal_index] = temp_tile;

			queue.enqueue(west);
		}

		return queue;
    }

    // Returns true if this board is the same as other, and false otherwise.
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

		Board compared_board = (Board)other;

		// Compare indices of `this.tiles` and `this.tiles_goal_board`
		for (int i = 0; i < compared_board.tiles.length; i++) {
			for (int j = 0; j < compared_board.tiles[0].length; j++) {
				if (this.tiles[i][j] != compared_board.tiles[i][j]) {
					return false;
				}
			}
		}
		
		return true;
    }

    // Returns a string representation of this board.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2s", tiles[i][j] == 0 ? " " : tiles[i][j]));
                if (j < n - 1) {
                    s.append(" ");
                }
            }
            if (i < n - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    // Returns a defensive copy of tiles[][].
    private int[][] cloneTiles() {
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                clone[i][j] = tiles[i][j];
            }
        }
        return clone;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board board = new Board(tiles);
        StdOut.printf("The board (%d-puzzle):\n%s\n", n, board);
        String f = "Hamming = %d, Manhattan = %d, Goal? %s, Solvable? %s\n";
        StdOut.printf(f, board.hamming(), board.manhattan(), board.isGoal(), board.isSolvable());
        StdOut.println("Neighboring boards:");
        for (Board neighbor : board.neighbors()) {
            StdOut.println(neighbor);
            StdOut.println("----------");
        }
    }
}
