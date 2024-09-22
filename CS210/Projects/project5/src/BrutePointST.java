import dsa.LinkedQueue;
import dsa.MinPQ;
import dsa.Point2D;
import dsa.RectHV;
import dsa.RedBlackBinarySearchTreeST;
import stdlib.StdIn;
import stdlib.StdOut;

public class BrutePointST<Value> implements PointST<Value> {
	private RedBlackBinarySearchTreeST<Point2D, Value> bst;

    // Constructs an empty symbol table.
    public BrutePointST() {
        this.bst = new RedBlackBinarySearchTreeST<Point2D, Value>();
    }

    // Returns true if this symbol table is empty, and false otherwise.
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Returns the number of key-value pairs in this symbol table.
    public int size() {
        return this.bst.size();
    }

    // Inserts the given point and value into this symbol table.
    public void put(Point2D p, Value value) {
		if (p == null) {
			throw new NullPointerException("p is null");
		}
		if (value == null) {
			throw new NullPointerException("value is null");
		}

		this.bst.put(p, value);

		// Uhm, why are elements getting removed??? I even decompiled the .jar files and I'm confused.
		//	StdOut.println("New Value: " + p);
		//StdOut.println("Int Value: " + value);
		//StdOut.println("Size: " + this.bst.size());
		//StdOut.println("Sanity: " + this.bst.contains(p));
		//StdOut.println(this.bst);
		//StdOut.println();
    }

    // Returns the value associated with the given point in this symbol table, or null.
    public Value get(Point2D p) {
        if (p == null) {
			throw new NullPointerException("p is null");
		}

		return bst.get(p);
    }

    // Returns true if this symbol table contains the given point, and false otherwise.
    public boolean contains(Point2D p) {
        if (p == null) {
			throw new NullPointerException("p is null");
		}

		return bst.get(p) != null;
    }

    // Returns all the points in this symbol table.
    public Iterable<Point2D> points() {
		return bst.keys();
    }

    // Returns all the points in this symbol table that are inside the given rectangle.
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
			throw new NullPointerException("rect is null");
		}

		// Create a linked list to hold all the keys
		LinkedQueue<Point2D> rect_queue = new LinkedQueue<Point2D>();

		// Iterate through all the points
		for (Point2D point : bst.keys()) {
			if (rect.contains(point)) {
				rect_queue.enqueue(point);
			}
		}

		return rect_queue;
    }

    // Returns the point in this symbol table that is different from and closest to the given point,
    // or null.
    public Point2D nearest(Point2D p) {
        if (p == null) {
			throw new NullPointerException("p is null");
		}

		// Create a linked queue to hold all the keys
		LinkedQueue<Point2D> queue = (LinkedQueue<Point2D>)this.bst.keys();

		// for (Point2D point : this.bst.keys()) {
		// 	StdOut.println(point.x() + " " + point.y());
		// }
		
		// StdOut.println(bst.keys().getClass().getName());
		
		Point2D nearest_point = null;

		// Iterate through all the points while adjusting `nearest_point` accordingly
		for (Point2D point : queue) {
			nearest_point = queue.dequeue();
			if (point == p) {
				continue;
			}
			
			if (nearest_point.distanceTo(p) > point.distanceTo(p)) {
				nearest_point = point;
			}
		}

		return nearest_point;
    }

    // Returns up to k points from this symbol table that are different from and closest to the
    // given point.
    public Iterable<Point2D> nearest(Point2D p, int k) {
        if (p == null) {
			throw new NullPointerException("p is null");
		}
		
		LinkedQueue<Point2D> queue = new LinkedQueue<Point2D>();

		

		return queue;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        BrutePointST<Integer> st = new BrutePointST<Integer>();
        double qx = Double.parseDouble(args[0]);
        double qy = Double.parseDouble(args[1]);
        int k = Integer.parseInt(args[2]);
        Point2D query = new Point2D(qx, qy);
        RectHV rect = new RectHV(-1, -1, 1, 1);
        int i = 0;
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point2D p = new Point2D(x, y);
            st.put(p, i++);
        }
        StdOut.println("st.empty()? " + st.isEmpty());
        StdOut.println("st.size() = " + st.size());
        StdOut.printf("st.contains(%s)? %s\n", query, st.contains(query));
        StdOut.printf("st.range(%s):\n", rect);
		StdOut.println(st.bst);
        for (Point2D p : st.range(rect)) {
            StdOut.println("  " + p);
        }
        StdOut.printf("st.nearest(%s) = %s\n", query, st.nearest(query));
        StdOut.printf("st.nearest(%s, %d):\n", query, k);
        for (Point2D p : st.nearest(query, k)) {
            StdOut.println("  " + p);
        }
    }
}
