import dsa.SeparateChainingHashST;
import java.util.Arrays;
import stdlib.StdOut;

public class CommonStringST {
	private static String commonString(String[] a, String[] b) {
		//SeparateChainingHashST<String, Integer> a_st = new SeparateChainingHashST<String, Integer>();
		SeparateChainingHashST<String, Integer> b_st = new SeparateChainingHashST<String, Integer>();

		// int i = 1;
		// for (String key : a) {
		// 	a_st.put(key, i);
		// 	i++;
		// }
		int j = 1;
		for (String key : b) {
			b_st.put(key, j);
			j++;
		}

		for (String key : a) {
			if (b_st.contains(key)) {
				return key;
			}
		}

		return null;
	}

	public static void main(String[] args) {
		String a = "GCA TCA ACG ACT GTC AGC GTA ATG";
		String b = "GAT GCA CAG GCT TCG GTC CTA ATG";
		String c = "it was the best of times it was the worst of times";
		String[] aList = a.split("\\s+");
		String[] bList = b.split("\\s+");
		String[] cList = c.split("\\s+");
		StdOut.println(commonString(aList, bList));
		StdOut.println(commonString(aList, cList));
	}
}
