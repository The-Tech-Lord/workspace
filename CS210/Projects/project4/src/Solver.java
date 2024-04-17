import dsa.LinkedStack;
import dsa.MinPQ;
import stdlib.In;
import stdlib.StdOut;

// A data type that implements the A* algorithm for solving the 8-puzzle and its generalizations.
public class Solver {
    int moves;                    // Minimum number of moves
	LinkedStack<Board> solution;  // Board sequence of shortest solution of initial board

    // Finds a solution to the initial board using the A* algorithm.
    public Solver(Board board) {
		solution = new LinkedStack<Board>();
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
		pq.insert(board);

		while (!pq.isEmpty()) {
			Board node = pq.delMin();

			if (node.isGoal()) {
				this.moves = node.moves;
				solution.push(node);
				break;
			}

			
		}
    }

    // Returns the minimum number of moves needed to solve the initial board.
    public int moves() {
        return this.moves;
    }

    // Returns a sequence of boards in a shortest solution of the initial board.
    public Iterable<Board> solution() {
        ...
    }

    // A data type that represents a search node in the grame tree. Each node includes a
    // reference to a board, the number of moves to the node from the initial node, and a
    // reference to the previous node.
    private class SearchNode implements Comparable<SearchNode> {
        Board board;          // Board represented by this node
		int moves;            // Number of moves to get from the initial node to this node
		SearchNode previous;  // Previous search node

        // Constructs a new search node.
        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
			this.moves = moves;
			this.previous = previous;
        }

        // Returns a comparison of this node and other based on the following sum:
        //   Manhattan distance of the board in the node + the # of moves to the node
        public int compareTo(SearchNode other) {
			int this_node_sum = this.board.manhattan + this.moves;
			int other_node_sum = other.board.manhattan + other.moves;
            if (this_node_sum < other_node_sum) {
				return -1;
			} else if (this_node_sum > other_node_sum) {
				return 1;
			}
			return 0;
        }
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
        Board initial = new Board(tiles);
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.printf("Solution (%d moves):\n", solver.moves());
            StdOut.println(initial);
            StdOut.println("----------");
            for (Board board : solver.solution()) {
                StdOut.println(board);
                StdOut.println("----------");
            }
        } else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}
