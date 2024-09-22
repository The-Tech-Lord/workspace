import stdlib.StdOut;
import stdlib.StdRandom;

public class Die {
    // Entry point.
    public static void main(String[] args) {
        int side = StdRandom.uniform(1, 7);

		// Depending on the randomly selected side, it will output the associated die side
		switch (side) {
		case 1:
			StdOut.println("     \n  *  \n     ");
			break;
		case 2:
			StdOut.println("*    \n     \n    *");
			break;
		case 3:
			StdOut.println("*    \n  *  \n    *");
			break;
		case 4:
			StdOut.println("*   *\n     \n*   *");
			break;
		case 5:
			StdOut.println("*   *\n  *  \n*   *");
			break;
		case 6:
			StdOut.println("* * *\n     \n* * *");
			break;
		}
    }
}
