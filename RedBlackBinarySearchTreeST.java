import java.util.NoSuchElementException;
import stdlib.StdIn;
import stdlib.StdOut;

import dsa.OrderedST;
import dsa.LinkedQueue;

public class RedBlackBinarySearchTreeST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {
  private static final boolean RED = true;
  
  private static final boolean BLACK = false;
  
  private Node root = null;
  
  public boolean isEmpty() {
	return (this.root == null);
  }
  
  public int size() {
	return size(this.root);
  }
  
  public void put(Key key, Value value) {
	if (key == null)
	  throw new IllegalArgumentException("key is null"); 
	if (value == null)
	  throw new IllegalArgumentException("value is null"); 
	this.root = put(this.root, key, value);
	this.root.color = false;
  }
  
  public Value get(Key key) {
	if (key == null)
	  throw new IllegalArgumentException("key is null"); 
	return get(this.root, key);
  }
  
  public boolean contains(Key key) {
	return (get(key) != null);
  }
  
  public void delete(Key key) {
	if (key == null)
	  throw new IllegalArgumentException("key is null"); 
	if (!contains(key))
	  return; 
	if (!isRed(this.root.left) && !isRed(this.root.right))
	  this.root.color = true; 
	this.root = delete(this.root, key);
    if (!isEmpty())
      this.root.color = false; 
  }
  
  public Iterable<Key> keys() {
    if (isEmpty())
      return new LinkedQueue<>(); 
    return keys(min(), max());
  }
  
  public Key min() {
    if (isEmpty())
      throw new NoSuchElementException("Symbol table is empty"); 
    return (min(this.root)).key;
  }
  
  public Key max() {
    if (isEmpty())
      throw new NoSuchElementException("Symbol table is empty"); 
    return (max(this.root)).key;
  }
  
  public void deleteMin() {
    if (isEmpty())
      throw new NoSuchElementException("Symbol table is empty"); 
    if (!isRed(this.root.left) && !isRed(this.root.right))
      this.root.color = true; 
    this.root = deleteMin(this.root);
    if (!isEmpty())
      this.root.color = false; 
  }
  
  public void deleteMax() {
    if (isEmpty())
      throw new NoSuchElementException("Symbol table is empty"); 
    if (!isRed(this.root.left) && !isRed(this.root.right))
      this.root.color = true; 
    this.root = deleteMax(this.root);
    if (!isEmpty())
      this.root.color = false; 
  }
  
  public Key floor(Key key) {
    if (key == null)
      throw new IllegalArgumentException("key is null"); 
    Node x = floor(this.root, key);
    return (x == null) ? null : x.key;
  }
  
  public Key ceiling(Key key) {
    if (key == null)
      throw new IllegalArgumentException("key is null"); 
    Node x = ceiling(this.root, key);
    return (x == null) ? null : x.key;
  }
  
  public int rank(Key key) {
    if (key == null)
      throw new IllegalArgumentException("key is null"); 
    return rank(this.root, key);
  }
  
  public Key select(int k) {
    if (k < 0 || k >= size())
      throw new IllegalArgumentException("Invalid rank"); 
    Node x = select(this.root, k);
    return x.key;
  }
  
  public int size(Key lo, Key hi) {
    if (lo == null)
      throw new IllegalArgumentException("lo is null"); 
    if (hi == null)
      throw new IllegalArgumentException("hi is null"); 
    if (lo.compareTo(hi) > 0)
      return 0; 
    if (contains(hi))
      return rank(hi) - rank(lo) + 1; 
    return rank(hi) - rank(lo);
  }
  
  public Iterable<Key> keys(Key lo, Key hi) {
    if (lo == null)
      throw new IllegalArgumentException("lo is null"); 
    if (hi == null)
      throw new IllegalArgumentException("hi is null"); 
    LinkedQueue<Key> queue = new LinkedQueue<>();
    keys(this.root, queue, lo, hi);
    return queue;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Comparable comparable : keys()) {
      sb.append(comparable);
      sb.append(": ");
      sb.append(get((Key)comparable));
      sb.append(", ");
    } 
    return (size() > 0) ? ("{" + sb.substring(0, sb.length() - 2) + "}") : "{}";
  }
  
  private class Node {
    private Key key;
    
    private Value val;
    
    private int size;
    
    private boolean color;
    
    private Node left;
    
    private Node right;
    
    public Node(Key key, Value value) {
      this.key = key;
      this.val = value;
      this.color = true;
      this.size = 1;
    }
  }
  
  private boolean isRed(Node x) {
    return (x != null && x.color == true);
  }
  
  private int size(Node x) {
    return (x == null) ? 0 : x.size;
  }
  
  private Value get(Node x, Key key) {
    if (x == null)
      return null; 
    int cmp = key.compareTo(x.key);
    if (cmp < 0)
      return get(x.left, key); 
    if (cmp > 0)
      return get(x.right, key); 
    return x.val;
  }
  
  private Node put(Node x, Key key, Value value) {
    if (x == null)
      return new Node(key, value); 
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      x.left = put(x.left, key, value);
    } else if (cmp > 0) {
      x.right = put(x.right, key, value);
    } else {
      x.val = value;
    } 
    return balance(x);
  }
  
  private Node delete(Node x, Key key) {
    if (key.compareTo(x.key) < 0) {
      if (!isRed(x.left) && !isRed(x.left.left))
        x = moveRedLeft(x); 
      x.left = delete(x.left, key);
    } else {
      if (isRed(x.left))
        x = rotateRight(x); 
      if (key.compareTo(x.key) == 0 && x.right == null)
        return null; 
      if (!isRed(x.right) && !isRed(x.right.left))
        x = moveRedRight(x); 
      if (key.compareTo(x.key) == 0) {
        Node t = min(x.right);
        x.key = t.key;
        x.val = t.val;
        x.right = deleteMin(x.right);
      } else {
        x.right = delete(x.right, key);
      } 
    } 
    return balance(x);
  }
  
  private Node min(Node x) {
    return (x.left == null) ? x : min(x.left);
  }
  
  private Node max(Node x) {
    return (x.right == null) ? x : max(x.right);
  }
  
  private Node deleteMin(Node x) {
    if (x.left == null)
      return null; 
    if (!isRed(x.left) && !isRed(x.left.left))
      x = moveRedLeft(x); 
    x.left = deleteMin(x.left);
    return balance(x);
  }
  
  private Node deleteMax(Node x) {
    if (isRed(x.left))
      x = rotateRight(x); 
    if (x.right == null)
      return null; 
    if (!isRed(x.right) && !isRed(x.right.left))
      x = moveRedRight(x); 
    x.right = deleteMax(x.right);
    return balance(x);
  }
  
  private Node floor(Node x, Key key) {
    if (x == null)
      return null; 
    int cmp = key.compareTo(x.key);
    if (cmp == 0)
      return x; 
    if (cmp < 0)
      return floor(x.left, key); 
    Node t = floor(x.right, key);
    return (t == null) ? x : t;
  }
  
  private Node ceiling(Node x, Key key) {
    if (x == null)
      return null; 
    int cmp = key.compareTo(x.key);
    if (cmp == 0)
      return x; 
    if (cmp > 0)
      return ceiling(x.right, key); 
    Node t = ceiling(x.left, key);
    return (t == null) ? x : t;
  }
  
  private int rank(Node x, Key key) {
    if (x == null)
      return 0; 
    int cmp = key.compareTo(x.key);
    if (cmp < 0)
      return rank(x.left, key); 
    if (cmp > 0)
      return size(x.left) + rank(x.right, key) + 1; 
    return size(x.left);
  }
  
  private Node select(Node x, int k) {
    if (x == null)
      return null; 
    int t = size(x.left);
    if (t > k)
      return select(x.left, k); 
    if (t < k)
      return select(x.right, k - t - 1); 
    return x;
  }
  
  private void keys(Node x, LinkedQueue<Key> queue, Key lo, Key hi) {
    if (x == null)
      return; 
    int cmpLo = lo.compareTo(x.key);
    int cmpHi = hi.compareTo(x.key);
    if (cmpLo < 0)
      keys(x.left, queue, lo, hi); 
    if (cmpLo <= 0 && cmpHi >= 0)
      queue.enqueue(x.key); 
    if (cmpHi > 0)
      keys(x.right, queue, lo, hi); 
  }
  
  private Node rotateRight(Node h) {
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = x.right.color;
    x.right.color = true;
    x.size = h.size;
    h.size = size(h.left) + size(h.right) + 1;
    return x;
  }
  
  private Node rotateLeft(Node h) {
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = x.left.color;
    x.left.color = true;
    x.size = h.size;
    h.size = size(h.left) + size(h.right) + 1;
    return x;
  }
  
  private void flipColors(Node h) {
    h.color = !h.color;
    h.left.color = !h.left.color;
    h.right.color = !h.right.color;
  }
  
  private Node moveRedLeft(Node h) {
    flipColors(h);
    if (isRed(h.right.left)) {
      h.right = rotateRight(h.right);
      h = rotateLeft(h);
      flipColors(h);
    } 
    return h;
  }
  
  private Node moveRedRight(Node h) {
    flipColors(h);
    if (isRed(h.left.left)) {
      h = rotateRight(h);
      flipColors(h);
    } 
    return h;
  }
  
  private Node balance(Node h) {
    if (!isRed(h.left) && isRed(h.right))
      h = rotateLeft(h); 
    if (isRed(h.left) && isRed(h.left.left))
      h = rotateRight(h); 
    if (isRed(h.left) && isRed(h.right))
      flipColors(h); 
    h.size = size(h.left) + size(h.right) + 1;
    return h;
  }
  
  public static void main(String[] args) {
    RedBlackBinarySearchTreeST<String, Integer> st = new RedBlackBinarySearchTreeST<>();
    for (int i = 0; !StdIn.isEmpty(); i++) {
      String key = StdIn.readString();
      st.put(key, Integer.valueOf(i));
    } 
    StdOut.println(st);
  }
}
