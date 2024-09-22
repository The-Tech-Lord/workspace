import dsa.BFSPaths;
import dsa.Graph;
import dsa.RedBlackBinarySearchTreeST;
import stdlib.In;
import stdlib.StdOut;

public class GraphProperties {
    private RedBlackBinarySearchTreeST<Integer, Integer> st; // degree -> frequency
    private double avgDegree;                                // average degree of the graph
    private double avgPathLength;                            // average path length of the graph
    private double clusteringCoefficient;                    // clustering coefficient of the graph

    // Computes graph properties for the undirected graph G.
    public GraphProperties(Graph G) {
		if (G == null) {
			throw new NullPointerException("Constructor : G is null");
		}

		// Frequency
		this.st = new RedBlackBinarySearchTreeST<Integer, Integer>();

		for (int i = 0; i < G.V(); i++) {
			if (G.degree(i) == 0) {
				continue;
			}
			if (this.st.contains(G.degree(i))) {
				continue;
			}

			int frequency = 0;
			for (int j = 0; j < G.V(); j++) {
				if (G.degree(j) == G.degree(i)) {
					frequency++;
				}
			}
			
			this.st.put(G.degree(i), frequency);
		}

		// Average Degree
		this.avgDegree = (2 * (double)G.E()) / (double)G.V();

		StdOut.println(G.E() + " " + G.V());

		// Average Path Length
		

		// Clustering Coefficient
		double totalLocal = 0.0;  // Holds the summation of all the local clustering coefficients
		for (int i = 0; i < G.V(); i++) {
			StdOut.println("Degree " + i + ": " + G.degree(i));
			// Skip empty indices
			if (G.degree(i) == 0) {
				continue;
			}
			
			double localCoefficient = ((double)G.degree(i) * ((double)G.degree(i) - 1.0)) / 2.0;
			totalLocal += localCoefficient;
		}
		this.clusteringCoefficient = totalLocal / (double)G.V();
    }

    // Returns the degree distribution of the graph (a symbol table mapping each degree value to
    // the number of vertices with that value).
    public RedBlackBinarySearchTreeST<Integer, Integer> degreeDistribution() {
        return this.st;
    }

    // Returns the average degree of the graph.
    public double averageDegree() {
        return this.avgDegree;
    }

    // Returns the average path length of the graph.
    public double averagePathLength() {
        return this.avgPathLength;
    }

    // Returns the global clustering coefficient of the graph.
    public double clusteringCoefficient() {
        return this.clusteringCoefficient;
    }

    // Returns true if G has an edge between vertices v and w, and false otherwise.
    private static boolean hasEdge(Graph G, int v, int w) {
        for (int u : G.adj(v)) {
            if (u == w) {
                return true;
            }
        }
        return false;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        GraphProperties gp = new GraphProperties(G);
        RedBlackBinarySearchTreeST<Integer, Integer> st = gp.degreeDistribution();
        StdOut.println("Degree distribution:");
        for (int degree : st.keys()) {
            StdOut.println("  " + degree + ": " + st.get(degree));
        }
        StdOut.printf("Average degree         = %7.3f\n", gp.averageDegree());
        StdOut.printf("Average path length    = %7.3f\n", gp.averagePathLength());
        StdOut.printf("Clustering coefficient = %7.3f\n", gp.clusteringCoefficient());
    }
}
