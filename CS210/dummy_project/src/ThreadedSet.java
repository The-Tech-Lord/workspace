import dsa.SeparateChainingHashST;
import java.util.NoSuchElementException;
import stdlib.StdOut;

public class ThreadedSet {
	private SeparateChainingHashST<String, String> sch_ST;
	private String previousKeyValue;

	public ThreadedSet() {
		this.sch_ST = new SeparateChainingHashST<String, String>();
		this.previousKeyValue = "null";
	}

	public void add(String s) {
		if (s == null) {
			throw new NullPointerException("s is null");
		}
		if (this.sch_ST.contains(s)) {
			return;
		}
		this.sch_ST.put(s, previousKeyValue);
		this.previousKeyValue = s;
	}

	public boolean contains(String s) {
		if (s == null) {
			throw new NullPointerException("s is null");
		}
		return this.sch_ST.get(s) != null;
	}

	public String previousKey(String s) {
		if (s == null) {
			throw new NullPointerException("s is null");
		}
		if (!this.sch_ST.contains(s)) {
			throw new NoSuchElementException("s is not in this set");
		}
		return this.sch_ST.get(s);
	}

	public static void main(String[] args) {
		ThreadedSet set = new ThreadedSet();
		set.add("aardvark");
		set.add("bear");
		set.add("cat");
		set.add("bear");
		StdOut.println(set.contains("bear"));
		StdOut.println(set.contains("tiger"));
		StdOut.println(set.previousKey("cat"));
		StdOut.println(set.previousKey("bear"));
		StdOut.println(set.previousKey("aardvark"));
	}
}
