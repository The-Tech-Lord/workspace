import stdlib.StdOut;
import stdlib.StdRandom;

public class Card {
    // Entry point.
    public static void main(String[] args) {
		// Rulers and Shapes from a 52-card standard deck
		String clubs = "Clubs";
		String diamonds = "Diamonds";
		String hearts = "Hearts";
		String spades = "Spades";

		String jack = "Jack";
		String queen = "Queen";
		String king = "King";
		String ace = "Ace";

		int random_number_ruler_rank = StdRandom.uniform(2, 14 + 1);
		int random_shape_rank = StdRandom.uniform(0, 3 + 1);
		String random_ruler = ""; // Jack, Queen, King, Ace
		String random_shape = ""; // Clubs, Diamonds, Hearts, Spades

		// If `random_number_ruler_rank` <= 10, it will later be a number, otherwise
		// `random_number_ruler_rank` becomes one of the 4 below
		switch (random_number_ruler_rank) {
		case 11:
			random_ruler = jack;
			break;
		case 12:
			random_ruler = queen;
			break;
		case 13:
			random_ruler = king;
			break;
		case 14:
			random_ruler = ace;
			break;
		}

		// `random_shape` will be randomly the four shapes below
		switch (random_shape_rank) {
		case 0:
			random_shape = clubs;
			break;
		case 1:
			random_shape = diamonds;
			break;
		case 2:
			random_shape = hearts;
			break;
		case 3:
			random_shape = spades;
			break;
		}

		String ruler_or_number = ""; // Thought doing this would be more efficient as opposed to making separate
		                             // variables for the numbers
		if (random_number_ruler_rank < 11) {
			ruler_or_number = Integer.toString(random_number_ruler_rank);
		} else {
			ruler_or_number = random_ruler;
		}

		// Instead of creating new variables, I decided to directly embed the random methods within the print statement
		StdOut.println(ruler_or_number + " of " + random_shape);
    }
}
