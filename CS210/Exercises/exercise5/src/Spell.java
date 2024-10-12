import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class Spell {
    // Entry point.
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] lines = in.readAllLines();
        in.close();

        // Create an ArrayST<String, String> object called st.
        ArrayST<String, String> st = new ArrayST<String, String>();

        // For each line in lines, split it into two tokens using "," as delimiter; insert into
        // st the key-value pair (token 1, token 2).
        for (int i = 0; i < lines.length; i++) {
			String[] splitLine = lines[i].split(",");
			st.put(splitLine[0], splitLine[1]);
		}

        // Read from standard input one line at a time; increment a line number counter; split
        // the line into words using "\\b" as the delimiter; for each word in words, if it
        // exists in st, write the (misspelled) word, its line number, and corresponding value
        // (correct spelling) from st.
		In lineInput = new In();
		String[] allLines = lineInput.readAllLines();
		lineInput.close();

		int lineNumber = 1;
		for (int i = 0; i < allLines.length; i++) {
			String[] words = allLines[i].split("\\b");

			for (int j = 0; j < words.length; j++) {
				if (st.contains(words[j])) {
					StdOut.println(words[j] + ":" + lineNumber + " -> " + st.get(words[j]));
				}
			}
			lineNumber++;
		}
    }
}
